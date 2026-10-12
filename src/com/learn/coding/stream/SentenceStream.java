package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SentenceStream {

	public static void main(String[] args) {

		String sentence = "Java streams are powerful and expressive";
		System.out.println("Input sentence : " + sentence);
		
		//without inbuilt reverse() -> avaJ smaerts era lufrewop dna evisserpxe
		String result1 = Arrays.stream(sentence.split("\\s+"))
                .map(word -> word.chars()
                        .mapToObj(c -> String.valueOf((char)c))
                        .reduce("", (a, b) -> b + a)) // prepend each char
                .collect(Collectors.joining(" "));
		
		System.out.println("result1 : " +result1);
		
		// avaJ smaerts era lufrewop dna evisserpxe
		String result2 = Arrays.stream(sentence.split("\\s+"))
				.map(s -> new StringBuilder(s).reverse().toString())
				.collect(Collectors.joining(" "));

		System.out.println("result2 : " +result2 );
		
		String result3 = Arrays.stream(sentence.split("\\s+"))
				.reduce("", (w1, w2) -> w2 + " " + w1);
		System.out.println("result3 : " +result3);
		
		// evisserpxe dna lufrewop era smaerts avaJ
		String result4 = Arrays.stream(sentence.split("\\s+"))
				.map(s -> new StringBuilder(s).reverse().toString())
				.reduce("", (w1, w2) -> w2 + " " + w1);
		System.out.println("result4 : " +result4);

		// evisserpxe and lufrewop are smaerts Java
		String[] word = sentence.split("\\s+");
		
		String result5 = IntStream.range(0, word.length)
				.mapToObj(i -> i % 2 == 1 ?
						new StringBuilder(word[i]).reverse().toString() 
						: word[i])
				.reduce("", (w1, w2) -> w2 + " " + w1);
		
		System.out.println("result5 : " +result5);
		
		// sort by length and find second element		
		String secondLargest = Arrays.stream(sentence.split("\\s+"))
				.sorted(Comparator.comparingInt(String::length).reversed())
				.skip(1)
				.findFirst()
				.orElseThrow();
		System.out.println(secondLargest);

	}

}
