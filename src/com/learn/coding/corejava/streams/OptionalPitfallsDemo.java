package com.learn.coding.corejava.streams;

import java.util.List;
import java.util.Optional;

/**
 * Optional is a return type for "missing", not a field, not a parameter.
 */
public class OptionalPitfallsDemo {

	public static void main(String[] args) {
		System.out.println(deviceName(Optional.of("NCS1004")));
		System.out.println(deviceName(Optional.empty()));

		List<String> ids = List.of("a", "b");
		System.out.println("empty collection, not Optional<List>: " + ids.stream().filter(s -> s.isBlank()).toList());

		try {
			Optional.<String>empty().get();
		} catch (Exception e) {
			System.out.println("never call get() on empty: " + e.getClass().getSimpleName());
		}
	}

	static String deviceName(Optional<String> maybe) {
		return maybe.map(String::toUpperCase).orElse("UNKNOWN");
	}
}
