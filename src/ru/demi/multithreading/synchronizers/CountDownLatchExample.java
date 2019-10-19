package ru.demi.multithreading.synchronizers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * A point synchronization between two groups of threads - one who waits and another who works.
 * It's one time object and cannot be reset and reused.
 * Use case - a service should not start until all service that it depends on have started.
 */
public class CountDownLatchExample {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final int numWorkers = 2;

        for (int i = 0; i < numWorkers; i++) {
            Thread thread = new Thread(new Worker(i, countDownLatch));
            thread.start();
        }

        for (int i = 0; i < numWorkers; i++) {
            Thread thread = new Thread(new WorkerAfterWait(i, countDownLatch));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private int id;
        private CountDownLatch latch;
        private Random random = new Random();

        private static final int MAX_WORK_TIME = 10;

        Worker(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            int workTime = random.nextInt(MAX_WORK_TIME);
            System.out.printf("Worker #%d is going to work for %d seconds.%n", id, workTime);
            try {
                TimeUnit.SECONDS.sleep(workTime);
                System.out.printf("Worker #%d finished.%n", id);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class WorkerAfterWait implements Runnable {
        private int id;
        private CountDownLatch latch;

        WorkerAfterWait(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.printf("WorkerAfterWait #%d is waiting.%n", id);
            try {
                latch.await();
                System.out.printf("WorkerAfterWait #%d does some another work.%n", id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
