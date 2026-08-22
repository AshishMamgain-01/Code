package com.learn.coding.dsa.greedy;

import java.util.Arrays;

public class CoinChange {

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
