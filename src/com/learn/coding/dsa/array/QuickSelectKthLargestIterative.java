package com.learn.coding.dsa.array;
import java.util.Random;

public class QuickSelectKthLargestIterative {
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int target = n - k; // kth largest = (n-k)th smallest
        int left = 0, right = n - 1;
        Random rand = new Random();

        while (left <= right) {
            // choose random pivot
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int newPivotIndex = partition(nums, left, right, pivotIndex);

            if (newPivotIndex == target) {
                return nums[newPivotIndex];
            } else if (newPivotIndex > target) {
                right = newPivotIndex - 1; // search left side
            } else {
                left = newPivotIndex + 1;  // search right side
            }
        }
        throw new RuntimeException("Unexpected state");
    }

    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right); // move pivot to end
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right); // move pivot to final place
        return storeIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(findKthLargest(nums, k)); // Output: 3
    }
}
