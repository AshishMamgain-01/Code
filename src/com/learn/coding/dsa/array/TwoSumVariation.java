package com.learn.coding.dsa.array;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumVariation {

	public static int[] twoSum(int arr[], int target) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int requiredNumber = target - arr[i];
			if (map.containsKey(requiredNumber)) {
				return new int[] { map.get(requiredNumber), i };
			}
			map.put(arr[i], i);
		}
		return new int[] { -1, -1 };
	}

	public static int[] twoSumSorted(int[] nums, int target) {

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
		// TODO Auto-generated method stub

		/*
		 * Input: nums = [2, 7, 11, 15] target = 9
		 * 
		 * Output: [0, 1]
		 */

		int[] arr = { 2, 7, 11, 15 };

		int target = 18;

		System.out.println(Arrays.toString(twoSum(arr, target)));
		System.out.println(Arrays.toString(twoSumSorted(arr, target)));
	}
	
	

}
