package com.learn.coding.dsa.tree;

public class BasicBinarySearch {
	
	    public static int search(int []nums, int target) {
	        // Write your code here.
	        int left = 0;
	        int right = nums.length-1;
	        while(left<=right){
	            int mid = left + (right-left)/2;
	            if(nums[mid]==target){
	                return mid;
	            }
	            if(target>=nums[left] && target<nums[mid]){
	                right = mid-1;
	            }
	            else{
	                left = mid +1;
	            } 
	        }
	        return -1;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
