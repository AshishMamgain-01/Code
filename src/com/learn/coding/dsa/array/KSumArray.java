package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSumArray {

	public static void main(String[] args) {
		int[] arr = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		int k = 4;
		List<List<Integer>> triplets = KSum(arr, target, k);

		System.out.println("Triplets with sum " + target + ":");
		for (List<Integer> triplet : triplets) {
			System.out.println(triplet);
		}

	}

	private static List<List<Integer>> KSum(int[] arr, int target, int k) {
		// TODO Auto-generated method stub

		Arrays.sort(arr);
		return kSum(arr, target, 0, k);
	}

	private static List<List<Integer>> kSum(int[] arr, int target, int start, int k) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (k == 2) {// base case
			int left = start;
			int right = arr.length - 1;

			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum == target) {
					List<Integer> al = new ArrayList<Integer>();
					al.add(arr[left]);
					al.add(arr[right]);
					result.add(al);

					// skip duplicate on left
					while (left < right && arr[left] == arr[left + 1]) {
						left++;
					}
					while (left < right && arr[right] == arr[right - 1]) {
						right--;
					}
					left++;
					right--;
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		} else {
			// recursive case
			for (int i = start; i < arr.length - k + 1; i++) {
				// skip duplicate
				if (i > start && arr[i] == arr[i - 1])
					continue;
				for (List<Integer> subset : kSum(arr, target - arr[i], i + 1, k - 1)) {
					List<Integer> combo = new ArrayList<Integer>();
					combo.add(arr[i]);
					combo.addAll(subset);
					result.add(combo);
				}
			}
		}
		return result;
	}
}
