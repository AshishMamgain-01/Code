package com.learn.coding.LLD.rateLimiters;

import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {

	private final int capacity;

	private final AtomicInteger tokens;

	public TokenBucket(int capacity) {

		this.capacity = capacity;

		this.tokens = new AtomicInteger(capacity);
	}

	public synchronized boolean allow() {

		if (tokens.get() > 0) {

			tokens.decrementAndGet();

			return true;
		}

		return false;
	}

	public synchronized void refill() {

		tokens.set(capacity);
	}

	public static void main(String[] args) {

		TokenBucket bucket = new TokenBucket(5);

		for (int i = 1; i <= 7; i++) {

			System.out.println(bucket.allow());
		}

		System.out.println("Refilling...");

		bucket.refill();

		System.out.println(bucket.allow());
	}
}