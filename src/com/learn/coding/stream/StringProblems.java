package com.learn.coding.stream;

import java.util.*;
import java.util.stream.*;

public class StringProblems {

	public static void main(String[] args) {

		String str = "This is my life I love my life";

		// 1️⃣ Count frequency of each word and print those with occurrence > 1
		Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() > 1)
				.forEach(System.out::println);

		// 2️⃣ Find duplicate words (case-insensitive)
		String duplicates = Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() > 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.joining(", "));
		System.out.println("Duplicates: " + duplicates);

		// 3️⃣ Find unique words (appear only once)
		String uniques = Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() == 1)
				.map(Map.Entry::getKey)
				.collect(Collectors.joining(", "));
		System.out.println("Unique words: " + uniques);

		// 4️⃣ Count frequency of each word
		Map<String, Long> frequency = Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		System.out.println("Word frequency: " + frequency);

		// 5️⃣ Find the longest word(s)
		String longest = Arrays.stream(str.split(" "))
				.max(Comparator.comparingInt(String::length))
				.orElse("");
		System.out.println("Longest word: " + longest);

		// 6️⃣ Sort words alphabetically
		String sortedAlpha = Arrays.stream(str.split(" "))
				.sorted(String.CASE_INSENSITIVE_ORDER)
				.collect(Collectors.joining(", "));
		System.out.println("Alphabetical sort: " + sortedAlpha);

		// 7️⃣ Sort words by frequency (descending)
		String sortedByFreq = Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
				.map(e -> e.getKey() + "=" + e.getValue())
				.collect(Collectors.joining(", "));
		System.out.println("Sorted by frequency: " + sortedByFreq);

		// 8️⃣ Remove duplicates and print distinct words
		String distinctWords = Arrays.stream(str.split(" "))
				.distinct()
				.collect(Collectors.joining(", "));
		System.out.println("Distinct words: " + distinctWords);

		// 9️⃣ Count total number of words
		long totalWords = Arrays.stream(str.split(" ")).count();
		System.out.println("Total words: " + totalWords);

		// 🔟 Convert all words to uppercase
		String upperCaseWords = Arrays.stream(str.split(" "))
				.map(String::toUpperCase)
				.collect(Collectors.joining(" "));
		System.out.println("Uppercase words: " + upperCaseWords);

		// 1️⃣1️⃣ Check if any word starts with "l"
		boolean startsWithL = Arrays.stream(str.split(" "))
				.anyMatch(w -> w.toLowerCase().startsWith("l"));
		System.out.println("Any word starts with 'l': " + startsWithL);

		// 1️⃣2️⃣ Reverse each word
		String reversedWords = Arrays.stream(str.split(" "))
				.map(w -> new StringBuilder(w).reverse().toString())
				.collect(Collectors.joining(", "));
		System.out.println("Reversed words: " + reversedWords);

		// 1️⃣3️⃣ Find palindromic words
		String palindromes = Arrays.stream(str.split(" "))
				.map(String::toLowerCase)
				.filter(w -> w.equals(new StringBuilder(w).reverse().toString()))
				.collect(Collectors.joining(", "));
		System.out.println("Palindromes: " + palindromes);

		// 1️⃣4️⃣ Find shortest word(s)
		String shortest = Arrays.stream(str.split(" "))
				.min(Comparator.comparingInt(String::length))
				.orElse("");
		System.out.println("Shortest word: " + shortest);

		// 1️⃣5️⃣ Find word with maximum vowels
		String maxVowelWord = Arrays.stream(str.split(" "))
				.max(Comparator.comparingInt(w -> countVowels(w)))
				.orElse("");
		System.out.println("Word with max vowels: " + maxVowelWord);
	}

	// Helper method to count vowels
	private static int countVowels(String word) {
		return (int) word.toLowerCase().chars()
				.filter(ch -> "aeiou".indexOf(ch) != -1)
				.count();
	}
}
