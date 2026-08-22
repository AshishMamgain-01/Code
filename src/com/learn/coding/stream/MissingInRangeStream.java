package com.learn.coding.stream;

import java.util.*;
import java.util.stream.*;

public class MissingInRangeStream {
    public static List<Integer> findMissing(int[] arr) {
        // Step 1: Find min and max using streams
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        // Step 2: Put array elements into a Set for O(1) lookup
        Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());

        // Step 3: Generate range [min..max], filter out present elements
        return IntStream.rangeClosed(min, max)
                        .filter(i -> !set.contains(i))
                        .boxed()
                        .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 9};
        List<Integer> missing = findMissing(arr);

        // Print result in sorted order
        String output = missing.stream()
                               .map(String::valueOf)
                               .collect(Collectors.joining(" "));
        System.out.println(output); // Output: 3 5 6 7 8
    }
}
