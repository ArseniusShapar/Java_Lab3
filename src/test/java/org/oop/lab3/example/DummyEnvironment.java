package org.oop.lab3.example;

import org.oop.lab3.Configuration;
import org.oop.lab3.Container;
import org.oop.lab3.Environment;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) {
        return new DummyContainer();
    }
}
