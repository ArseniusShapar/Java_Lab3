package org.oop.lab3;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MyBinder implements Binder {
    private final Map<Class, Callable> rules = new HashMap<>();
    private final MethodCreator methodCreator = new MethodCreator();

    public <T> Callable<T> getMethod(Class<T> clazz) {
        return rules.get(clazz);
    }

    public <T> void saveRule(Class<T> clazz, Constructor<T> constructor) {
        constructor.setAccessible(true);
        Callable<T> method = (Callable<T>) methodCreator.createByConstructor(constructor);
        rules.put(clazz, method);
    }

    private <T> Constructor<T> findInject(Class<T> clazz) throws RuntimeException {
        boolean injectExist = false;
        boolean oneInject = true;
        Constructor<T> injectConstructor = null;
        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                if (injectExist) {
                    oneInject = false;
                    break;
                }
                injectExist = true;
                injectConstructor = constructor;
            }
        }
        if (!injectExist) throw new RuntimeException("Constructor with inject does not exist");
        if (!oneInject) throw new RuntimeException("More then one constructor with inject");

        return injectConstructor;
    }

    private <T> Constructor<T> findConstructor(Class<T> clazz, Class[] parameters) throws RuntimeException {
        try {
            return clazz.getConstructor(parameters);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Constructor with given parameters does not exist");
        }
    }

    @Override
    public <T> void bind(Class<T> clazz) throws RuntimeException {
        Constructor<T> injectConstructor = findInject(clazz);
        saveRule(clazz, injectConstructor);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        Constructor<T> injectConstructor = (Constructor<T>) findInject(implementation);
        saveRule(clazz, injectConstructor);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        Callable<T> method = methodCreator.createByInstance(instance);
        rules.put(clazz, method);
    }

    public <T> void bind(Class<T> clazz, Class[] parameters) {
        Constructor<T> constructor = findConstructor(clazz, parameters);
        saveRule(clazz, constructor);
    }

    public <T> void bind(Class<T> clazz, Class<? extends T> implementation, Class[] parameters) {
        Constructor<T> constructor = (Constructor<T>) findConstructor(implementation, parameters);
        saveRule(clazz, constructor);
    }
}
