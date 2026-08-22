package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABCRentrant {
	private static final int TOTAL_TURNS = 10;
	private int state = 0;

	private final ReentrantLock lock = new ReentrantLock();

	private final Condition conditionA = lock.newCondition();
	private final Condition conditionB = lock.newCondition();
	private final Condition conditionC = lock.newCondition();

	public void printA() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			lock.lock();
			try {
				while (state != 0) {
					conditionA.await();
				}
				System.out.print("a");
				state = 1;
				conditionB.signal();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			} finally {
				lock.unlock();
			}
		}
	}

	public void printB() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			lock.lock();
			try {
				while (state != 1) {
					conditionB.await();
				}
				System.out.print("b");
				state = 2;
				conditionC.signal();
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
				return;
			} finally {
				lock.unlock();
			}
		}
	}

	public void printC() {
		for (int i = 0; i < TOTAL_TURNS; i++) {
			lock.lock();
			try {
				while (state != 2) {
					conditionC.await();
				}
				System.out.print("c");
				state = 0;
				conditionA.signal();
			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
				return;
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ABCRentrant printer = new ABCRentrant();

		Thread threadA = new Thread(printer::printA);
		Thread threadB = new Thread(printer::printB);
		Thread threadC = new Thread(printer::printC);

		threadA.start();
		threadB.start();
		threadC.start();

	}
}
