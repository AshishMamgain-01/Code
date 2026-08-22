package com.learn.coding.dsa.array;

public class Infinitearray {

	public static int searchInfiniteArray(int[] arr, int target) {

		int left = 0;
		int right = 1;

		// Expand range until target is within [left, right]
		while (arr[right] < target) {
			left = right;
			right = right * 2;
		}

		// Binary search within the found range
		while (left <= right) {

			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid;
			}

			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// Example sorted array (simulating infinite array)
		int[] arr = { 1, 3, 5, 7, 9, 13, 18, 21, 25, 30, 35, 40 };

		int target = 18;
		int index = searchInfiniteArray(arr, target);

		if (index != -1) {
			System.out.println("Target " + target + " found at index: " + index);
		} else {
			System.out.println("Target " + target + " not found.");
		}
	}
}
