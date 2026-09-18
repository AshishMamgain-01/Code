package com.learn.coding.corejava.streams;

import java.util.Optional;

public class OptionalPitfallsDemo {

	/*
	 * Feature  		orElse 								orElseGet 
	 * Argument type 	Direct 								value Supplier
	 *(lambda/function) Evaluation Always evaluated (eager) Evaluated only if empty(lazy)
	 * Use case 		Cheap, constant fallback 			Expensive fallback (DB call, I/O)
	 * 
	 */
	public static String expensiveOperation() {
		System.out.println("Expensive operation executed!");
		return "Fallback";
	}

	public static void main(String[] args) {
		Optional<String> opt = Optional.of("Ashish");
		Optional<String> opt1 = Optional.empty();

		System.out.println("Using orElse:");
		String val1 = opt1.orElse(expensiveOperation()); // prints "Expensive operation executed!"

		System.out.println("Using orElseGet:");
		String val2 = opt1.orElseGet(() -> expensiveOperation()); // supplier not executed
	}

}
