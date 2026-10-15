package com.learn.coding.dsa.array;

import java.util.*;

/*
 * Given an array of integers temperatures representing the daily temperatures, 
 * return an array answer such that answer[i] is the number of days you have to wait after 
 * the ith day to get a warmer temperature. If there is no future day for which this is possible, 
 * keep answer[i] == 0 instead.
   Example 1:
	Input: temperatures = [73,74,75,71,69,72,76,73]
	Output: [1,1,4,2,1,1,0,0]			
 * 
 */
public class DailyTemperatures {
	public static int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] answer = new int[n];
		Stack<Integer> stack = new Stack<>(); // stores indices

		for (int i = 0; i < n; i++) {
			// While current temp is greater than the temp at stack top
			while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
				int prevIndex = stack.pop();
				answer[prevIndex] = i - prevIndex; // days waited
			}
			stack.push(i);
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
		int[] result = dailyTemperatures(temperatures);
		System.out.println(Arrays.toString(result)); // [1,1,4,2,1,1,0,0]
	}
}
