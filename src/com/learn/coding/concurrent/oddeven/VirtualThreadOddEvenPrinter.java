package com.learn.coding.concurrent.oddeven;

public class VirtualThreadOddEvenPrinter {

	private final int MAX = 10;
	int num =1;
	
	private synchronized void printEven() {
		
		while(num<=10) {
			try {
				while(num%2!=0) {
					wait();
				}
				
				if(num<=MAX) {
					System.out.println("Print Even:" +num);
					num++;
					notifyAll();					
				}
			}
			catch (Exception e) {
				Thread.currentThread().interrupt();
				return;
			}
			
		}
		
	}
	
private synchronized void printOdd() {
		
		while(num<=10) {
			try {
				while(num%2==0) {
					wait();
				}
				
				if(num<=MAX) {
					System.out.println("Print Odd:" +num);
					num++;
					notifyAll();					
				}
			}
			catch (Exception e) {
				Thread.currentThread().interrupt();
				return;
			}
			
		}
		
	}
	public static void main(String[] args) {
		VirtualThreadOddEvenPrinter obj = new VirtualThreadOddEvenPrinter();
		Thread even = Thread.ofVirtual().start(obj::printEven);
		Thread odd = Thread.ofVirtual().start(obj::printOdd);
		
		try {
			odd.join();
			even.join();
		}
		catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		
		

	}

}
