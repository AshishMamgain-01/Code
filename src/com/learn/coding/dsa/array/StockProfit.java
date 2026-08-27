package com.learn.coding.dsa.array;

public class StockProfit {

    // Single transaction: Buy once, sell once
    // Function to calculate maximum profit using single pass
    public static int maxProfitSingle(int[] prices) {
        // Initialize the minimum price to a large number
        int minPrice = Integer.MAX_VALUE;

        // Initialize the maximum profit to 0
        int maxProfit = 0;

        // Traverse each price in the array
        for (int price : prices) {
            // If current price is less than minPrice, update minPrice
            if (price < minPrice) {
                minPrice = price;
            }
            // Else calculate profit and update maxProfit if it's greater
            else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        // Return the maximum profit found
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
