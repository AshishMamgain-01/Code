package com.learn.coding.dsa.array;

import java.util.*;

public class LongestCompleteString {

	public static String longestCompleteString(String[] A) {
		Set<String> set = new HashSet<>(Arrays.asList(A));
		String result = "None";

		for (String s : A) {
			boolean isComplete = true;

			// Check all prefixes
			for (int i = 1; i <= s.length(); i++) {
				String prefix = s.substring(0, i);
				if (!set.contains(prefix)) {
					isComplete = false;
					break;
				}
			}

			if (isComplete) {
				if (result.equals("None") || s.length() > result.length()
						|| (s.length() == result.length() && s.compareTo(result) < 0)) {
					result = s;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		String[] A = { "n", "ni", "nin", "ninj", "ninja", "niro" };
		System.out.println("Longest complete string: " + longestCompleteString(A));
	}
}