package com.learn.coding.dsa.array;

public class MinSubarrayGreaterThanX {
    public static void minSubArray(int[] nums, int X) {
        int n = nums.length;
        int sum = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0, minEnd = 0;

        for (int end = 0; end < n; end++) {
            sum += nums[end];

            // shrink window while sum > X
            while (sum > X) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    minStart = start;
                    minEnd = end;
                }
                sum -= nums[start];
                start++;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            System.out.println("No subarray found with sum > " + X);
        } else {
            System.out.print("Smallest subarray length: " + minLen + "\nSubarray: [");
            for (int i = minStart; i <= minEnd; i++) {
                System.out.print(nums[i] + (i < minEnd ? ", " : ""));
            }
            System.out.print("]");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 0, 19};
        int X = 51;
        minSubArray(arr, X);
    }
}

