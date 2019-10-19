package ru.demi.reflection;

import java.lang.reflect.Field;

/**
 * Write the code for reflection prevent for an Immutable object. (SecurityManager)
 * Run with -Djava.security.manager, also you can set own policy with permissions.
 * Another way - set new SecurityManager in runtime.
 */
public class ImmutableWithUsingOfSecurityManager {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.setSecurityManager(new SecurityManager());
        System.out.println(System.getSecurityManager());

        Immutable immutable = new Immutable("some value");
        Field valueField = Immutable.class.getDeclaredField("value");
        valueField.setAccessible(true);
        valueField.set(immutable, "new value");
    }

    private static final class Immutable {
        private final String value;

        private Immutable(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
