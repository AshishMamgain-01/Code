package com.learn.coding.stream;

import java.util.*;
import java.util.stream.*;

public class PalindromeExample {

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {

        List<String> list =
                Arrays.asList("madam", "racecar", "apple", "level", "noon");

        List<String> palindromes = list.stream()
                .filter(PalindromeExample::isPalindrome)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());

        System.out.println(palindromes);
    }
}