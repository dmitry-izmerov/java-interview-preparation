package ru.demi.tasks;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Program for n’th node from the end of a LinkedList
 */
public class NthNodeFromEndOfLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        int nth = 2;
        System.out.println(linkedList.listIterator(linkedList.size() - nth).next());
    }
}
