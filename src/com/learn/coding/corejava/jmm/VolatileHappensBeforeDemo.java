package com.learn.coding.corejava.jmm;

/**
 * Without volatile, the reader thread may never see the writer's write
 * (cached value / allowed reorder). With volatile, the write happens-before
 * the subsequent read.
 *
 * i++ on a volatile int is still not atomic — use AtomicInteger for counters.
 */
public class VolatileHappensBeforeDemo {

	private static boolean stopWithoutVolatile;
	private static volatile boolean stopWithVolatile;

	public static void main(String[] args) throws InterruptedException {
		demoVolatileStopFlag();
		System.out.println("volatile guarantees visibility, not atomic compound updates.");
	}

	private static void demoVolatileStopFlag() throws InterruptedException {
		stopWithVolatile = false;
		Thread reader = new Thread(() -> {
			while (!stopWithVolatile) {
				// spin
			}
			System.out.println("reader saw volatile stop flag");
		});
		reader.start();
		Thread.sleep(50);
		stopWithVolatile = true;
		reader.join(1_000);
		if (reader.isAlive()) {
			System.out.println("unexpected: reader stuck");
			reader.interrupt();
		}

		stopWithoutVolatile = false;
		Thread maybeStuck = new Thread(() -> {
			while (!stopWithoutVolatile) {
				// JIT may hoist this read — might spin forever
			}
			System.out.println("reader saw non-volatile stop (not guaranteed)");
		});
		maybeStuck.setDaemon(true);
		maybeStuck.start();
		Thread.sleep(50);
		stopWithoutVolatile = true;
		maybeStuck.join(300);
		if (maybeStuck.isAlive()) {
			System.out.println("non-volatile flag: reader still spinning after 300ms (visibility failure).");
		}
	}
}
