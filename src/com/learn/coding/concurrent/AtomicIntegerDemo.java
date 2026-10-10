package com.learn.coding.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // atomically increments by 1
    }

    public int getCount() {
        return count.get(); // atomically retrieves the value
    }
}

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
    	Counter counter = new Counter();

        // Two threads increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
            	counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
            	counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + counter.getCount());
    }
}