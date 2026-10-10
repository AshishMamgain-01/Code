package com.learn.coding.dsa.string;


import java.util.*;

public class Subsequences {
    public static void main(String[] args) {
        String str = "abc";
        List<String> result = new ArrayList<>();
        generateSubsequences(str, 0, "", result);

        System.out.println("All subsequences: " + result);
    }

    private static void generateSubsequences(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            result.add(current);
            return;
        }

        // Include current character
        generateSubsequences(str, index + 1, current + str.charAt(index), result);

        // Exclude current character
        generateSubsequences(str, index + 1, current, result);
    }
}
