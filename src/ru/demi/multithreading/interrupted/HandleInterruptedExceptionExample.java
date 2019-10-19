package ru.demi.multithreading.interrupted;

import java.util.concurrent.TimeUnit;

public class HandleInterruptedExceptionExample {

    private static class TestThread extends Thread {
        private final int maxNum;
        private int counter = 0;

        private TestThread(int maxNum) {
            this.maxNum = maxNum;
        }

        @Override
        public void run() {
            interrupt();
            System.out.println(isInterrupted());
            while (counter < maxNum) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(++counter);
                } catch (InterruptedException e) {
                    // catch exception and postpone handling of interruption because it is important task
                }
            }
            // Interruption policies:
            // or to propagate InterruptedException further
            // throw new InterruptedException(e);
            // or to restore interruption status
            System.out.println(isInterrupted());
            interrupt();
            System.out.println(isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread t = new TestThread(5);
        t.start();

        TimeUnit.SECONDS.sleep(2);
        t.interrupt();
        t.join();
    }
}
