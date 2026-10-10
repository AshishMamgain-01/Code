package com.learn.coding.basic;

public class fibonacci {

	public static void printFibonacci(int n) {
		
		System.out.println("Fibonacci Series : ");
		if(n<=0) {
			System.out.println("Invalid input");
			return;
		}
		int first = 0;
		int second = 1;
		if (n == 1) {
			System.out.print(first);
			return;
		}

		System.out.print(first + " " + second);

		for (int i = 3; i <= n; i++) {
			int next = first + second;
			System.out.print(" " + next);
			first =second;
			second =next;
		}

	}

	public static void main(String[] args) {
		printFibonacci(1);
	}

}
