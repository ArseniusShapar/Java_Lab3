package org.oop.lab3.example;

import org.oop.lab3.Binder;
import org.oop.lab3.MyConfiguration;

public class TestConfiguration extends MyConfiguration {
    public Instance instance = new Instance();

    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class, MySingleton.getInstance());
        binder.bind(OneInject.class);
        binder.bind(ManyParams.class, new Class[]{int.class, float.class});
        binder.bind(Instance.class, instance);

        binder.bind(WithDependency.class);

        binder.bind(Parent.class, Child.class);
        binder.bind(Child.class, new Child());
        saveRules(binder);
    }
}
