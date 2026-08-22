package com.learn.coding.concurrent.executors;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//Runs every 5 seconds, irrespective of how long the previous execution took 
//(as long as it doesn't exceed the period).
public class ScheduledFixedRateExample {

	public static void main(String[] args) throws Exception {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate(() -> {

			System.out.println("Running at " + LocalTime.now());

		}, 2, 5, TimeUnit.SECONDS);

		Thread.sleep(20000);

		scheduler.shutdown();
	}
}
