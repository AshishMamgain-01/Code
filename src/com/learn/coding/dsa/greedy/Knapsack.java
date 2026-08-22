package com.learn.coding.dsa.greedy;

public class Knapsack {
	static int knapsack(int[] weight, int[] value, int n, int maxWeight, int[][] dp) {
		
		//if item is zero or bag carry weight zero
		if (maxWeight == 0 || n == 0) {
			return 0;
		}

		if (dp[n][maxWeight] != -1) {
			return dp[n][maxWeight];
		}

		int pick = 0;
		if (weight[n - 1] <= maxWeight) {
			pick = value[n - 1] + knapsack(weight, value, n - 1, maxWeight - weight[n - 1], dp);
		}

		int notPick = knapsack(weight, value, n - 1, maxWeight, dp);
		return dp[n][maxWeight] = Math.max(pick, notPick);
	}

	static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

		int dp[][] = new int[n + 1][maxWeight + 1];
		return knapsack(weight, value, n, maxWeight, dp);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
