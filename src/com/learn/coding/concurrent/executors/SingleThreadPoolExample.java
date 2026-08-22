package com.learn.coding.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolExample {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		for (int i = 1; i <= 5; i++) {

			int task = i;

			executor.submit(() -> {

				System.out.println(Thread.currentThread().getName() + " executing Task " + task);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			});
		}

		executor.shutdown();
	}
}