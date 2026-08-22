package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringProblems {

	public static void main(String[] args) {

		// Count frequency of each word and there occurrence and
		// give word whose occurrence is more than 1

		String str = "This is my life I love my life";

		Arrays.stream(str.split(" ")).map(s -> s.toLowerCase())
				.collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > 1).forEach(System.out::println);

		// Find duplicate words (case-insensitive)

		// Expected output: my, life

		String result = Arrays.stream(str.split(" ")).map(s -> s.toLowerCase())
				.collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(e -> e.getKey()).collect(Collectors.joining(", "));

		System.out.println(result);

		// Use groupingBy(String::toLowerCase, counting()) + filter > 1.

		// Find unique words (appear only once)

		// Expected output: this, is, i, love

		// Filter == 1.

		String result2 = Arrays.stream(str.split(" ")).sorted().map(word -> word.toLowerCase())
				.collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet().stream()
				.filter(wo -> wo.getValue() == 1).map(e -> e.getKey()).collect(Collectors.joining(","));
		System.out.println(result2);

		// Count frequency of each word

		// Output: {this=1, is=1, my=2, life=2, i=1, love=1}

		// Use Collectors.groupingBy(..., Collectors.counting()).

		// Find the longest word

		// Output: life or love (both length 4).
		// Use max(Comparator.comparing(String::length)).

		String a = Arrays.stream(str.split(" ")).max(Comparator.comparing(String::length)).get();
		System.out.println(a);

		List<String> sa = Arrays.stream(str.split(" ")).map(e -> e.toLowerCase())
				.collect(Collectors.groupingBy(String::length)).entrySet().stream().max(Map.Entry.comparingByKey())
				.map(Map.Entry::getValue).orElse(Collections.EMPTY_LIST);

		System.out.println(sa);

		System.out.println(
				Arrays.stream(str.split(" ")).map(e -> e.toLowerCase()).collect(Collectors.groupingBy(String::length)));

		// Sort words alphabetically

		String sortedString = Arrays.stream(str.split(" ")).sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(","));
		System.out.println(sortedString);

		// Output: i, is, life, lIfe, love, my, this (depending on case handling).

		// Use sorted().

		// Sort words by frequency (descending)

		String aasd = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(w -> w, Collectors.counting()))
				.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
				.map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(", "));

		System.out.println(aasd);

		// Output: my=2, life=2, this=1, is=1, i=1, love=1.

		// Sort by Map.Entry::getValue.

		// Remove duplicates and print distinct words

		// Use distinct().
		String asds = Arrays.stream(str.split(" ")).distinct().collect(Collectors.joining(", "));
		System.out.println(asds);

		// Output: this, is, my, life, i, love, lIfe.

		// Count total number of words

		long count = Arrays.stream(str.split(" ")).count();
		System.out.println(count);

		// Output: 7.

		// Use count().

		// Convert all words to uppercase and print

		String asd = Arrays.stream(str.split(" ")).map(s -> s.toUpperCase()).collect(Collectors.joining(" "));
		System.out.println(asd);
		// Output: THIS, IS, MY, LIFE, I, LOVE, LIFE.

		// Use map(String::toUpperCase).

		// Check if any word starts with "l"

		// Output: true.

		String asdfss = Arrays.stream(str.split(" ")).filter(w -> w.toUpperCase().startsWith("L"))
				.collect(Collectors.joining(","));
		System.out.println(asdfss);

		// Use anyMatch(w -> w.toLowerCase().startsWith("l")).

		String strin = "This is my life I love my lIfe";

		Arrays.stream(strin.split(" ")).map(s -> s.toLowerCase())
				.collect(Collectors.groupingBy(w -> w, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > 1).forEach(System.out::println);

	}

}
