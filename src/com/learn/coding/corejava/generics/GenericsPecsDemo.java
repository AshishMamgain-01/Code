package com.learn.coding.corejava.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * PECS: Producer Extends, Consumer Super.
 */
public class GenericsPecsDemo {

	public static void main(String[] args) {
		List<Integer> integers = List.of(1, 2, 3);
		List<Number> numbers = new ArrayList<>();
		copy(integers, numbers);
		System.out.println("copied into List<Number>: " + numbers);

		List<Object> objects = new ArrayList<>();
		copy(integers, objects);
		System.out.println("copied into List<Object>: " + objects);

		System.out.println("Erasure: List<String> and List<Integer> are both List at runtime: "
				+ (new ArrayList<String>().getClass() == new ArrayList<Integer>().getClass()));
	}

	static <T> void copy(List<? extends T> src, List<? super T> dest) {
		for (T item : src) {
			dest.add(item);
		}
	}
}
