package com.learn.coding.concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunnableTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Callable<String> task = () -> {
			Thread.sleep(100);
			
			return "Result form callable";

		};

		Runnable task1 = () -> {
			try {
			Thread.sleep(100);
			System.out.println("I am Runnable.");
			}catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};

		Future<String> future = executor.submit(task);
		Future<?> future1 = executor.submit(task1);
		
		System.out.println("Hi Ashish");
		
		String as = future.get();
		String as1 = (String) future1.get();
		System.out.println("Hello Ashish : "+ as);
		System.out.println("Hello Ashish as1 : "+ as1);

		executor.shutdown();
	}
}