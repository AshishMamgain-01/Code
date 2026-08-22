package com.learn.coding.dsa.array;

public class MaximumSubArraySum {

	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		getMaxSubarray(arr);
		getMaxSumSubarray(arr);
	}

	private static void getMaxSubarray(int[] arr) {

		int currentSum = arr[0];
		int maxSum = arr[0];

		int start = 0;
		int end = 0;
		int temp = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > currentSum + arr[i]) {
				currentSum = arr[i];
				temp++;
			} else {
				currentSum = currentSum + arr[i];
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
				start = temp;
				end = i;
			}
		}

		System.out.println("MaxSum:" + maxSum + " start : " + start + " end: " + end);
		System.out.print("Subarray : [");
		for (int i = start; i <= end; i++) {
			System.out.print("" + arr[i] + (i < end ? ", " : ""));
		}
		System.out.print("]");

	}

	private static int getMaxSumSubarray(int[] arr) {
		int maxSum = arr[0];
		int currentSum = arr[0];

		for (Integer item : arr) {
			currentSum = Math.max(item, currentSum + item);
			maxSum = Math.max(maxSum, currentSum);
		}
		return maxSum;
	}

}
