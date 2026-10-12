package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sentence = "Java streams are powerful and expressive";

		// find the 2nd max word(by length) from the given sentence/string

		Optional<String> word = Arrays.stream(sentence.split(" "))
				.sorted((w1, w2) -> Integer.compare(w2.length(), w1.length())).skip(1).findFirst();
		System.out.println(word);

		Optional<String> str = Arrays.stream(sentence.split(" "))
				.sorted(Comparator.comparingInt(String::length).reversed()).skip(1).findFirst();
		System.out.println(str);

		String res = Arrays.stream(sentence.split("\\s+")).sorted(Comparator.comparingInt(String::length).reversed())
				.skip(0).findFirst().orElseThrow();
		System.out.println(res);

		System.out.println(Arrays.stream(sentence.split("\\s+")).reduce("", (a, b) -> b + " " + a));

		/*
		 * I/P: String sentence = "Java streams are powerful and expressive"; O/P:
		 * evisserpxe dna lufrewop era smaerts avaJ
		 *
		 */
		String asds = Arrays.stream(sentence.split("\\s+")).map(s -> new StringBuilder(s).reverse().toString())
				.reduce("", (w1, w2) -> w2 + " " + w1);
		System.out.println(asds);

		// O/P: evisserpxe and lufrewop are smaerts Java

		System.out.println(asds);
	}

}
