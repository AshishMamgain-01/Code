package com.learn.coding.dsa.array;

import java.util.HashSet;

public class LongestConsecutiveSequence {

	private static int longestConsecutive(int[] nums) {
		HashSet<Integer> set = new HashSet<Integer>();

		for (Integer num : nums) {
			set.add(num);
		}

		int maxLength = 0;
		for (Integer num : set) {
			// FIRST NODE
			if (!set.contains(num - 1)) {
				int currentNumber = num;
				int currentLength = 1;
				while (set.contains(currentNumber + 1)) {
					currentNumber++;
					currentLength++;
				}
				maxLength = Math.max(maxLength, currentLength);
			}
		}
		return maxLength;
	}

	public static void main(String[] args) {
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		System.out.println(longestConsecutive(nums));
	}
}
