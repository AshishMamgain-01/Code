package com.learn.coding.dsa.array;

public class SecondLargest {

    public static void main(String[] args) {
        int[] nums = {10, 20, 4, 45, 99};
		findSecondLargest(nums);

    }

    private static void findSecondLargest(int[] nums) {

        if (nums.length < 2) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }

        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (Integer num : nums) {
            if (num > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = num;
            } else if (num > secondLargest && num < firstLargest) {
                secondLargest = num;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("No valid second largest element");
        } else {
            System.out.println("First Largest : " + firstLargest);
            System.out.println("Second Largest : " + secondLargest);
        }
    }

}
