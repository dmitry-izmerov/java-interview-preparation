package ru.demi.multithreading.synchronizers;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Lets two threads to wait for each other at a synchronization point a make an exchange
 * and continue working.
 */
public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread baryga = new Thread(new Baryga(exchanger, "2 AK-47 items"));
        Thread customer = new Thread(new Customer(exchanger, "4.000 $"));
        baryga.start();
        customer.start();
    }

    static class Baryga implements Runnable {
        private Exchanger<String> exchanger;
        private String product;

        Baryga(Exchanger<String> exchanger, String product) {
            this.exchanger = exchanger;
            this.product = product;
        }

        @Override
        public void run() {
            System.out.println("Baryga is going to a customer.");
            try {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(5) + 1L);
                String exchange = exchanger.exchange(product);
                System.out.println("Baryga have got money: " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Customer implements Runnable {

        private Exchanger<String> exchanger;
        private String money;

        Customer(Exchanger<String> exchanger, String product) {
            this.exchanger = exchanger;
            this.money = product;
        }

        @Override
        public void run() {
            System.out.println("Customer is going to a baryga.");
            try {
                Random random = new Random();
                TimeUnit.SECONDS.sleep(random.nextInt(5) + 1L);
                String exchange = exchanger.exchange(money);
                System.out.println("Customer have got product: " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
