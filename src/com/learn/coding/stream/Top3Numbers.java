package com.learn.coding.stream;

import java.util.*;
import java.util.stream.*;

public class Top3Numbers {
    public static void main(String[] args) {
        int[] arr = {10, 5, 20, 8, 3, 15, 25, 2, 18};

        // Top 3 maximum numbers
        List<Integer> top3Max = Arrays.stream(arr)
                .boxed() // convert IntStream → Stream<Integer>
                .sorted(Comparator.reverseOrder()) // sort descending
                .limit(3)
                .collect(Collectors.toList());

        // Top 3 minimum numbers
        List<Integer> top3Min = Arrays.stream(arr)
                .boxed()// convert IntStream → Stream<Integer>
                .sorted() // sort ascending
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 Max: " + top3Max);
        System.out.println("Top 3 Min: " + top3Min);
    }
}
