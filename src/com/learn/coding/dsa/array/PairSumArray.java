package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;

public class PairSumArray {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 2, 3, 4, 5, 5 };
		int targetSum = 7;

		ArrayList<int[]> pairs = pairSum(arr, targetSum);

		System.out.println("Pairs with sum " + targetSum + ":");
		for (int[] pair : pairs) {
			System.out.println(Arrays.toString(pair));
		}
	}

	private static ArrayList<int[]> pairSum(int[] arr, int targetSum) {

		ArrayList<int[]> result = new ArrayList<int[]>();

		Arrays.sort(arr);

		int left = 0;
		int right = arr.length - 1;
		while (left < right) {

			int sum = arr[left] + arr[right];

			if (sum == targetSum) {
				int leftValue = arr[left];
				int rightValue = arr[right];

				if (leftValue == rightValue) {
					int count = right - left + 1;
					int pairs = (count * (count - 1)) / 2;
					for (int i = 0; i < pairs; i++) {
						result.add(new int[] { leftValue, rightValue });
					}
					break;
				} else {
					int leftCount = 0;
					int rightCount = 0;
					while (left <= right && arr[left] == leftValue) {
						leftCount++;
						left++;
					}

					while (left <= right && arr[right] == rightValue) {
						rightCount++;
						right--;
					}
					int pairs = leftCount * rightCount;
					for (int i = 0; i < pairs; i++) {
						result.add(new int[] { leftValue, rightValue });
					}
				}
			} else if (sum < targetSum) {
				left++;
			} else {
				right--;
			}
		}

		return result;
	}

}
