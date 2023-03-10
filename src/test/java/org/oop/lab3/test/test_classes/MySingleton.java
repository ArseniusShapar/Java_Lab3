package org.oop.lab3.test.test_classes;

import javax.inject.Singleton;

@Singleton
public class MySingleton {
    private static MySingleton instance;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (instance == null) instance = new MySingleton();
        return instance;
    }
}
