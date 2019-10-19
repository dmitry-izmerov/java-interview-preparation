package ru.demi.java8.inherit_same_default_method_from_interfaces;

public interface Interface2 {
    default void print() {
        System.out.println("interface 2");
    }
}
