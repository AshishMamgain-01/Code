package com.learn.coding.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Creates a fixed number of threads. If all threads are busy, new tasks wait in a queue.
public class FixedThreadPoolExample {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= 10; i++) {
			int task = i;
			executor.submit(() -> {
				System.out.println(Thread.currentThread().getName() + " executing Task " + task);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			});
		}
		executor.shutdown();
	}
}