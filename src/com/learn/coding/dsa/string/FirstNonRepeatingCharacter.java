package com.learn.coding.dsa.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

	private static Character firstNonRepeating(String str) {

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (char ch : str.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		for (char ch : str.toCharArray()) {
			if (map.get(ch) == 1) {
				return ch;
			}
		}
		return null;
	}

	private static Character firstNonRepeatingWithLinkedHashMap(String str) {
		HashMap<Character, Integer> lmap = new LinkedHashMap<Character, Integer>();

		for (char c : str.toCharArray()) {
			lmap.put(c, lmap.getOrDefault(c, 0) + 1);
		}

		for (Map.Entry<Character, Integer> entry : lmap.entrySet()) {
			if (entry.getValue() == 1)
				return entry.getKey();

		}
		return null;
	}

	private static Character firstNonRepeatingWithCharArray(String str) {
		int[] frequency = new int[26];

		for (char ch : str.toCharArray()) {
			frequency[ch - 'a']++;
		}

		for (char ch : str.toCharArray()) {
			if (frequency[ch - 'a'] == 1)
				return ch;
		}

		return null;
	}
	
	public static void main(String[] args) {

		String str = "loveleetcode";

		Character result = firstNonRepeatingWithCharArray(str);

		if (result != null) {
			System.out.println("First non-repeating character: " + result);
		} else {
			System.out.println("No non-repeating character");
		}
	}


}
