package ru.demi.multithreading.synchronizers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * Provides functionality similar to CyclicBarrier and CountDownLatch.
 * It is reusable.
 * The number of parties for synchronization is variable. (for CyclicBarrier is fixed)
 * Has an associated phase number, which starts at zero.
 * Has a three types of parties count: a registered parties count, an arrived partied count and an unarrived parties
 * count.
 * Lets you execute a phaser action what when all registered parties arrive at the phaser.
 *
 */
public class PhaserExample {

    public static void main(String[] args) {
        int numPhases = 2;
        MyPhaser phaser = new MyPhaser(numPhases);
        // ConcurrentSkipListSet doesn't work here. Why?
        // ConcurrentSkipListSet<Integer> nums = new ConcurrentSkipListSet<>();
        List<Integer> nums = Collections.synchronizedList(new ArrayList<>());
        // ConcurrentLinkedQueue<Integer> nums = new ConcurrentLinkedQueue<>();

        final int numThreads = 3;
        phaser.bulkRegister(numThreads + 1);
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new AdderTask("Task #" + i, phaser, 2, nums));
            thread.start();
        }

        while (!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
        }

        System.out.println("Sum = " + nums.stream().mapToInt(Integer::intValue).sum());
    }

    private static class AdderTask implements Runnable {
        private String name;
        private Phaser phaser;
        private int number;
        private Collection<Integer> nums;

        private AdderTask(String name, Phaser phaser, int number, Collection<Integer> nums) {
            this.name = name;
            this.phaser = phaser;
            this.number = number;
            this.nums = nums;
        }

        @Override
        public void run() {
            do {
                System.out.printf("%s added %d.%n", name, number);
                nums.add(number);
                phaser.arriveAndAwaitAdvance();
            } while (!phaser.isTerminated());
        }
    }

    private static class MyPhaser extends Phaser {
        private int numPhases;

        private MyPhaser(int numPhases) {
            this.numPhases = numPhases;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.printf("Phase: %d, Parties: %d, Arrived: %d.%n", phase, this.getArrivedParties(), registeredParties);

            boolean isTerminated = false;
            if (phase >= numPhases - 1 || registeredParties == 0) {
                isTerminated = true;
            }
            return isTerminated;
        }
    }
}
