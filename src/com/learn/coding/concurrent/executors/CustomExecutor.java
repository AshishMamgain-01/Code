package com.learn.coding.concurrent.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomExecutor {

    private static final Runnable STOP_WORKER = () -> { };

    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final Thread[] workers;
    private volatile boolean acceptingTasks = true;

    public CustomExecutor(int poolSize) {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Pool size must be greater than zero");
        }

        workers = new Thread[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Thread(this::runWorker, "Worker-" + (i + 1));
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!acceptingTasks) {
            throw new IllegalStateException("The thread pool has been shut down");
        }
        taskQueue.offer(task);
    }

    public void shutdown() {
        acceptingTasks = false;
        for (int i = 0; i < workers.length; i++) {
            taskQueue.offer(STOP_WORKER);
        }
    }

    private void runWorker() {
        while (true) {
            try {
                Runnable task = taskQueue.take();
                if (task == STOP_WORKER) {
                    return;
                }
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } catch (RuntimeException e) {
                System.err.println("Task failed: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	CustomExecutor threadPool = new CustomExecutor(2);
        CountDownLatch tasksCompleted = new CountDownLatch(4);

        for (int taskNumber = 1; taskNumber <= 4; taskNumber++) {
            int currentTask = taskNumber;
            threadPool.submit(() -> {
                System.out.println("Task " + currentTask + " executed by "
                        + Thread.currentThread().getName());
                tasksCompleted.countDown();
            });
        }

        tasksCompleted.await();
        threadPool.shutdown();
    }
}
