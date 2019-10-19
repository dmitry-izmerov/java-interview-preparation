package ru.demi.tasks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Function to check if a singly linked list is palindrome
 */
public class LinkedListPalindromeCheck {

    public static void main(String[] args) {
        LinkedList<Integer> linkedListNotPalindrome = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedList<Integer> linkedListPalindrome = new LinkedList<>(Arrays.asList(1, 2, 3, 2, 1));
        System.out.println(isLinkedListPalindrome(linkedListNotPalindrome));
        System.out.println(isLinkedListPalindrome(linkedListPalindrome));
    }

    private static boolean isLinkedListPalindrome(LinkedList<?> linkedList) {
        int size = linkedList.size();
        ListIterator<?> forward = linkedList.listIterator();
        ListIterator<?> backward = linkedList.listIterator(size);

        for (int i = 0; i < size / 2; i++) {
            if (!forward.next().equals(backward.previous())) {
                return false;
            }
        }

        return true;
    }
}
