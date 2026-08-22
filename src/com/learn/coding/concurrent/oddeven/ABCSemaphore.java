package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.Semaphore;

public class ABCSemaphore {
	private static final int TOTAL_TURNS = 10;

	private final Semaphore conditionA = new Semaphore(1);
	private final Semaphore conditionB = new Semaphore(0);
	private final Semaphore conditionC = new Semaphore(0);

	public void printA() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			try {
				conditionA.acquire();
				System.out.print("a");
				conditionB.release();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}

	public void printB() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			try {
				conditionB.acquire();
				System.out.print("b");
				conditionC.release();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}

	public void printC() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			try {
				conditionC.acquire();
				System.out.print("c");
				conditionA.release();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}
	}

	public static void main(String[] args) {
		ABCSemaphore printer = new ABCSemaphore();

		Thread threadA = new Thread(printer::printA);
		Thread threadB = new Thread(printer::printB);
		Thread threadC = new Thread(printer::printC);

		threadA.start();
		threadB.start();
		threadC.start();

	}
}
