package ru.demi.multithreading.synchronizers;

public class SemaphoreByPrimitives implements Semaphore {
    private final int size;
    private volatile int counter = 0;
    private final Object lock = new Object();

    SemaphoreByPrimitives(int size) {
        this.size = size;
    }

    @Override
    public void acquire() {
        synchronized (lock) {
            try {
                while (counter >= size) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ++counter;
        }
    }

    @Override
    public void release() {
        synchronized (lock) {
            if (counter == 0) {
                throw new IllegalStateException("You cannot release free semaphore.");
            }
            --counter;
            lock.notify();
        }
    }
}
