package com.learn.coding.dsa.array;


import java.util.Arrays;

public class Median {

	public static void main(String args[]) {

		int[] nums1 = { 1, 3, 5 };
		int[] nums2 = { 2, 4, 6 };
		System.out.println("Median: " + findMedianSortedArrays(nums1, nums2));
		System.out.println("Median: " + findMedianSortedArraysOptimal(nums1, nums2));
	}

	// naive approach
	private static double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int len1 = nums1.length;
		int len2 = nums2.length;

		int[] result = new int[len1 + len2];

		System.arraycopy(nums1, 0, result, 0, len1);
		System.arraycopy(nums2, 0, result, len1, len2);

		Arrays.sort(result);

		int len3 = result.length;
		if (result.length % 2 == 0) {
			return (result[len3 / 2] + result[len3 / 2 - 1]) / 2.0;
		} else {
			return (result[len3 / 2]);
		}
	}

	private static double findMedianSortedArraysOptimal(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length)
			return findMedianSortedArraysOptimal(nums2, nums1);// ensure nums1 is smaller

		int len1 = nums1.length;
		int len2 = nums2.length;

		int low = 0, high = len1;

		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = (len1 + len2 + 1) / 2 - partitionX;

			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
			int minRightX = (partitionX == len1) ? Integer.MAX_VALUE : nums1[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
			int minRightY = (partitionY == len2) ? Integer.MAX_VALUE : nums2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				if ((len1 + len2) % 2 == 0) {
					return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) {
				high = partitionX - 1;
			} else {
				low = partitionX + 1;
			}
		}
		throw new IllegalArgumentException("Input arrays not sorted.");
	}

}
