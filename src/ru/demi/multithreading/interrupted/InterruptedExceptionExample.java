package ru.demi.multithreading.interrupted;

import java.util.concurrent.TimeUnit;

public class InterruptedExceptionExample {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(2);

        t.interrupt();
    }
}
