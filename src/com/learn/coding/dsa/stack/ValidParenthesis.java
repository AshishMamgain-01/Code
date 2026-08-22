package com.learn.coding.dsa.stack;

import java.util.ArrayDeque;

public class ValidParenthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isValid("()")); // true
		System.out.println(isValid("()[]{}")); // true
		System.out.println(isValid("(]")); // false
		System.out.println(isValid("([)]")); // false
		System.out.println(isValid("{[]}")); // true

	}

	private static boolean isValid(String string) {

		ArrayDeque<Character> stack = new ArrayDeque<Character>();

		for (Character ch : string.toCharArray()) {
			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {
				if (stack.isEmpty())
					return false;
				Character top = stack.pop();
				if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
}
