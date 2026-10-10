package com.learn.coding.dsa.string;

public class LongestPalindrome {

	public static void main(String args[]) {

		System.out.println(findLongestPalindrome("babad"));
	}

	private static String findLongestPalindrome(String input) {

		if (input.length() < 2) {
			return input;
		}

		int start = 0;
		int end = 0;

		for (int i = 0; i < input.length(); i++) {
			int len1 = expandFromCenter(input, i, i);
			int len2 = expandFromCenter(input, i, i + 1);

			int len = Math.max(len1, len2);
			if (len > end - start + 1) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}

		}

		return input.substring(start, end + 1);

	}

	private static int expandFromCenter(String input, int left, int right) {

		while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}

}
