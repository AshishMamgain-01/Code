package com.learn.coding.dsa.string;

import java.util.LinkedHashMap;
import java.util.Map;

public class CountConsecutiveChar {

	public static void main(String[] args) {
		String str = "XXXFFZZAAABBBFF";

		Map<String, Integer> map = getConsecutiveCharCount(str);
		System.out.println(map);
		System.out.println(getCompressString(str));
	}
	
	private static String getCompressString(String str) {
		int count =1;
		StringBuilder result = new StringBuilder();
		for(int i = 1; i<str.length();i++) {
			
			if(str.charAt(i) ==str.charAt(i-1)) {
				count++;
			}
			else {
				result.append(str.charAt(i-1));
				result.append(count);
				count =1;
			}
		}
		
		result.append(str.charAt(str.length()-1));
		result.append(count);
		return result.toString();
	}

	private static Map<String, Integer> getConsecutiveCharCount(String str) {

		int count = 1;
		Map<String, Integer> map = new LinkedHashMap<>();

		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == str.charAt(i- 1)) {
				count++;
			} else {
				String block = str.substring(i - count, i);
				map.put(block, map.getOrDefault(block, 0) + 1);
				count = 1;
			}
		}
		String block = str.substring(str.length() - count);
		map.put(block, map.getOrDefault(block, 0) + 1);
		return map;
	}

}
