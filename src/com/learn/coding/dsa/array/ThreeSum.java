package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	private static List<List<Integer>> threeSum(int[] arr, int target) {
		

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(arr);

		for (int firstIndex = 0; firstIndex < arr.length - 2; firstIndex++) {

			if (firstIndex > 0 && arr[firstIndex] == arr[firstIndex - 1]) {
				continue;
			}

			int leftIndex = firstIndex + 1;
			int rightIndex = arr.length - 1;

			while (leftIndex < rightIndex) {
				int sum = arr[firstIndex] + arr[leftIndex] + arr[rightIndex];

				if (sum == target) {

					result.add(Arrays.asList(arr[firstIndex], arr[leftIndex], arr[rightIndex]));
					// skip duplicate left values
					while (leftIndex <= rightIndex && arr[leftIndex] == arr[leftIndex + 1]) {
						leftIndex++;
					}

					// skip duplicate right values
					while (leftIndex <= rightIndex && arr[rightIndex] == arr[rightIndex - 1]) {
						rightIndex--;
					}

					leftIndex++;
					rightIndex--;

				} else if (sum < target) {

					leftIndex++;
				} else {
					rightIndex--;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] arr = { -1, 0, 1, 2, -1, -4 };
		int target = 0;

		List<List<Integer>> triplets = threeSum(arr, target);

		System.out.println("Triplets with sum " + target + ":");
		for (List<Integer> triplet : triplets) {
			System.out.println(triplet);
		}
	}

}
