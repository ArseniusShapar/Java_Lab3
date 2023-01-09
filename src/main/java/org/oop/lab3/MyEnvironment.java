package org.oop.lab3;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

public class MyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        MyConfiguration myConfiguration = (MyConfiguration) configuration;
        Map<Class, Creator> rules = myConfiguration.getRules();
        Map<Class, Creator> newRules = new HashMap<>();
        MyContainer utilityContainer = new MyContainer(rules);

        for (Class clazz : rules.keySet()) {
            if (clazz != rules.get(clazz).getImplementation() && rules.containsKey(rules.get(clazz).getImplementation())) {
                Class implementation = rules.get(clazz).getImplementation();
                newRules.put(clazz, rules.get(implementation));
            } else if (clazz.isAnnotationPresent(Singleton.class) && rules.get(clazz).getParams() != null) {
                Creator instanceCreator = new Creator(utilityContainer.getComponent(clazz));
                newRules.put(clazz, instanceCreator);
            } else newRules.put(clazz, rules.get(clazz));

        }
        return new MyContainer(newRules);
    }
}
