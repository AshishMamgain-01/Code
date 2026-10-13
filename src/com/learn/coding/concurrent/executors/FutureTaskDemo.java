package com.learn.coding.concurrent.executors;

import java.util.concurrent.*;
/*
*
Runnable → fire-and-forget.
Callable + Future → async with result.
FutureTask → hybrid, useful when you want to run a task manually in a thread but still get a Future.
 * 
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable task (no return value)
        Runnable runnableTask = () -> {
            System.out.println("Runnable task executed");
        };

        // Callable task (returns a value)
        Callable<String> callableTask = () -> {
            Thread.sleep(500);
            return "Callable result";
        };

        // Submitting Runnable → Future<?> (but result is always null)
        Future<?> runnableFuture = executor.submit(runnableTask);

        // Submitting Callable → Future<String>
        Future<String> callableFuture = executor.submit(callableTask);

        // Using FutureTask explicitly
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(300);
            return "FutureTask result";
        });

        // Run FutureTask in a thread
        Thread t = new Thread(futureTask);
        t.start();

        // Collect results
        System.out.println("Runnable Future result: " + runnableFuture.get()); // null
        System.out.println("Callable Future result: " + callableFuture.get());
        System.out.println("FutureTask result: " + futureTask.get());

        executor.shutdown();
    }
}
