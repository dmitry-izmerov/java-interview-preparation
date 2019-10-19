package ru.demi.java8.inherit_same_default_method_from_interfaces;

public class Example implements Interface1, Interface2 {

    // without explicit declaration we will have next error:
    // Error:(3, 8) java: class Example inherits unrelated defaults for print() from types Interface1 and Interface2
    public void print() {
        Interface2.super.print(); // how to refer to specific default method
    }

    public static void main(String[] args) {
        Example example = new Example();
        example.print();
    }
}
