package com.learn.coding.dsa.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {


    private static int lengthOfLongestSubstring(String string) {
        // Longest Substring Without Repeating Characters (Sliding Window)
        int left = 0;
        int maxLength = 0;
        Set<Character> set = new HashSet<Character>();
        for (int right = 0; right < string.length(); right++) {
            Character ch = string.charAt(right);
            while (set.contains(ch)) {
                set.remove(string.charAt(left));
                left++;
            }
            set.add(ch);
            int currentLength = right - left + 1;
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    private static String longestSubstringUsingArrays(String input) {

        if (input == null || input.isEmpty()) {
            return "";
        }

        int[] lastSeen = new int[256];
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int startIndex = 0;
        int maxLength = 0;

        for (int right = 0; right < input.length(); right++) {

            char ch = input.charAt(right);

            int previousIndexOfChar = lastSeen[ch];

			if (previousIndexOfChar >= left) {
				left = previousIndexOfChar + 1;
			}

            lastSeen[ch] = right;

            int currentLength = right - left + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                startIndex = left;
            }
        }

        return input.substring(startIndex, startIndex + maxLength);
    }

    public static String longestSubstringUsingHashMap(String input) {

        int left = 0;
        int maxLength = 0;
        int startIndex = 0;

        HashMap<Character, Integer> seen = new HashMap<Character, Integer>();

        for (int right = 0; right < input.length(); right++) {

            Character ch = input.charAt(right);
            Integer previousIndex = seen.get(ch);

            if (previousIndex != null && previousIndex > left) {
                left = previousIndex + 1;
            }

            seen.put(ch, right);

            int currentLength = right - left + 1;

            if (currentLength > maxLength) {
                maxLength = currentLength;
                startIndex = left;
            }
        }
        return input.substring(startIndex, startIndex + maxLength);
    }

    public static void main(String[] args) {
		
		/*
		
		HashSet
		-------
		duplicate found
		      ↓
		remove one by one
		      ↓
		start++
		      ↓
		start++
		      ↓
		duplicate removed


		HashMap
		-------
		duplicate found
		      ↓
		get previous index
		      ↓
		start = previousIndex + 1
		      ↓
		direct jump
		
		*/

        System.out.println(lengthOfLongestSubstring("abcdbcbc"));
        System.out.println(longestSubstringUsingArrays("abcdbcbc"));
        System.out.println(longestSubstringUsingHashSet("abcdbcbc"));
        System.out.println(longestSubstringUsingHashMap("abcdbcbc"));
    }

}
