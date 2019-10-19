package ru.demi.design_patterns.gof.creational.singleton;

import java.util.Objects;

public class ThreadSafeSingletonWithDoubleCheck {
    // volatile here because of details of the initialization
    private static volatile ThreadSafeSingletonWithDoubleCheck instance;

    private ThreadSafeSingletonWithDoubleCheck() {}

    // you can do this method just synchronized but it reduces the performance
    public static ThreadSafeSingletonWithDoubleCheck getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (ThreadSafeSingletonWithDoubleCheck.class) {
                if (Objects.isNull(instance)) {
                    instance = new ThreadSafeSingletonWithDoubleCheck();
                }
            }
        }
        return instance;
    }
}
