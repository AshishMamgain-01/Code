package com.learn.coding.concurrent.executors;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class PlayerTask implements Runnable {
    private final CyclicBarrier barrier;

    public PlayerTask(CyclicBarrier barrier) {
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

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> 
            System.out.println("\nAll players arrived. Game Starts!\n")
        );

        // Create and start players
        new Thread(new PlayerTask(barrier), "Player-1").start();
        new Thread(new PlayerTask(barrier), "Player-2").start();
        new Thread(new PlayerTask(barrier), "Player-3").start();
    }
}
