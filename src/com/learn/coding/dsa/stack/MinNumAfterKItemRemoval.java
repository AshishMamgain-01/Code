package com.learn.coding.dsa.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinNumAfterKItemRemoval {

	/*
	 * 
	 * You are given:
	 * 
	 * A numeric string (or integer) num
	 * 
	 * An integer k
	 * 
	 * Your task: remove exactly k digits from num so that the resulting number is
	 * the smallest possible.
	 * 
	 * You cannot reorder digits — only remove.
	 * 
	 * Leading zeros should be handled (final answer should not have unnecessary
	 * leading zeros).
	 * 
	 * If all digits are removed, return "0".
	 * 
	 * 
	 */

	public static void main(String[] args) {
		// Example test cases
		System.out.println(removeKDigits("1432219", 3)); // Output: 1219
		System.out.println(removeKDigits("10200", 1)); // Output: 200
		System.out.println(removeKDigits("10", 2)); // Output: 0
	}

	private static String removeKDigits(String numberString, int k) {
		// TODO Auto-generated method stub

		// Use a stack (Deque) to build the smallest number
		Deque<Character> stack = new ArrayDeque<Character>();

		// Traverse each digit in the number
		for (char digit : numberString.toCharArray()) {
			// While the stack is not empty, we still have removals left,
			// and the top of the stack is greater than the current digit:
			// → pop from stack (remove larger digit to make number smaller)
			while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
				stack.pollLast();
				k--;
			}
			// Push the current digit into the stack
			stack.addLast(digit);

		}

		// If we still need to remove digits (k > 0), remove from the end
		if (k > 0 && !stack.isEmpty()) {
			stack.pollLast();
			k--;
		}
		// Build the result string from the stack
		StringBuilder sb = new StringBuilder();
		for (char ch : stack) {
			sb.append(ch);
		}

		 // Remove leading zeros
		while (sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}

		// If result is empty, return "0"
		System.out.println("Stack" + stack);
		return sb.length() == 0 ? "0" : sb.toString();
	}

}
