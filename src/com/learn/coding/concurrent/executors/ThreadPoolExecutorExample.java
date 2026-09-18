package com.learn.coding.concurrent.executors;

import java.util.concurrent.*;

/*
 * 
 * 
 * Task submitted
      ↓
Threads < corePoolSize?
      │
     YES
      ↓
Create worker
      │
     NO
      ↓
Queue task
      │
Queue full?
   /      \
 NO       YES
 │         │
Queue    Threads < max?
           │
          YES
           ↓
      Create worker
           │
          NO
           ↓
        Reject
 * 
 * 
 * 
 */

public class ThreadPoolExecutorExample {

	public static void main(String[] args) {
		try (ThreadPoolExecutor executor = new ThreadPoolExecutor(
				2, // Core pool size
				4, // Maximum pool size
				1, // Keep alive time (seconds)
				TimeUnit.SECONDS, // Time unit for keep alive time
				new LinkedBlockingQueue<>(1), // Task queue
				new ThreadPoolExecutor.DiscardPolicy())) {
			
			// Submit 5 tasks to the executor
			for (int i = 1; i <= 7; i++) {
				final int taskId = i;
				executor.execute(() -> {
					System.out.println(">>Task " + taskId + " is being executed by " + Thread.currentThread().getName());
					try {
						Thread.sleep(1000); // Simulating a task that takes 1 second to complete
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					System.out.println("Task " + taskId + " completed by " + Thread.currentThread().getName());
				});
			}

			// Gracefully shut down the executor after all tasks are completed
			executor.shutdown();
		}
	}
}