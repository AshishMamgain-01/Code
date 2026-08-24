package com.learn.coding.dsa.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountConsecutiveChar {

	public static void main(String[] args) {
		String str = "XXXFFZZAAABBBFF";

		Map<String, Integer> map = getConsecutiveCharCount(str);
		System.out.println(map);

	}

	private static Map<String, Integer> getConsecutiveCharCount(String input) {

		char[] characterArray = input.toCharArray();

		Map<String, Integer> map = new LinkedHashMap<>();

		int count = 1;

		for (int i = 1; i < characterArray.length; i++) {
			if (characterArray[i] == characterArray[i - 1]) {
				count++;
			} else {
				String block = input.substring(i - count, i);
				map.put(block, map.getOrDefault(block, 0) + 1);
				count = 1;
			}

		}
		String block = input.substring(input.length() - count);
		map.put(block, map.getOrDefault(block, 0) + 1);
		return map;
	}

}
