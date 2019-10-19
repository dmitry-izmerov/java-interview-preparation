package ru.demi.multithreading.synchronizers;

import java.util.Random;
import java.util.concurrent.*;

/**
 * A barrier makes a group of threads meet at a barrier point.
 * One of use cases - to divide one big task to multiple sub tasks and then
 * collect results from them at a barrier.
 * Oscar: "To a barrier mother fuckers!"
 */
public class CyclicBarrierExample {

    public static void main(String[] args) {
        final int numWorkers = 5;
        Runnable barrierAction = () -> System.out.println("All arrived at the barrier!");
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(numWorkers, barrierAction);
        ExecutorService threadPool = Executors.newFixedThreadPool(numWorkers);
        for (int i = 0; i < numWorkers; i++) {
            threadPool.submit(new Worker(i, cyclicBarrier));
        }
        threadPool.shutdown();
    }

    static class Worker implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;
        private Random random = new Random();

        private static final int MAX_WORK_TIME = 10;

        Worker(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            int workTime = random.nextInt(MAX_WORK_TIME);
            System.out.printf("Worker #%d is going to work for %d seconds.%n", id, workTime);
            try {
                TimeUnit.SECONDS.sleep(workTime);
                System.out.printf("Worker #%d finished and is waiting at the barrier.%n", id);
                cyclicBarrier.await();
                System.out.printf("Worker #%d passed the barrier.%n", id);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
