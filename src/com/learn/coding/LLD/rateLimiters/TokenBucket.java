package com.learn.coding.LLD.rateLimiters;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {
    private final int capacity;          // Max tokens in bucket
    private final int refillRate;        // Tokens per second
    private AtomicInteger tokens;        // Current tokens
    private long lastRefillTimestamp;    // Last refill time

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicInteger(capacity);
        this.lastRefillTimestamp = System.nanoTime();
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsedSeconds = (now - lastRefillTimestamp) / 1_000_000_000;
        if (elapsedSeconds > 0) {
            int newTokens = (int) (elapsedSeconds * refillRate);
            int currentTokens = Math.min(capacity, tokens.get() + newTokens);
            tokens.set(currentTokens);
            lastRefillTimestamp = now;
        }
    }

    public boolean tryConsume() {
        refill();
        if (tokens.get() > 0) {
            tokens.decrementAndGet();
            return true; // request allowed
        }
        return false; // request denied
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 2); // capacity=5, refill=2 tokens/sec

        for (int i = 0; i < 10; i++) {
            if (bucket.tryConsume()) {
                System.out.println("Request " + i + " allowed");
            } else {
                System.out.println("Request " + i + " denied");
            }
            Thread.sleep(300); // simulate requests every 300ms
        }
    }
}
