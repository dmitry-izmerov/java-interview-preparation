package ru.demi.java8.default_pitfall;

public interface I2 extends I1 {
    default void m() {
        System.out.println("I2");
    }
}
