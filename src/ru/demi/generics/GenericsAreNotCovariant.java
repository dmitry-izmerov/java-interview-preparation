package ru.demi.generics;

import java.util.ArrayList;
import java.util.Collection;

public class GenericsAreNotCovariant {
    private static class A {}
    private static class B extends A {}

    public static void main(String[] args) {
        ArrayList<B> listB = new ArrayList<>();
        Collection<B> collectionB = listB; // you can do this
        // but not this because of generics are not covariant, they don't keep inheritance of their types
        // Collection<A> collectionA = collectionB;
        // though:
        A linkA = new B();
    }
}
