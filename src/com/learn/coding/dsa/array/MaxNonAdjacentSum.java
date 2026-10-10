package com.learn.coding.dsa.array;

public class MaxNonAdjacentSum {
    public static int findMaxSum(int[] arr) {
        int incl = arr[0]; // include first element
        int excl = 0;      // exclude first element

        for (int i = 1; i < arr.length; i++) {
            int newIncl = excl + arr[i];   // include current
            int newExcl = Math.max(incl, excl); // exclude current

            incl = newIncl;
            excl = newExcl;
        }

        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 7, 10};
        System.out.println("Maximum sum of non-adjacent elements: " + findMaxSum(arr));
    }
}

