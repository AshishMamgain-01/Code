package com.learn.coding.dsa.greedy;

import java.util.Arrays;

public class CoinChange {
	
	/*
	You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
	Return the fewest number of coins that you need to make up that amount. 
	If that amount of money cannot be made up by any combination of the coins, return -1.
	You may assume that you have an infinite number of each kind of coin.
	 
	Example 1:
	Input: coins = [1,2,5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1
	Example 2:
	Input: coins = [2], amount = 3 Output: -1
	Example 3:
	Input: coins = [1], amount = 0 Output: 0
	 
	Constraints:
	1 <= coins.length <= 12
	1 <= coins[i] <= 231 - 1
	0 <= amount <= 104
	
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] coins = { 1, 2, 5 };
		int n = 11;
		// Explanation: 11 = 5 + 5 + 1

		int dp[] = new int[n + 1];

		Arrays.fill(dp, -1);

		int result = minCoinChange(coins, n, dp);
		System.out.println(result==Integer.MAX_VALUE ? -1 :result);

	}

	private static int minCoinChange(int[] coins, int n, int[] dp) {
		// TODO Auto-generated method stub

		if (n == 0)
			return 0;

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < coins.length; i++) {
			if (n - coins[i] >= 0) {
				int subAns = 0;
				if (dp[n - coins[i]] != -1) {
					subAns = dp[n - coins[i]];
				} else {
					subAns = minCoinChange(coins, n - coins[i], dp);
				}

				if (subAns != Integer.MAX_VALUE && subAns + 1 < ans) {
					ans = subAns + 1;
				}
			}
		}
		return dp[n] = ans;
	}

}
