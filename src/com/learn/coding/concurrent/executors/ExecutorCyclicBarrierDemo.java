package com.learn.coding.concurrent.executors;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private final CyclicBarrier cyclicBarrier;

    public Task(CyclicBarrier barrier) {
        this.cyclicBarrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " reached barrier");

        try {
            Thread.sleep((long) (Math.random() * 3000)); // simulate arrival delay
            cyclicBarrier.await(); // wait until all players arrive
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " started playing");
    }
}

public class ExecutorCyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> 
            System.out.println("\nAll players arrived. Game Starts!\n")
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Task(barrier));
        executor.submit(new Task(barrier));
        executor.submit(new Task(barrier));
        executor.shutdown();
    }
}
