package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSumVariation {

    public static int[] twoSumUsingMap(int arr[], int target) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            int requiredNumber = target - arr[i];
            if (map.containsKey(requiredNumber)) {
                return new int[]{map.get(requiredNumber), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }


    public static int[] twoSumTwoPointers(int[] nums, int target) {

		//this will work only whern array is sorted array
		int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        /*
         * Input: nums = [2, 7, 11, 15] target = 9
         * Output: [0, 1]
         */

        int[] arr = {2, 7, 11, 15};
        int target = 18;
        System.out.println(Arrays.toString(twoSumUsingMap(arr, target)));

        //sorted array for two pointer approach
        Arrays.sort(arr);
        System.out.println(Arrays.toString(twoSumTwoPointers(arr, target)));
    }
}
