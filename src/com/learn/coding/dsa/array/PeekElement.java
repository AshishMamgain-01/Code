package com.learn.coding.dsa.array;

public class PeekElement {

	public static void main(String[] args) {

		int[] num = { 1, 2, 3, 4, 5 };
		System.out.println(findPeekElement(num));
		System.out.println(findPeekElementnaive(num));

	}

	// naive
	private static int findPeekElementnaive(int[] num) {

		int n = num.length;
		// check first

		if (n == 1 || num[0] >= num[1])
			return 0;

		for (int i = 1; i < num.length - 1; i++) {
			if (num[i] >= num[i - 1] && num[i] >= num[i + 1]) {
				return i;
			}

		}

		return n - 1;

	}

	// optimized
	private static int findPeekElement(int[] num) {

		int left = 0;
		int right = num.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;
			if (num[mid] < num[mid + 1]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}

}
