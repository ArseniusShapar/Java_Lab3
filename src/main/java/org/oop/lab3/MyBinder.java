package org.oop.lab3;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyBinder implements Binder {
    private final Map<Class, Creator> rules = new HashMap<>();

    public <T> Creator<T> getCreator(Class<T> clazz) {
        return rules.get(clazz);
    }

    Set<Class> getClasses() {
        return rules.keySet();
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
        injectConstructor.setAccessible(true);
        Creator<T> creator = new Creator<>(injectConstructor);
        rules.put(clazz, creator);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        Constructor<? extends T> injectConstructor = findInject(implementation);
        injectConstructor.setAccessible(true);
        Creator<T> creator = new Creator<>(injectConstructor, implementation);
        rules.put(clazz, creator);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        Creator<T> creator = new Creator<>(instance);
        rules.put(clazz, creator);
    }

    public <T> void bind(Class<T> clazz, Class[] parameters) {
        Constructor<T> constructor = findConstructor(clazz, parameters);
        constructor.setAccessible(true);
        Creator<T> creator = new Creator<>(constructor);
        rules.put(clazz, creator);
    }

    public <T> void bind(Class<T> clazz, Class<? extends T> implementation, Class[] parameters) {
        Constructor<? extends T> constructor = findConstructor(implementation, parameters);
        constructor.setAccessible(true);
        Creator<T> creator = new Creator<>(constructor, implementation);
        rules.put(clazz, creator);
    }
}
