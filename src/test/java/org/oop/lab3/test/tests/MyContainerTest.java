package org.oop.lab3.test.tests;

import org.junit.Before;
import org.junit.Test;
import org.oop.lab3.MyBinder;
import org.oop.lab3.MyContainer;
import org.oop.lab3.MyEnvironment;
import org.oop.lab3.test.test_classes.*;

import static org.junit.Assert.*;

public class MyContainerTest {
    private MyContainer container;
    private TestConfiguration configuration;

    @Before
    public void setUp() {
        MyBinder binder = new MyBinder();
        configuration = new TestConfiguration();
        MyEnvironment environment = new MyEnvironment();
        configuration.configure(binder);
        container = (MyContainer) environment.configure(configuration);
    }

    @Test
    public void testInstance() {
        assertSame(container.getComponent(Instance.class), configuration.instance);
    }

    @Test
    public void testZeroParams() {
        OneInject a = container.getComponent(OneInject.class);
        assertNotNull(a);
    }

    @Test
    public void testCreateWithArgs() {
        ManyParams fromConstructor = new ManyParams(0, 0);
        ManyParams fromContainer = container.getComponent(ManyParams.class, new Object[]{0, 0});
        assertNotNull(fromContainer);
        assertEquals(fromConstructor, fromContainer);
    }

    @Test
    public void shouldInjectSingleton() {
        assertNotNull(container.getComponent(MySingleton.class));
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(OneInject.class), container.getComponent(OneInject.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        final Child child = container.getComponent(Child.class);
        assertSame(container.getComponent(Parent.class), child);
        assertSame(container.getComponent(Child.class), child);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final WithDependency withDependency = container.getComponent(WithDependency.class);
        assertSame(withDependency.getDependency(), container.getComponent(Child.class));
    }
}
