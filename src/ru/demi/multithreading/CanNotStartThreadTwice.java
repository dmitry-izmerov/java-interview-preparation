package ru.demi.multithreading;

public class CanNotStartThreadTwice {

    public static void main(String[] args) {
        Thread t = new Thread(System.out::println);
        t.start();
        t.start();
    }
}
