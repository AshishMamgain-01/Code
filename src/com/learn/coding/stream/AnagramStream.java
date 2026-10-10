package com.learn.coding.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnagramStream {
	
	public static void main(String[] args) {
		List<String> words = Arrays.asList("listen", "silent", "enlist", "google", "elbow", "below");
		
		//Simpler Approach 1: Use char[] + Arrays.sort
		Map<String, List<String>> anagrams = words.stream()
			    .collect(Collectors.groupingBy(word -> {
			        char[] chars = word.toCharArray();
			        Arrays.sort(chars);
			        return new String(chars);
			    }));

			System.out.println(anagrams);



		//Simpler Approach 2: Frequency Map Key
			Map<Map<Character, Long>, List<String>> anagramss = words.stream()
				    .collect(Collectors.groupingBy(word -> 
				        word.chars()
				            .mapToObj(c -> (char) c)
				            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				    ));

			System.out.println(anagramss);
	}
	
	

}
