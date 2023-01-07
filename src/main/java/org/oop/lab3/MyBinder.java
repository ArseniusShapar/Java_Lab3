package org.oop.lab3;

import javax.inject.Inject;
import java.lang.reflect.Constructor;

public class MyBinder {
    @Inject
    public <T> void bind(Class<T> clazz) {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            //Constructor maintConstructor = constructor.getAnnotation();
        }
    }
}
