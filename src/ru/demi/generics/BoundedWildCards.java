package ru.demi.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BoundedWildCards {

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2));
        List<? extends Number> list = integers;
        Number number = list.get(0);
        // list.add(1); // compile error - incompatible types

        // The get and put principle:
        // use an extends wildcard when you only get values out of a structure
        // use a super wildcard when you only put values into a structure
        // doesn't use wildcard when you both get and put values
        // Example - copy method below
        ArrayList<Number> numbers = null;
        List<? super Integer> ints = numbers;
        // vice versa doesn't work
    }

    private static <T> void copy(Collection<? extends T> src, Collection<? super T> dst) {
        for (T item : src) {
            dst.add(item);
        }
    }

}
