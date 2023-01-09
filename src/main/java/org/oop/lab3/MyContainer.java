package org.oop.lab3;

import java.lang.reflect.Constructor;

public class MyContainer implements Container {
    private final MyBinder binder;

    public MyContainer(MyBinder binder) {
        this.binder = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        Constructor<T> constructor = null;
        return null;
    }
}
