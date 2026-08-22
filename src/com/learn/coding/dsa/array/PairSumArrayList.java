package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Find all possible pairs
public class PairSumArrayList {

	private static ArrayList<ArrayList<Integer>> pairSum(ArrayList<Integer> arr, int targetSum) {
		// TODO Auto-generated method stub

		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		Collections.sort(arr);

		int left = 0;
		int right = arr.size() - 1;

		while (left <= right) {
			int sum = arr.get(left) + arr.get(right);

			if (sum == targetSum) {

				int leftValue = arr.get(left);
				int rightValue = arr.get(right);

				if (leftValue == rightValue) {
					int count = right - left + 1;
					int pairs = (count * (count - 1)) / 2; // nc2

					for (int i = 0; i < pairs; i++) {
						ArrayList<Integer> pair = new ArrayList<Integer>();
						pair.add(leftValue);
						pair.add(rightValue);
						result.add(pair);
					}
					break;

				}

				else {
					int leftCount = 0;
					int rightCount = 0;

					while (left <= right && arr.get(left) == leftValue) {
						leftCount++;
						left++;
					}

					while (left <= right && arr.get(right) == rightValue) {
						rightCount++;
						right--;
					}
					// int pairs = Math.min(leftCount, rightCount);
					int pairs = leftCount * rightCount;

					for (int i = 0; i < pairs; i++) {
						ArrayList<Integer> par = new ArrayList<Integer>();
						par.add(leftValue);
						par.add(rightValue);
						result.add(par);
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

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 5));
		int targetSum = 7;

		ArrayList<ArrayList<Integer>> pairs = pairSum(arr, targetSum);

		System.out.println("Pairs with sum " + targetSum + ":");
		for (ArrayList<Integer> pair : pairs) {
			System.out.println(pair);
		}
	}

}
