package com.learn.coding.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 1; i <= 10; i++) {

			int task = i;

			executor.submit(() -> {

				System.out.println(Thread.currentThread().getName() + " executing Task " + task);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			});
		}

		executor.shutdown();
	}
}
