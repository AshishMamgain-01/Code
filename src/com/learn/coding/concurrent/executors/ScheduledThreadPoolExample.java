package com.learn.coding.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {

	public static void main(String[] args) {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

		System.out.println("Application Started");

		scheduler.schedule(() -> {
			System.out.println("Task executed after 5 seconds");
		}, 5, TimeUnit.SECONDS);

		scheduler.schedule(() -> {
			System.out.println("Second task executed");
			scheduler.shutdown();
		}, 7, TimeUnit.SECONDS);
	}
}