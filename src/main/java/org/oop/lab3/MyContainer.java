package org.oop.lab3;

import java.util.Map;

public class MyContainer implements Container {
    private final Map<Class, Creator> rules;

    public MyContainer(Map<Class, Creator> rules) {
        this.rules = rules;
    }

    <T> T getComponent(Creator<T> creator) {
        if (creator.getParams() == null) return creator.create(null);
        else {
            Object[] args = createArgs(creator.getParams());
            return creator.create(args);
        }
    }

    private Object[] createArgs(Class[] params) {
        Object[] args = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            Class param = params[i];
            byte defaultNumber = 0;
            if (param == boolean.class) args[i] = false;
            else if (param == char.class) args[i] = Character.MIN_VALUE;
            else if (param.isPrimitive()) args[i] = defaultNumber;
            else if (rules.containsKey(param)) args[i] = getComponent(param);
            else args[i] = null;
        }
        return args;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        Creator<T> creator = rules.get(clazz);
        return getComponent(creator);
    }

    public <T> T getComponent(Class<T> clazz, Object[] args) {
        Creator<T> creator = rules.get(clazz);
        if (creator.getParams() == null) return creator.create(null);
        else return creator.create(args);
    }
}
