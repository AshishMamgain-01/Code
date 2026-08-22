package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class StreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sentence = "Java streams are powerful and expressive";
		
		//find the 2nd max word(by length) from the given sentence/string
		
		Optional<String> word = Arrays.stream(sentence.split(" ")).sorted((w1,w2) -> Integer.compare(w2.length(), w1.length())).skip(1).findFirst();
		System.out.println(word);
		
		Optional<String> str = Arrays.stream(sentence.split(" ")).sorted(Comparator.comparingInt(String::length).reversed()).skip(1).findFirst();
		System.out.println(str);
	}

}
