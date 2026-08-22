package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantOddEvenPrinter {
	public final int MAX = 10;
	int number = 1;

	public final ReentrantLock lock = new ReentrantLock();
	public final Condition odd = lock.newCondition();
	public final Condition even = lock.newCondition();

	public void printOdd() {
		lock.lock();
		try {
			while (number <= MAX) {

				while (number % 2 == 0) {
					odd.await();
				}

				if (number <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					even.signal();
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			
			return;
		} finally {
			lock.unlock();
		}

	}

	public void printEven() {

		lock.lock();
		try {
			while (number <= MAX) {

				while (number % 2 != 0) {
					even.await();
				}

				if (number <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					odd.signal();
				}
			}

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		} finally {
			lock.unlock();
		}

	}
}

public class PrintOddEvenWithReentrantLock {

	public static void main(String[] args) {
	
		ReentrantOddEvenPrinter printer = new ReentrantOddEvenPrinter();
		Thread odd = new Thread(printer::printOdd, "Odd Thread :");
		Thread even = new Thread(printer::printEven, "Even Thread :");

		odd.start();
		even.start();

	}

}
