package com.learn.coding.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {

	public static void main(String[] args) {
		
		//Predicate<T>: takes one input return boolean
		Predicate<String> isLongWord = word -> word.length() >5;
		System.out.println(isLongWord.test("Ashish"));
		
		//Function<T,R> : Transform an input <T> and tranfroms into different output <R>
		
		Function<String,Integer> stringToLength = str -> str.length();
		Integer length = stringToLength.apply("Ashish");
		System.out.println(length);
		
		//Consumer<T> : Consumes an input without returning anything.		
		Consumer<String> upperCaseConvert = str -> System.out.println(str.toUpperCase());
		upperCaseConvert.accept("Ashish");

		//Supplier<T> : Generates or supplies a value without input.
		Supplier<Double> randomValue = () -> Math.random();
		System.out.println(randomValue.get());
		
		
		

	}

}
