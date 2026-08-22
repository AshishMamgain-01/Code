package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class SemaphoreAtomicOddEvenPrinter {

	private final int MAX = 10;

	private final Semaphore odd = new Semaphore(1);
	private final Semaphore even = new Semaphore(0);
	private AtomicInteger number = new AtomicInteger();

	public void printOdd() {

		while (number.get() <= MAX) {

			try {
				odd.acquire();
				while ((number.get()) % 2 == 0) {
					even.release();
				}

				if (number.get() <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number.get());
					number.incrementAndGet();
					even.release();
				}
			} catch (InterruptedException e) {				
				Thread.currentThread().interrupt();
				return;
			}

		}
	}

	public void printEven() {

		while (number.get() <= MAX) {
			try {
				even.acquire();
				
				while(number.get() % 2 !=0) {
					odd.release();
				}
				
				if(number.get()<=MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number.get());
					number.incrementAndGet();
					odd.release();
				}

			}

			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}
		}

	}

}

public class PrintOddEvenWithSemaphoreAtomicCount {

	public static void main(String[] args) {
		ReentrantOddEvenPrinter printer = new ReentrantOddEvenPrinter();
		Thread odd = new Thread(printer::printOdd, "Odd Thread :");
		Thread even = new Thread(printer::printEven, "Even Thread :");

		odd.start();
		even.start();
	}

}
