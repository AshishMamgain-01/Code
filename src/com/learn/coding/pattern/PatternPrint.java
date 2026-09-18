package com.learn.coding.pattern;

public class PatternPrint {

	/*
	 * 1 
	 * 121 
	 * 12321 
	 * 1234321 
	 * 123454321
	 */
	public static void main(String[] args) {
		int row = 5;

		for (int i = 1; i <= row; i++) {
			// increasing part
			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}
			// decreasing part
			for (int k = i-1; k > 0; k--) {
				System.out.print(k);
			}
			System.out.println();
		}
	}
	
	
}
