package ru.demi.multithreading.synchronizers;

public interface Semaphore {
    void acquire();
    void release();
}
