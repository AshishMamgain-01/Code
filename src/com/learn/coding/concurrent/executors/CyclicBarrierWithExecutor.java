package com.learn.coding.concurrent.executors;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PlayerTask1 implements Runnable {
    private final CyclicBarrier barrier;

    public PlayerTask1(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " reached barrier");

        try {
            Thread.sleep((long) (Math.random() * 3000)); // simulate arrival delay
            barrier.await(); // wait until all players arrive
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " started playing");
    }
}

public class CyclicBarrierWithExecutor {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> 
            System.out.println("\nAll players arrived. Game Starts!\n")
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new PlayerTask1(barrier));
        executor.submit(new PlayerTask1(barrier));
        executor.submit(new PlayerTask1(barrier));

        executor.shutdown();
    }
}
