package com.learn.coding.dsa.string;

public class ReverseString {

	

	private static String reverse(String input) {
		char[] ch = input.toCharArray();
		int left = 0;
		int right = input.length() - 1;

		while (left < right) {
			char temp = ch[left];
			ch[left] = ch[right];
			ch[right] = temp;
			left++;
			right--;
		}
		return new String(ch);
	}
	
	public static void main(String[] args) {
		// Reverse a string without using inbuilt method or java8 features in O(n) time
		// and no extra space

		String input = "Ashish";
		System.out.println("Original: " + input);
		System.out.println("Reversed: " + reverse(input));
	}

}
