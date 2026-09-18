package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSpecialIntegers {
	/*
	 * 
	 * You are given an integer array nums.
	 * 
	 * An integer x is called special if:
	 * 
	 * x appears at least three times in nums. All occurrences of x are equally
	 * spaced in nums. In other words, if all occurrences of x are at indices i1 <
	 * i2 < ... < im, then i2 - i1 = i3 - i2 = ... = im - im-1. Return the number of
	 * distinct special integers in nums.
	 */

	public static void main(String[] args) {
		int[] nums1 = { 1, 8, 1, 5, 1, 5, 8, 5 };
		int[] nums2 = { 8, 8, 8, 8 };
		int[] nums3 = { 1, 2, 3, 4, 5 };
		System.out.println(countSpecialIntegers(nums1));
		System.out.println(countSpecialIntegers(nums2));
		System.out.println(countSpecialIntegers(nums3));
	}

	public static int countSpecialIntegers(int[] nums) {

		Map<Integer, List<Integer>> map = new HashMap<>();
		int specialNumberCount = 0;
		for (int i = 0; i < nums.length; i++) {
			map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
		}

		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> list = entry.getValue();
			int size = list.size();
			if (size >= 3) {
				int diff = list.get(1) - list.get(0);
				boolean allEquals = true;
				for (int i = 1; i < size - 1; i++) {
					if (list.get(i + 1) - list.get(i) != diff) {
						allEquals = false;
						break;
					}
				}
				if (allEquals) {
					specialNumberCount++;
				}
			}
		}
		return specialNumberCount;
	}
}
