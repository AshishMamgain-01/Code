package com.learn.coding.dsa.array;

import java.util.*;

public class NextGreaterElement {
	static ArrayList<Integer> nextGreaterElement(ArrayList<Integer> arr, int n) {
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();

		// Traverse from right to left
		for (int i = n - 1; i >= 0; i--) {
			// Remove elements smaller or equal to current
			while (!stack.isEmpty() && stack.peek() <= arr.get(i)) {
				stack.pop();
			}

			// If stack empty → no greater element
			result[i] = stack.isEmpty() ? -1 : stack.peek();

			// Push current element
			stack.push(arr.get(i));
		}

		ArrayList<Integer> resultList = new ArrayList<>();
		for (int val : result)
			resultList.add(val);
		return resultList;
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 8, 5, 2, 25));
		System.out.println(nextGreaterElement(arr, arr.size()));
		// Output: [8, 25, 25, 25, -1]
	}
}
