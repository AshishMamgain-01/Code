package com.learn.coding.concurrent.oddeven;

class WaitNotifyOddEvenPrinter {

	int MAX = 10;
	int number = 1;

	public synchronized void printOdd() {

		while (number <= MAX) {

			try {
				while (number % 2 == 0) {
					wait();
				}

				if (number <= MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					notifyAll();
				}

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}

	}

	public synchronized void printEven() {

		while (number <= MAX) {
			try {
				while(number%2!=0){
					wait();					
				}
				
				if(number<=MAX) {
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					notifyAll();
				}
				
			} catch (InterruptedException ex) {
				// TODO: handle exception
				
				Thread.currentThread().interrupt();
			}
		}
	}

}

public class PrintOddEvenWithWaitNotify {

	/*
	 * 
	 * 1- odd even
	 * 
	 * wait()/notify() Semaphore Lock + Condition AtomicInteger + coordination
	 * 
	 * 
	 * Product Consumer wait()/notify() Semaphore Lock + Condition AtomicInteger +
	 * coordination
	 * 
	 * Blocking queue
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WaitNotifyOddEvenPrinter printer = new WaitNotifyOddEvenPrinter();

		Thread odd = new Thread(printer::printOdd, "OddThread");
		Thread even = new Thread(printer::printEven, "EvenThread");

		odd.start();
		even.start();

	}

}
