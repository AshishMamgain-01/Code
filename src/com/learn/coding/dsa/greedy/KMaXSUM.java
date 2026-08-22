package com.learn.coding.dsa.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class KMaXSUM {
	// K maximum sum combinations from two arrays
	/*
	 * Input: a[] = [3, 2], b[] = [1, 4], k = 2 Output: [7, 6] Explanation: Possible
	 * sums: 3 + 1 = 4, 3 + 4 = 7, 2 + 1 = 3, 2 + 4 = 6, Top 2 sums are 7 and 6.
	 * 
	 * Input: a[] = [1, 4, 2, 3], b[] = [2, 5, 1, 6], k = 3 Output: [10, 9, 9]
	 * Explanation: The top 3 maximum possible sums are : 4 + 6 = 10, 3 + 6 = 9, and
	 * 4 + 5 = 9.
	 */
	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 3 };
		int[] b = { 2, 5, 1, 6 };
		int k = 3;

		ArrayList<Integer> res = topKSumPairs(a, b, k);

		for (int sum : res) {
			System.out.print(sum + " ");
		}
	}

	private static ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
		// TODO Auto-generated method stub

		int n = a.length;
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				int curr_sum = a[i] + b[j];

				if (minHeap.size() < k) {
					minHeap.offer(curr_sum);
				}

				if (curr_sum > minHeap.peek()) {
					minHeap.poll();
					minHeap.offer(curr_sum);
				}
			}
		}

		ArrayList<Integer> res = new ArrayList<>();
		while (!minHeap.isEmpty()) {
			res.add(minHeap.poll());
		}

		Collections.reverse(res);

		return res;
	}

}
