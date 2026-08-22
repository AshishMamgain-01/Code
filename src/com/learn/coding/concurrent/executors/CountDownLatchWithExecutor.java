package com.learn.coding.concurrent.executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private final CountDownLatch latch;

    public Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started working");

        try {
            Thread.sleep(2000); // simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " finished");
        latch.countDown();
    }
}

public class CountDownLatchWithExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);

        // Submit tasks
        executor.submit(new Task(latch));
        executor.submit(new Task(latch));
        executor.submit(new Task(latch));

        // Manager waits until all tasks finish
        latch.await();

        System.out.println("All tasks are completed successfully !!!");

        executor.shutdown();
    }
}
