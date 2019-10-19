package ru.demi.same_method_from_interfaces;

public class Example implements A, B {

    @Override
    public String test() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
