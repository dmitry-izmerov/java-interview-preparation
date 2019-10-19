package ru.demi.design_patterns.gof.creational.singleton;

public enum EnumSingleton {
    INSTANCE;

    public void someMethod() {
        System.out.println("print something");
    }
}
