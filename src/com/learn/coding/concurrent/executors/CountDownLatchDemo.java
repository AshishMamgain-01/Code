package com.learn.coding.concurrent.executors;

import java.util.concurrent.CountDownLatch;

class EmployeeTask implements Runnable {
    private final CountDownLatch latch;

    public EmployeeTask(CountDownLatch latch) {
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

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        // Create workers
        Thread t1 = new Thread(new EmployeeTask(latch), "Employee-1");
        Thread t2 = new Thread(new EmployeeTask(latch), "Employee-2");
        Thread t3 = new Thread(new EmployeeTask(latch), "Employee-3");

        // Start workers
        t1.start();
        t2.start();
        t3.start();

        System.out.println("Manager waiting...");

        // Manager waits until all employees finish
        latch.await();

        System.out.println("All employees finished.");
        System.out.println("Manager starts review.");
    }
}
