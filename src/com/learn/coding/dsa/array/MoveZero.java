package com.learn.coding.dsa.array;

import java.util.Arrays;

public class MoveZero {
	/*
	 * Given an array, move all zeroes to the end while keeping the relative order
	 * of the non‑zero elements. Do this in‑place with minimal operations.
	 */
	public static void main(String[] args) {

		int arr[] = { 0, 1, 0, 13, 0, 2 };
		int pos = 0;
		moveZeroToEnd(arr, pos);
		System.out.println(Arrays.toString(arr));


		int arr1[] = { 0, 1, 0, 13, 0, 2 };

		moveZeroToEndTwoPointer(arr1);
		System.out.println(Arrays.toString(arr));

	}

	private static void moveZeroToEndTwoPointer(int[] arr) {
		int left =0;
		for(int right = 0; right < arr.length;right++){
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] =temp;
			left++;
		}
	}

	private static void moveZeroToEnd(int[] arr, int pos) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				arr[pos] = arr[i];
				pos++;
			}
		}

		while (pos < arr.length) {
			arr[pos] = 0;
			pos++;
		}
	}
}
