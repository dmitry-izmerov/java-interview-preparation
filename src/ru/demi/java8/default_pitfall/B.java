package ru.demi.java8.default_pitfall;

class B extends A implements I2 {
    void callSuperM() {
        super.m();
    }
}
