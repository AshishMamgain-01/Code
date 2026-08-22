package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.Semaphore;

class SemaphoreOddEvenPrinter {

	int MAX = 10;
	int number = 1;
	public Semaphore odd = new Semaphore(1);
	public Semaphore even = new Semaphore(0);

	public void printOdd() {

		while (number <= MAX) {
			try {
				odd.acquire();
				while (number % 2 == 0) {
					even.release();
				}

				if (number <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					even.release();
				}

			} catch (InterruptedException e) {
				// TODO: handle exception
				Thread.currentThread().interrupt();
			}

		}
	}

	public void printEven() {

		while (number <= MAX) {
			try {
				even.acquire();
				while (number % 2 != 0) {
					odd.release();
				}
				if (number <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					odd.release();
				}
			} catch (InterruptedException e) {
				// TODO: handle exception
				Thread.currentThread().interrupt();
			}

		}

	}

}

public class PrintOddEvenWithSemaphore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SemaphoreOddEvenPrinter printer = new SemaphoreOddEvenPrinter();
		
		Thread odd = new Thread(printer::printOdd, "Odd Thread : ");
		Thread even = new Thread(printer::printEven, "Even Thread : ");
		
		odd.start();
		even.start();
		
	}

}
