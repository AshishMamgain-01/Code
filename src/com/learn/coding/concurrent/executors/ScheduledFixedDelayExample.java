package com.learn.coding.concurrent.executors;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//The next execution starts only after the previous execution completes plus the specified delay.
public class ScheduledFixedDelayExample {

	public static void main(String[] args) throws Exception {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleWithFixedDelay(() -> {

			System.out.println("Started : " + LocalTime.now());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			System.out.println("Completed : " + LocalTime.now());

		}, 2, 5, TimeUnit.SECONDS);

		Thread.sleep(25000);

		scheduler.shutdown();
	}
}