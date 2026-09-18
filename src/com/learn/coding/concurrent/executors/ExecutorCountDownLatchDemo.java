package com.learn.coding.concurrent.executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {
    private final CountDownLatch latch;

    public MyTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started working\n");

        try {
            Thread.sleep(2000); // simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
        	System.out.println(Thread.currentThread().getName() + " finishing task\n");
            latch.countDown(); // always decrement
        }

    }
}

public class ExecutorCountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);

        // Submit tasks
        executor.submit(new MyTask(latch));
        executor.submit(new MyTask(latch));
        executor.submit(new MyTask(latch));

        // waits until all tasks finish
        latch.await();
        System.out.println("All tasks are completed successfully !!!");
        executor.shutdown();
    }
}
