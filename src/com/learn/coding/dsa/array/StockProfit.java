package com.learn.coding.dsa.array;

public class StockProfit {

    // Single transaction: Buy once, sell once
    public static int maxProfitSingle(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }

    // Multiple transactions: Buy and sell many times previous value should be known to get max profit
    public static int maxProfitMultiple(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        int singleProfit = maxProfitSingle(prices);
        int multipleProfit = maxProfitMultiple(prices);

        System.out.println("Prices: ");
        for (int p : prices) {
            System.out.print(p + " ");
        }
        System.out.println("\n");

        System.out.println("Max Profit (Single Transaction): " + singleProfit);
        System.out.println("Max Profit (Multiple Transactions): " + multipleProfit);
    }
}
