package com.learn.coding.concurrent.oddeven;

public class ABCWaitNotify {

	private static final int TOTAL_TURNS = 10;
	private int state = 0;

	public synchronized void printA() {
		for (int i = 0; i <= TOTAL_TURNS; i++) {
			while (state != 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}

			System.out.print("a");
			state = 1;
			notifyAll();
		}
	}

	public synchronized void printB() {
		for (int i = 0; i <= TOTAL_TURNS; i++) {
			while (state != 1) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}

			System.out.print("b");
			state = 2;
			notifyAll();
		}
	}

	public synchronized void printC() {
		for (int i = 0; i <= TOTAL_TURNS; i++) {
			while (state != 2) {
				try {
					wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
			}
			System.out.print("c");
			state = 0;
			notifyAll();
		}
	}

	public static void main(String[] args) {
		ABCWaitNotify printer = new ABCWaitNotify();

		Thread threadA = new Thread(printer::printA);
		Thread threadB = new Thread(printer::printB);
		Thread threadC = new Thread(printer::printC);

		threadA.start();
		threadB.start();
		threadC.start();

	}
}
