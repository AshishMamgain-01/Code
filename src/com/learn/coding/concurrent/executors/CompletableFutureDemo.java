package com.learn.coding.concurrent.executors;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 1. supplyAsync → start async task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return "Hello";
        }, executor);

        // 2. thenApply → transform result
        CompletableFuture<String> upper = future.thenApply(s -> s.toUpperCase());

        // 3. thenAccept → consume result (no return)
        upper.thenAccept(s -> System.out.println("thenAccept: " + s));

        // 4. thenRun → run side-effect (no input, no output)
        upper.thenRun(() -> System.out.println("thenRun: Task finished"));

        // 5. thenCompose → chain dependent async calls
        CompletableFuture<String> composed = upper.thenCompose(s ->
            CompletableFuture.supplyAsync(() -> s + " World", executor)
        );

        // 6. thenCombine → combine two independent futures
        CompletableFuture<String> combined = composed.thenCombine(
            CompletableFuture.supplyAsync(() -> "!!!", executor),
            (a, b) -> a + b
        );

        // 7. allOf → wait for multiple futures
        CompletableFuture<Void> all = CompletableFuture.allOf(future, upper, composed, combined);

        // 8. exceptionally → handle exceptions with fallback
        CompletableFuture<String> safe = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Boom!");
            return "Won't reach";
        }, executor).exceptionally(ex -> "Recovered from error: " + ex.getMessage());

        // 9. handle → process both result and exception
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Oops!");
            return "Success";
        }, executor).handle((res, ex) -> {
            if (ex != null) return "Handled error: " + ex.getMessage();
            return res;
        });

        // 10. whenComplete → observe result or error (side-effect only)
        combined.whenComplete((res, ex) -> {
            if (ex != null) System.out.println("whenComplete saw error: " + ex);
            else System.out.println("whenComplete got: " + res);
        });

        // Block until all tasks finish
        all.join();

        // Print results
        System.out.println("Original future: " + future.get());
        System.out.println("Upper: " + upper.get());
        System.out.println("Composed: " + composed.get());
        System.out.println("Combined: " + combined.get());
        System.out.println("Safe (exceptionally): " + safe.get());
        System.out.println("Handled (handle): " + handled.get());

        executor.shutdown();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

