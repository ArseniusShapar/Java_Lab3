package org.oop.lab3.example;

import javax.inject.Inject;

public class WithDependency {
    private final Child dependency;

    @Inject
    public WithDependency(Child child) {
        this.dependency = child;
    }

    public Child getDependency() {
        return dependency;
    }
}
