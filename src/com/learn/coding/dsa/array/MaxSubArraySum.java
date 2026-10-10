package com.learn.coding.dsa.array;

public class MaxSubArraySum {

	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		maxSubArray(arr);// tuf easy

		int[] arr1 = { 1, 2, -1, 2, -3, 2, -5 };
		kMaxSubArray(arr1, 2);

	}

	// Function to find maximum sum of subarrays
	public static void maxSubArray(int[] nums) {

		int sum = 0;
		int maxSum = Integer.MIN_VALUE;

		int startIndex = 0;
		int endIndex = 0;
		int tempIndex = 0;

		// Iterate through the array
		for (int i = 0; i < nums.length; i++) {

			// Add current element to the sum
			sum = sum + nums[i];

			// Update maxSum if current sum is greater
			if (sum > maxSum) {
				maxSum = sum;
				startIndex = tempIndex;
				endIndex = i;
			}

			// Reset sum to 0 if it becomes negative
			if (sum < 0) {
				sum = 0;
				tempIndex = i + 1; // start of next segment
			}
		}

		// Return the maximum subarray sum found
		System.out.print("Sum is : " + maxSum + "\nSubarray : [");
		for (int i = startIndex; i <= endIndex; i++) {
			System.out.print("" + nums[i] + (i < endIndex ? ", " : ""));
		}
		System.out.print("]\n");
		System.out.print("_______________________\n");
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

	// Find k non-overlapping subarrays with max sum
	public static void kMaxSubArray(int[] nums, int k) {
		
		//Goal : Find the top‑K maximum sum subarrays (non‑overlapping).
		
		for (int count = 1; count <= k; count++) {
			
			int sum = 0;
			int maxSum = Integer.MIN_VALUE;
			
			int startIndex = 0;
			int endIndex = 0;
			int tempIndex = 0; 

			// Kadane’s to find one max subarray
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
				if (sum > maxSum) {
					maxSum = sum;
					startIndex = tempIndex;
					endIndex = i;
				}
				if (sum < 0) {
					sum = 0;
					tempIndex = i + 1;
				}
			}

			// Print this subarray
			System.out.print("Subarray " + count + " (Sum=" + maxSum + "): [");
			for (int i = startIndex; i <= endIndex; i++) {
				System.out.print(nums[i] + (i < endIndex ? ", " : ""));
				nums[i] = Integer.MIN_VALUE; // mark as used
			}
			System.out.println("]");
		}
	}

}
