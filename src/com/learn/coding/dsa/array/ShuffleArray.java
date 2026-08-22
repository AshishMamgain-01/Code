package com.learn.coding.dsa.array;

import java.util.Arrays;

public class ShuffleArray {

	public int[] shuffle(int[] nums, int n) {
		int[] ans = new int[2 * n];
		System.out.println("######"+ans.length);
		int index = 0;

		for (int i = 0; i < n; i++) {
			ans[index++] = nums[i];
			ans[index++] = nums[i + n];
		}
		return ans;
	}

	public static void main(String[] args) {
		ShuffleArray sol = new ShuffleArray();

		// Example 1
		int[] nums1 = { 2, 5, 1, 3, 4, 7 };
		int n1 = 3;
		int[] result1 = sol.shuffle(nums1, n1);
		System.out.println(Arrays.toString(result1));

		// Example 2
		int[] nums2 = { 1, 2, 3, 4, 4, 3, 2, 1 };
		int n2 = 4;
		int[] result2 = sol.shuffle(nums2, n2);
		System.out.println(Arrays.toString(result2));

		// Example 3
		int[] nums3 = { 1, 1, 2, 2 };
		int n3 = 2;
		int[] result3 = sol.shuffle(nums3, n3);
		System.out.println(Arrays.toString(result3));
	}

}
