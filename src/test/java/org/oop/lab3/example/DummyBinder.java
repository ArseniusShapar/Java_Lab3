package org.oop.lab3.example;

import org.oop.lab3.Binder;

public class DummyBinder implements Binder {
    @Override
    public <T> void bind(Class<T> clazz) {

    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {

    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {

    }
}
