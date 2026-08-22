package com.learn.coding.dsa.array;

public class SearchRotatedArray {

	/*
	 * Search in Rotated Sorted Array
	 * 
	 * [4,5,6,7,0,1,2] target = 0
	 * 
	 */

	public static int searchRotatedArray(int arr[], int target) {

		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid;
			}

			// left side sorted
			if (arr[left] < arr[mid]) {
				if (target >= arr[left] && target < arr[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			// right side
			else {
				if (target > arr[mid] && target <= arr[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
		int target = 2;

		System.out.println(searchRotatedArray(arr, target));
	}
}
