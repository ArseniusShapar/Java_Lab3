package org.oop.lab3.test.tests;

import org.oop.lab3.Binder;
import org.oop.lab3.MyConfiguration;
import org.oop.lab3.test.test_classes.*;

public class TestConfiguration extends MyConfiguration {
    public Instance instance = new Instance();

    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class, MySingleton.getInstance());
        binder.bind(OneInject.class);
        binder.bind(ManyParams.class);
        binder.bind(Instance.class, instance);

        binder.bind(WithDependency.class);

        binder.bind(Parent.class, Child.class);
        binder.bind(Child.class, new Child());
        saveRules(binder);
    }
}
