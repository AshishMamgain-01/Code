package com.learn.coding.dsa.array;

import java.util.Arrays;

public class RemoveDuplicate {

	public static int removeDuplicates(int[] arr, int n) {
		int idx = 1;
		int length = arr.length;

		for (int i = 1; i < length; i++) {
			if (arr[i] != arr[i - 1]) {
				arr[idx++] = arr[i];
			}
		}
		return idx;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 1, 2, 2, 3, 4, 4 };
		int n = arr.length;

		int newLength = removeDuplicates(arr, n);

		System.out.println("Unique count: " + newLength);
		System.out.println("Array after removing duplicates: " + Arrays.toString(Arrays.copyOf(arr, newLength)));
	}
}
