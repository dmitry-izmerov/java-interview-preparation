package ru.demi.java8.default_pitfall;

public class Example {
    public static void main(String[] args) {
        B b = new B();
        b.m();
        b.callM();
        b.callSuperM();

        System.out.println("--------------");

        A a = new B();
        a.m();
        a.callM();
    }
}
