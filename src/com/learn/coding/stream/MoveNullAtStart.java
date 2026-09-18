package com.learn.coding.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoveNullAtStart {

	public static void main(String[] args) {
		List<String> input = Arrays.asList("ashish", "mamgain", null, null, "Amit", "Atul");

		List<String> result = Stream
				.concat(input.stream().filter(Objects::isNull), input.stream().filter(Objects::nonNull))
				.collect(Collectors.toList());
		
		System.out.println(result);
	}

}
