package com.learn.coding.concurrent.executors;

import java.util.*;
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Prepare multiple Callable tasks
        List<Callable<String>> tasks = Arrays.asList(
            () -> {
                Thread.sleep(500);
                return "Result from Service A";
            },
            () -> {
                Thread.sleep(300);
                return "Result from Service B";
            },
            () -> {
                Thread.sleep(700);
                return "Result from Service C";
            }
        );

        // invokeAll runs all tasks and returns a list of Futures
        List<Future<String>> futures = executor.invokeAll(tasks);

        // Collect results
        for (Future<String> f : futures) {
            System.out.println("Got: " + f.get());
        }

        executor.shutdown();
    }
}
