package com.learn.coding.dsa.array;
import java.util.Random;

public class QuickSelectKthLargest {
    public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // kth largest is (n - k)th smallest in 0-based index
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) {
            return nums[left];
        }

        // choose random pivot
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == kSmallest) {
            return nums[pivotIndex];
        } else if (pivotIndex > kSmallest) {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        }
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
