package ru.demi.multithreading.synchronizers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A semaphore is used to control the number of threads that can access a resource.
 */
public class SemaphoreExample {

    public static void main(String[] args) {
        // Semaphore semaphore = new Semaphore(maxPermits);
        // or new Semaphore(maxPermits, fair flag)
        Toilet toilet = new Toilet(3);
        int howLong = 5;
        PersonAction vasya = new PersonAction("Vasya", howLong, toilet);
        PersonAction fedor = new PersonAction("Fedor", howLong, toilet);
        PersonAction peter = new PersonAction("Peter", howLong, toilet);
        PersonAction mary = new PersonAction("Mary", howLong, toilet);
        PersonAction erick = new PersonAction("Erick", howLong, toilet);
        PersonAction john = new PersonAction("John", howLong, toilet);
        PersonAction pedro = new PersonAction("Pedro", howLong, toilet);
        List<PersonAction> actions = Arrays.asList(vasya, fedor, peter, mary, erick, john, pedro);

        ExecutorService threadPool = Executors.newFixedThreadPool(actions.size());

        actions.forEach(threadPool::submit);

        threadPool.shutdown();
    }

    static class Toilet {
        private Semaphore places;

        Toilet(int numPlaces) {
            places = new Semaphore(numPlaces);
        }

        void takePlace() {
            try {
                places.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void leave() {
            places.release();
        }
    }

    static class PersonAction implements Runnable {

        private String name;
        private int howLong;
        private Toilet toilet;

        PersonAction(String name, int howLong, Toilet toilet) {
            this.name = name;
            this.howLong = howLong;
            this.toilet = toilet;
        }

        @Override
        public void run() {
            toilet.takePlace();
            System.out.printf("%s took a place in the toilet.%n", name);

            System.out.printf("%s does some actions in there.%n", name);
            try {
                TimeUnit.SECONDS.sleep(howLong);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            toilet.leave();
            System.out.printf("%s left the toilet.%n", name);
        }
    }
}
