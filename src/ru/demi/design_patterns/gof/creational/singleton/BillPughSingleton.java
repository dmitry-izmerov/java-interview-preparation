package ru.demi.design_patterns.gof.creational.singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("BillPughSingleton constructor");
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
