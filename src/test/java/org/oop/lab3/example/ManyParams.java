package org.oop.lab3.example;

public class ManyParams {
    private final int a;
    private final float b;

    public ManyParams(int a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        ManyParams that = (ManyParams) o;
        return this.a == that.a && this.b == that.b;
    }
}
