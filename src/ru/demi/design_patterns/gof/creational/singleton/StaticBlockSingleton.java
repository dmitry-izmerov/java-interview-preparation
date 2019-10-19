package ru.demi.design_patterns.gof.creational.singleton;

public class StaticBlockSingleton {
    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {}

    // this implementation will allow you to handle exceptional situations
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StaticBlockSingleton getInstance() {
        return instance;
    }
}
