package org.oop.lab3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Creator<T> {
    private final Constructor<? extends T> constructor;
    private final T instance;
    private final Class<? extends T> implementation;
    private final Class[] params;

    public Creator(Constructor<T> constructor) {
        this.constructor = constructor;
        params = constructor.getParameterTypes();
        instance = null;
        implementation = null;
    }

    public Creator(T instance) {
        this.instance = instance;
        constructor = null;
        params = null;
        implementation = null;
    }

    public Creator(Constructor<? extends T> constructor, Class<? extends T> implementation) {
        this.constructor = constructor;
        this.implementation = implementation;
        params = constructor.getParameterTypes();
        instance = null;
    }

    public Class<? extends T> getImplementation() {
        return implementation;
    }

    public Class[] getParams() {
        return params;
    }

    public T create(Object[] args) {
        if (constructor == null && implementation == null) return instance;
        else {
            try {
                return constructor.newInstance(args);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
