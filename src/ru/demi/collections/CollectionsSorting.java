package ru.demi.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class CollectionsSorting {

    public static void main(String[] args) {
        List<String> values = Arrays.asList("3", "1", "2");
        Set<String> set = new HashSet<>(values);
        List<String> list = new ArrayList<>(values);
        List<String> linkedList = new LinkedList<>(values);
        Map<String, String> map = new HashMap<>();
        map.put("5", "5");
        map.put("3", "8");
        map.put("2", "3");
        List<String> copyList = new CopyOnWriteArrayList<>(values);
        Set<String> copySet = new CopyOnWriteArraySet<>(values);

        System.out.println("====== HashSet sorting ======");
        TreeSet<String> treeSet = new TreeSet<>(set);
        // or
//        TreeSet<String> treeSet = new TreeSet<>(String::compareTo);
//        treeSet.addAll(set);
        // because there is no a constructor with Collection and Comparator parameters
        System.out.println(treeSet);
        ArrayList<String> listForSet = new ArrayList<>(set);
        Collections.sort(listForSet);
        System.out.println(listForSet);
        System.out.println(set.stream()
                .sorted(String::compareTo)
                .collect(Collectors.joining(", ")));

        System.out.println("====== ArrayList sorting ======");
        Collections.sort(list);
        System.out.println(list);
        System.out.println(list.stream()
                .sorted()
                .collect(Collectors.joining(", ")));

        System.out.println("====== LinkedList sorting ======");
        Collections.sort(linkedList);
        System.out.println(linkedList);
        System.out.println(linkedList.stream()
                .sorted()
                .collect(Collectors.joining(", ")));

        System.out.println("====== HashMap keys sorting ======");
        System.out.println(map.keySet().stream().sorted().collect(Collectors.joining(", ")));

        System.out.println("====== HashMap values sorting ======");
        System.out.println(map.values().stream().sorted().collect(Collectors.joining(", ")));

        System.out.println("====== CopyOnWriteArrayList sorting ======");
        System.out.println(copyList.stream().sorted().collect(Collectors.joining(", ")));

        System.out.println("====== CopyOnWriteArraySet sorting ======");
        System.out.println(copySet.stream().sorted().collect(Collectors.joining(", ")));
    }
}
