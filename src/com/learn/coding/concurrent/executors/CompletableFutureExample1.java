package com.learn.coding.concurrent.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample1{
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// Asynchronous tasks
		CompletableFuture<String> serviceA = CompletableFuture.supplyAsync(() -> {
			sleep(500);
			return "Result from Service A";
		}, executor);

		CompletableFuture<String> serviceB = CompletableFuture.supplyAsync(() -> {
			sleep(300);
			return "Result from Service B";
		}, executor);

		CompletableFuture<String> serviceC = CompletableFuture.supplyAsync(() -> {
			sleep(700);
			return "Result from Service C";
		}, executor);

		// Combine all futures
		CompletableFuture<Void> allFutures = CompletableFuture.allOf(serviceA, serviceB, serviceC);

		// When all are done, collect results
		allFutures.thenRun(() -> {
			try {
				System.out.println("Got: " + serviceA.get());
				System.out.println("Got: " + serviceB.get());
				System.out.println("Got: " + serviceC.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).join(); // wait for completion

		executor.shutdown();
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
