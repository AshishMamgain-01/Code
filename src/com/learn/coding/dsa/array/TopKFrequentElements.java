package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

	private static int[] topKFrequent(int[] nums, int k) {
		// TODO Auto-generated method stub

		// calculate frequencies
		HashMap<Integer, Integer> frequencyHashMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			frequencyHashMap.put(num, frequencyHashMap.getOrDefault(num, 0) + 1);
		}

		// Step 2: Min heap based on frequenc
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(
				(a, b) -> Integer.compare(frequencyHashMap.get(a), frequencyHashMap.get(b)));

		System.out.println("queue" + queue);

		// Step 3: Maintain only K elements
		for (Integer integer : frequencyHashMap.keySet()) {
			queue.offer(integer);

			if (queue.size() > k) {
				queue.poll();
			}

		}

		// Step 4: Build result
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = queue.poll();
		}

		return result;
	}

	public static int[] topKFrequentBucket(int[] nums, int k) {

		Map<Integer, Integer> frequencyMap = new HashMap<>();

		// Step 1: Count frequency
		for (int num : nums) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}

		// Step 2: Create buckets
		List<Integer>[] buckets = new List[nums.length + 1];

		for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {

			int number = entry.getKey();
			int frequency = entry.getValue();

			if (buckets[frequency] == null) {
				buckets[frequency] = new ArrayList<>();
			}

			buckets[frequency].add(number);
		}

		// Step 3: Read buckets from highest frequency
		int[] result = new int[k];
		int index = 0;

		for (int i = 0; i < buckets.length; i++) {
		    System.out.println("Frequency " + i + " -> " + buckets[i]);
		}
		
		for (int frequency = buckets.length - 1; frequency >= 0; frequency--) {

			if (buckets[frequency] != null) {

				for (int number : buckets[frequency]) {

					result[index++] = number;

					if (index == k) {
						return result;
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 1, 1, 1, 2, 2, 3 };
		int k = 2;

		int[] result = topKFrequent(nums, k);
		int[] result1 = topKFrequentBucket(nums, k);

		System.out.println(Arrays.toString(result1));
	}

}
