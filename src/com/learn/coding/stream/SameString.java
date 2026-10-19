package com.learn.coding.stream;

import java.util.ArrayList;
import java.util.Collection;
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
	
	public static boolean isCountSameAfterElementRemovalNew(String str) {
	    Map<Character, Long> map = freqMap(str);
	    System.out.println(map);
	    Collection<Long> counts = map.values();
	    System.out.println(counts);

	    // Frequency of frequencies
	    Map<Long, Long> freqOfFreq = counts.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	    System.out.println(freqOfFreq);

	    if (freqOfFreq.size() == 1) {
	        // All counts already same
	        return true;
	    } else if (freqOfFreq.size() == 2) {
	        List<Map.Entry<Long, Long>> list = new ArrayList<>(freqOfFreq.entrySet());
	        long c1 = list.get(0).getKey(), f1 = list.get(0).getValue();
	        long c2 = list.get(1).getKey(), f2 = list.get(1).getValue();

	        // Case 1: one character occurs once → remove it
	        if ((c1 == 1 && f1 == 1) || (c2 == 1 && f2 == 1)) return true;

	        // Case 2: one count is higher by 1 and occurs once
	        if ((Math.abs(c1 - c2) == 1) && ((f1 == 1) || (f2 == 1))) return true;
	    }

	    return false;
	}


	public static void main(String[] args) {

		System.out.println(isCountSame("aabbcc"));
		System.out.println(isCountSameAfterElementRemovalNew("aabbc"));
	}
}
