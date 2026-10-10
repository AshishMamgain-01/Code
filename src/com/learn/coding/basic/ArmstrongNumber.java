package com.learn.coding.basic;

public class ArmstrongNumber {

	public static boolean isArmstrongNumber(int n) {
		int digits= 0;
		int temp =n;
		int original =n;
		
		//first find number of digit in number
		while(temp>0) {
			digits++;
			temp = temp/10;
		}
		
		temp =n;
		int sum =0;
		while(temp>0) {
			int digit = temp%10;
			sum = sum + (int) Math.pow(digit,digits);
			temp = temp/10;
		}
		
		if (sum == original) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(isArmstrongNumber(100));
	}

}
