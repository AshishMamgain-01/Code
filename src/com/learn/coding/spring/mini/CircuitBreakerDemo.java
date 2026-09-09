package com.learn.coding.spring.mini;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tiny circuit breaker (CLOSED / OPEN / HALF_OPEN). Same idea as Resilience4j
 * / Spring Cloud, without those JARs. Story: stop hammering a dead collector.
 *
 * See interview-prep/microservices/Microservices.md
 */
public class CircuitBreakerDemo {

	public static void main(String[] args) {
		FlakyCollector collector = new FlakyCollector(4);
		CircuitBreaker br = new CircuitBreaker(3, Duration.ofMillis(50));

		for (int i = 1; i <= 8; i++) {
			try {
				String result = br.call(() -> collector.collect("NCS1004"));
				System.out.println("call " + i + " ok: " + result + " state=" + br.state());
			} catch (Exception e) {
				System.out.println("call " + i + " fail: " + e.getMessage() + " state=" + br.state());
			}
			if (i == 5) {
				sleep(80);
			}
		}
	}

	enum State {
		CLOSED, OPEN, HALF_OPEN
	}

	@FunctionalInterface
	interface Action {
		String run() throws Exception;
	}

	static final class CircuitBreaker {
		private final int failureThreshold;
		private final Duration openFor;
		private final AtomicInteger failures = new AtomicInteger();
		private volatile State state = State.CLOSED;
		private volatile Instant openedAt = Instant.EPOCH;

		CircuitBreaker(int failureThreshold, Duration openFor) {
			this.failureThreshold = failureThreshold;
			this.openFor = openFor;
		}

		State state() {
			return state;
		}

		String call(Action action) throws Exception {
			if (state == State.OPEN) {
				if (Instant.now().isBefore(openedAt.plus(openFor))) {
					throw new IllegalStateException("circuit OPEN — fail fast");
				}
				state = State.HALF_OPEN;
			}
			try {
				String result = action.run();
				failures.set(0);
				state = State.CLOSED;
				return result;
			} catch (Exception e) {
				int n = failures.incrementAndGet();
				if (n >= failureThreshold || state == State.HALF_OPEN) {
					state = State.OPEN;
					openedAt = Instant.now();
				}
				throw e;
			}
		}
	}

	static final class FlakyCollector {
		private final int failUntil;
		private int attempts;

		FlakyCollector(int failUntil) {
			this.failUntil = failUntil;
		}

		String collect(String id) {
			attempts++;
			if (attempts <= failUntil) {
				throw new IllegalStateException("collector timeout " + id);
			}
			return "ok:" + id;
		}
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
