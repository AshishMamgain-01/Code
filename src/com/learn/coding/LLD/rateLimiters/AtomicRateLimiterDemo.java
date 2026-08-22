package com.learn.coding.LLD.rateLimiters;

import java.util.concurrent.atomic.AtomicInteger;

class RateLimiter {
    private final int maxRequests;
    private final long timeWindowMillis;
    private final AtomicInteger requestCount = new AtomicInteger(0);
    private long windowStart;

    public RateLimiter(int maxRequests, long timeWindowMillis) {
        this.maxRequests = maxRequests;
        this.timeWindowMillis = timeWindowMillis;
        this.windowStart = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        // Reset window if time has passed
        if (now - windowStart >= timeWindowMillis) {
            windowStart = now;
            requestCount.set(0);
        }

        // Atomically increment and check
        if (requestCount.incrementAndGet() <= maxRequests) {
            return true; // request allowed
        } else {
            return false; // request denied
        }
    }
}

public class AtomicRateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5, 1000); // max 5 requests per second

        for (int i = 1; i <= 10; i++) {
            if (limiter.allowRequest()) {
                System.out.println("Request " + i + " allowed");
            } else {
                System.out.println("Request " + i + " denied");
            }
            Thread.sleep(150); // simulate requests every 150ms
        }
    }
}