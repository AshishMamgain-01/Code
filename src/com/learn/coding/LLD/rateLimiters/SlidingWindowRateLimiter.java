package com.learn.coding.LLD.rateLimiters;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowRateLimiter {

    private static class KeyState {
        private final ReentrantLock lock = new ReentrantLock();
        private final Deque<Long> timestamps = new ArrayDeque<>();
        private volatile long lastAccessNanos;
    }

    private final int maxRequests;
    private final long windowNanos;
    private final ConcurrentHashMap<String, KeyState> states = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowSeconds) {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests must be > 0");
        }
        if (windowSeconds <= 0) {
            throw new IllegalArgumentException("windowSeconds must be > 0");
        }
        this.maxRequests = maxRequests;
        this.windowNanos = windowSeconds * 1_000_000_000L;
    }

    public boolean allow(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        KeyState state = states.computeIfAbsent(key, k -> new KeyState());
        long now = System.nanoTime();

        state.lock.lock();
        try {
            removeExpired(state.timestamps, now);

            if (state.timestamps.size() >= maxRequests) {
                state.lastAccessNanos = now;
                return false;
            }

            state.timestamps.addLast(now);
            state.lastAccessNanos = now;
            return true;
        } finally {
            state.lock.unlock();
        } 
    }

    private void removeExpired(Deque<Long> timestamps, long now) {
        long cutoff = now - windowNanos;
        while (!timestamps.isEmpty() && timestamps.peekFirst() <= cutoff) {
            timestamps.removeFirst();
        }
    }

    // Demo main method
    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(3, 5); 
        // max 3 requests per 5 seconds

        String userKey = "user123";

        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allow(userKey);
            System.out.println("Request " + i + " allowed? " + allowed);
            Thread.sleep(1000); // simulate 1 second between requests
        }

        // Wait enough time for window to expire
        System.out.println("Waiting 6 seconds...");
        Thread.sleep(6000);

        for (int i = 6; i <= 8; i++) {
            boolean allowed = limiter.allow(userKey);
            System.out.println("Request " + i + " allowed? " + allowed);
        }
    }
}
