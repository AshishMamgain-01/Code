package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class NumericalStreaming {

	public static void main(String[] args) {
		int[] arr = { 10, 5, 20, 8, 3, 15, 25, 2, 18 };
		System.out.println("Input array : " + Arrays.toString(arr));
		
		int maxNum = Arrays.stream(arr).max().orElseThrow();
		System.out.println("Max Num : "+ maxNum);
		
		int minNum = Arrays.stream(arr).min().orElseThrow();
		System.out.println("Min Num : "+ minNum);
		
		double average = Arrays.stream(arr).average().orElseThrow();
		System.out.println("average 1 : "+average);
		
		double average1 = Arrays.stream(arr)
                .boxed() // convert IntStream → Stream<Integer>
                .collect(Collectors.averagingDouble(Integer::doubleValue));
		System.out.println("average 2 : "+average1);
		
		long count1 = Arrays.stream(arr).count();
		System.out.println("count 1 : "+count1);
		
		long count2 = Arrays.stream(arr)
		.boxed() // convert IntStream → Stream<Integer>
		.collect(Collectors.summarizingInt(Integer::intValue)).getCount();
		
		System.out.println("count 2 : "+count2);
		
		int sum = Arrays.stream(arr).sum();
		System.out.println("sum 1 : " + sum);
		
		int sum1 = Arrays.stream(arr)
				.boxed() // convert IntStream → Stream<Integer>
				.collect(Collectors.summingInt(Integer::intValue));
		System.out.println("sum 2 : " + sum1);
		
		long sum2 = Arrays.stream(arr)
				.boxed() // convert IntStream → Stream<Integer>
				.collect(Collectors.summarizingInt(Integer::intValue)).getSum();
		System.out.println("sum 3 : " + sum2);
		
		int secondGreater = Arrays.stream(arr)
				.boxed()
				.sorted(Comparator.comparingInt(Integer::intValue).reversed())
				.skip(1)
				.findFirst()
				.orElseThrow();
		System.out.println("Second Greater: " + secondGreater);

		// Top 3 maximum numbers
		List<Integer> top3Max = Arrays.stream(arr)
				.boxed() // convert IntStream → Stream<Integer>
				.sorted(Comparator.reverseOrder()) // sort descending
				.limit(3)
				.collect(Collectors.toList());

		// Top 3 minimum numbers
		List<Integer> top3Min = Arrays.stream(arr)
				.boxed()// convert IntStream → Stream<Integer>
				.sorted() // sort ascending
				.limit(3)
				.collect(Collectors.toList());

		System.out.println("Top 3 Max: " + top3Max);
		System.out.println("Top 3 Min: " + top3Min);
		
		List<Integer> list = Arrays.asList(10, 5, 20, 8, 3, 15, 25, 2, 18);
		long listSum = list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum();
		System.out.println("listSum: " + listSum);


	}

}
