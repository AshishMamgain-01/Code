package com.learn.coding.dsa.array;

public class MaximumSubArraySum {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray(arr);//tuf easy
        // getMaxSubarray(arr);
        //getMaxSumSubarray(arr);

    }

    // Function to find maximum sum of subarrays
    public static void maxSubArray(int[] nums) {

        // Maximum sum
        long maxSum = Long.MIN_VALUE;

        // Current sum of subarray
        long sum = 0;

        //IF subarray needed
        int startIndex = 0;
        int endIndex = 0;
        int tempIndex = 0;

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {

            // Add current element to the sum
            sum += nums[i];

            // Update maxi if current sum is greater
            if (sum > maxSum) {
                maxSum = sum;
                startIndex = tempIndex;
                endIndex = i;
            }

            // Reset sum to 0 if it becomes negative
            if (sum < 0) {
                sum = 0;
                tempIndex = i + 1; // start of next segment
            }
        }

        // Return the maximum subarray sum found
        System.out.print("Sum is : " + maxSum + "\nSubarray : [");
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print("" + nums[i] + (i < endIndex ? ", " : ""));
        }
        System.out.print("]");
    }


    private static void getMaxSubarray(int[] arr) {

        int currentSum = arr[0];
        int maxSum = arr[0];

        int startIndex = 0;
        int endIndex = 0;
        int tempStart = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i];
                tempStart = i;
            } else {
                currentSum = currentSum + arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                startIndex = tempStart;
                endIndex = i;
            }
        }

        System.out.println("MaxSum:" + maxSum + " start : " + startIndex + " end: " + endIndex);
        System.out.print("Subarray : [");
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print("" + arr[i] + (i < endIndex ? ", " : ""));
        }
        System.out.print("]");

    }

    private static int getMaxSumSubarray(int[] arr) {
        int maxSum = arr[0];
        int currentSum = arr[0];

        for (Integer item : arr) {
            currentSum = Math.max(item, currentSum + item);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

}
