package ru.demi.multithreading.synchronizers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomSemaphoreTest {

    public static void main(String[] args) {
//        final Semaphore semaphore = new SemaphoreByQueue(2);
        final Semaphore semaphore = new SemaphoreByPrimitives(2);
        int numOfThreads = 5;
        List<Thread> tasks = IntStream.rangeClosed(1, numOfThreads)
            .mapToObj(i -> new Thread(new SemaphoreTask(i, semaphore)))
            .collect(Collectors.toList());

        tasks.forEach(Thread::start);
    }

    private static class SemaphoreTask implements Runnable {
        private int id;
        private Semaphore semaphore;

        private static final int TIMEOUT = 5;

        SemaphoreTask(int id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.printf("Thread #%d is acquiring the semaphore.%n", id);
            semaphore.acquire();

            System.out.printf("Thread #%d is doing work.%n", id);
            try {
                TimeUnit.SECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.printf("Thread #%d is releasing the semaphore.%n", id);
            semaphore.release();
        }
    }
}
