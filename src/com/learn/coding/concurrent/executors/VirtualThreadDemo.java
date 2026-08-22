package com.learn.coding.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadDemo {

    public static void main(String[] args) {

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 10; i++) {

                int task = i;

                executor.submit(() -> {

                    System.out.println(
                            Thread.currentThread()
                                    + " executing Task " + task);

                    Thread.sleep(2000);

                    System.out.println(
                            Thread.currentThread()
                                    + " completed Task " + task);

                    return null;
                });
            }
        }
    }
}
