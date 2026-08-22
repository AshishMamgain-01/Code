package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NonRepeatingNumber {

	public static ArrayList<Integer> findNonRepeating(ArrayList<Integer> list) {

		ArrayList<Integer> result = new ArrayList<>();

		Map<Integer, Integer> freq = new LinkedHashMap<>();
		for (int num : list) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}

		
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			if (entry.getValue() == 1) {
				result.add(entry.getKey());
			}
		}
		return result;
	}

	public static ArrayList<Integer> findNonRepeatingStream(ArrayList<Integer> list) {
		List<Integer> a = list.stream()
				.collect(Collectors.groupingBy(num -> num, LinkedHashMap::new, Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue() == 1)
				.map(e -> e.getKey()).collect(Collectors.toList());

		return new ArrayList<>(a);
	}

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 4));
		System.out.println(findNonRepeating(arr)); // Output: [3, 4]
	}

}
