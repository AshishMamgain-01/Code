package com.learn.coding.stream;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RandomNumber {

	public static void main(String[] args) {
		Random random = new Random();

		Supplier<Integer> randomNumber = () -> random.nextInt(10) + 10;

		for (int i = 0; i < 20; i++) {
			System.out.println(randomNumber.get());

		}

		IntStream.rangeClosed(0, 10).forEach(System.out::println);
		IntStream.rangeClosed(0, 10).forEach(num -> System.out.println(num));

		Supplier<Integer> supplier = () -> {
			int last = 0;
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				last = i;
			}
			return last; // must return an Integer
		};

		System.out.println("Last value: " + supplier.get());
		
		Runnable task = () -> {
		    for (int i = 0; i < 10; i++) {
		        System.out.println(i);
		    }
		};
		
		
		task.run();
		
		List<String> list = new CopyOnWriteArrayList<>();

	}

}
