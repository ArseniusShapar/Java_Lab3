package org.oop.lab3;

import java.util.HashMap;
import java.util.Map;

public class MyConfiguration implements Configuration {
    private final Map<Class, Creator> rules = new HashMap<>();

    protected final void saveRules(Binder binder) {
        MyBinder myBinder = (MyBinder) binder;
        for (Class clazz : myBinder.getClasses()) rules.put(clazz, myBinder.getCreator(clazz));
    }

    Map<Class, Creator> getRules() {
        return rules;
    }

    @Override
    public void configure(Binder binder) {
    }


}
