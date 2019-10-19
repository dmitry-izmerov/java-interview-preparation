package ru.demi.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WildCards {

    public static void main(String[] args) {
        // what if I want to print objects from collection
        // previously you can do this in the following way
        List<Integer> collection = Arrays.asList(1, 2);
        printObjectsByOldWay(collection);

        // By simple way you can try the following approach:
        // printObjects(collection); // compile error here, see GenericsAreNotCovariant class

        // You can do this with wildcard:
        printObjectsByWildCard(collection);

        // wildcard is unknown type
        // therefore you cannot add not null object to collection of wildcard or collection of unknown
        Collection<?> c = new ArrayList<String>();
        // c.add(new Object()); // compile time error
        // c.add("some"); // compile time error

        // Since we don’t know what the element type of c stands for, we cannot add objects
        // to it. The add method takes arguments of type E, the element type of the collection.
        // When the actual type parameter is ?, it stands for some unknown type. Any parameter
        // we pass to add would have to be a subtype of this unknown type. Since we don’t know
        // what type that is, we cannot pass anything in. The sole exception is null, which is a
        // member of every type.
    }

    // using raw type
    static void printObjectsByOldWay(Collection collection) {
        for (Object item : collection) {
            System.out.println(item);
        }
    }

    static void printObjects(Collection<Object> collection) {
        for (Object item : collection) {
            System.out.println(item);
        }
    }

    static void printObjectsByWildCard(Collection<?> collection) {
        for (Object item : collection) {
            System.out.println(item);
        }
    }

    private static class Box<E extends Integer> {

    }
}
