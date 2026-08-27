package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSumAllPairs {

    public static List<int[]> allTwoSumUsingMap(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int requiredNumber = target - arr[i];
            if (map.containsKey(requiredNumber)) {
                result.add(new int[]{map.get(requiredNumber), i});
            }
            map.put(arr[i], i);
        }
        return result;
    }

    public static List<int[]> allTwoSumTwoPointers(int[] nums, int target) {
        List<int[]> result = new ArrayList<>();
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result.add(new int[]{left, right});
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] arr = {2, 7, 11, 15, -2, 9};
        int target = 9;
        System.out.println("Initial Array : "+Arrays.toString(arr) + " target : "+target);

        System.out.println("After Map approach:");
        for (int[] pair : allTwoSumUsingMap(arr, target)) {
            System.out.println(Arrays.toString(pair));
        }

        Arrays.sort(arr); // Required for two-pointer
        System.out.println("Initial Array : "+Arrays.toString(arr) + " target : "+target);
        System.out.println("Two-pointer approach:");
        for (int[] pair : allTwoSumTwoPointers(arr, target)) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
