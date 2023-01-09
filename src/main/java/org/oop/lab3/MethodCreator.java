package org.oop.lab3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

public class MethodCreator {
    public <T> Callable<T> createByConstructor(Constructor<T> constructor) {
        return new Callable<T>() {
            @Override
            public T call() {
                try {
                    return (T) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    public <T> Callable<T> createByInstance(T instance) {
        return new Callable<T>() {
            @Override
            public T call() {
                return instance;
            }
        };
    }
}

