package com.learn.coding.concurrent.executors;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
	public static void main(String[] args) throws Exception {
		// Run async task that returns a value
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			return "Hello from async task!";
		});

		// Chain transformations
		future.thenApply(result -> result.toUpperCase()).thenAccept(System.out::println);

		// Block only if needed
		System.out.println("Original result: " + future.get());

	}
}
