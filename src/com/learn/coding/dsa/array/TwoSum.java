package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TwoSum {

	public static void main(String[] args) {

	}

	public static ArrayList<ArrayList<Integer>> pairSum(ArrayList<Integer> arr, int s) {

		Collections.sort(arr);
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		int left = 0;
		int right = arr.size() - 1;

		while (left < right) {
			int sum = arr.get(left) + arr.get(right);
			if (sum == s) {
				int leftVal = arr.get(left);
				int rightVal = arr.get(right);

				if (leftVal == rightVal) {
					int count = right - left + 1;
					int pairs = count * (count - 1) / 2;

					for (int i = 0; i < pairs; i++) {
						result.add(new ArrayList<>(Arrays.asList(leftVal, rightVal)));
					}
					break;
				}

				int leftCount = 0;
				while (left <= right && arr.get(left) == leftVal) {
					leftCount++;
					left++;
				}

				int rightCount = 0;
				while (right >= left && arr.get(right) == rightVal) {
					rightCount++;
					right--;
				}

				int pairs = leftCount * rightCount;
				for (int i = 0; i < pairs; i++) {
					result.add(new ArrayList<>(Arrays.asList(leftVal, rightVal)));
				}
			}
			// sum is less move left pointer
			else if (sum < s) {
				left++;
			}
			// sum is more move right pointer
			else {
				right--;
			}
		}
		return result;
	}

}
