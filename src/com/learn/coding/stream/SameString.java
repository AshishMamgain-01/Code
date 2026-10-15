package com.learn.coding.stream;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SameString {
	public static Map<Character, Long> freqMap(String str) {
		Map<Character, Long> map = str.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return map;
	}

	public static boolean isCountSame(String str) {
		Map<Character, Long> map = freqMap(str);
		List<Long> list = map.entrySet().stream().map(e -> e.getValue()).distinct().collect(Collectors.toList());

		if (list.size() == 1)
			return true;
		else {
			return false;
		}
	}
	
	public static boolean isCountSameSet(String str) {
		Map<Character, Long> map = freqMap(str);
		return new HashSet<Long>(map.values()).size()==1;
	}

	public static boolean isCountSameAfterElementRemoval(String str) {
		for (int i = 0; i < str.length(); i++) {
			// remove character at index i
			String modified = str.substring(0, i) + str.substring(i + 1);
			if (isCountSameSet(modified)) {
				System.out.println("index -> "+ i +" char -> "+str.charAt(i));
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		System.out.println(isCountSame("aabbcc"));
		System.out.println(isCountSameAfterElementRemoval("aabbc"));
	}
}
