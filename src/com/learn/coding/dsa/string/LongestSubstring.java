package com.learn.coding.dsa.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

	
	
	
	
	private static int lengthOfLongestSubstring(String string) {
		// Longest Substring Without Repeating Characters (Sliding Window)
		// abcabcbb
		int start = 0;
		int maxLength=0;
		Set<Character> set = new HashSet<Character>();
		for (int end = 0; end < string.length(); end++) {
			while (set.contains(string.charAt(end))) {
				set.remove(string.charAt(start));
				start++;
			}
			set.add(string.charAt(end));			
			maxLength= Math.max(maxLength, end-start+1);
		}
		return maxLength;
	}
	
	private static String longestSubstringUsingArrays(String input) {

	    if (input == null || input.isEmpty()) {
	        return "";
	    }

	    int[] lastSeen = new int[256];
	    Arrays.fill(lastSeen, -1);

	    int start = 0;
	    int maxStart = 0;
	    int maxLength = 0;

	    for (int end = 0; end < input.length(); end++) {

	        char ch = input.charAt(end);

	        int previousIndex = lastSeen[ch];

	        lastSeen[ch] = end;

	        if (previousIndex >= start) {
	            start = previousIndex + 1;
	        }

	        int currentLength = end - start + 1;

	        if (currentLength > maxLength) {
	            maxLength = currentLength;
	            maxStart = start;
	        }
	    }

	    return input.substring(maxStart, maxStart + maxLength);
	}

	private static String longestSubstringUsingHashSet(String input) {

		int start = 0;
		int maxLength = 0;
		int maxStart = 0;
		Set<Character> set = new HashSet<Character>();

		for (int end = 0; end < input.length(); end++) {

			while (set.contains(input.charAt(end))) {
				set.remove(input.charAt(start));
				start++;
			}

			set.add(input.charAt(end));

			if (end - start + 1 > maxLength) {
				maxLength = end - start + 1;
				maxStart = start;
			}
		}
		return input.substring(maxStart, maxStart + maxLength);

	}

	public static String longestSubstringUsingHashMap(String input) {

		int start = 0;
		int maxLength = 0;
		int maxStart =0;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int endIndex = 0; endIndex < input.length(); endIndex++) {

			char currentChar = input.charAt(endIndex);
			Integer previousIndex = map.get(currentChar);

			if (previousIndex != null && previousIndex > start) {
				start = previousIndex + 1;

			}

			map.put(currentChar, endIndex);

			if (endIndex - start + 1 > maxLength) {
				maxLength  = endIndex - start +1;
				maxStart = start;
			}
		}
		return input.substring(maxStart,maxStart+maxLength);
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

		System.out.println(lengthOfLongestSubstring("abcbcbc"));
		System.out.println(longestSubstringUsingArrays("abcbcbc"));
		System.out.println(longestSubstringUsingHashSet("abcbcbc"));
		System.out.println(longestSubstringUsingHashMap("abcbcbc"));
	}

}
