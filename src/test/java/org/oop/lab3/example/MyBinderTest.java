package org.oop.lab3.example;

import org.junit.Before;
import org.junit.Test;
import org.oop.lab3.MyBinder;

import static org.junit.Assert.assertEquals;

public class MyBinderTest {
    private MyBinder binder;

    @Before
    public void setUp() {
        binder = new MyBinder();
    }

    @Test
    public void testZeroInjections() {
        try {
            binder.bind(ZeroInject.class);
        } catch (RuntimeException e) {
            String expectedMessage = "Constructor with inject does not exist";
            String actualMessage = e.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }
    }

    @Test
    public void testOneInjection() {
        binder.bind(OneInject.class);
    }

    @Test
    public void testTwoInjections() {
        try {
            binder.bind(TwoInject.class);
        } catch (RuntimeException e) {
            String expectedMessage = "More then one constructor with inject";
            String actualMessage = e.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }
    }

    @Test
    public void testPrivateInjection() {
        binder.bind(PrivateInject.class);
    }

}
