package ru.demi.java8.inherit_same_default_method_from_interfaces;

public interface Interface1 {
    default void print() {
        System.out.println("interface 1");
    }
}
