package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class NextSmallerNumber {

	static ArrayList<Integer> nextSmallerElement(ArrayList<Integer> arr, int n) {
		// Write your code here.

		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = n - 1; i >= 0; i--) {

			//this line is to remove number which are greater because we can't return them need smaller number
			while (!stack.isEmpty() && stack.peek() >= arr.get(i)) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				result[i] = -1;
			} else {
				result[i] = stack.peek();
			}

			stack.push(arr.get(i));
		}
		ArrayList<Integer> resultList = new ArrayList<>();

		for (int value : result) {
			resultList.add(value);
		}

		return resultList;
	}

	public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 8, 5, 2, 25));
        System.out.println(nextSmallerElement(arr, arr.size())); 
	}
}
