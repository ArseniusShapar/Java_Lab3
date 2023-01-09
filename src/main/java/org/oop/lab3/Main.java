package org.oop.lab3;

public class Main {
    public static void main(String[] args) {
        MyBinder binder = new MyBinder();
        try {
            binder.bind(A.class, B.class);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}