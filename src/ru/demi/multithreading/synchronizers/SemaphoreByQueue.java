package ru.demi.multithreading.synchronizers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SemaphoreByQueue implements Semaphore {
    private final int size;
    private BlockingQueue<Object> queue;
    private final Object empty = new Object();

    public SemaphoreByQueue(int size) {
        this.size = size;
        this.queue = new ArrayBlockingQueue<>(size);
    }

    public void acquire() {
        try {
            queue.put(empty);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void release() {
        if (queue.remainingCapacity() == size) {
            throw new IllegalStateException("You cannot release free semaphore.");
        }

        try {
            queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
