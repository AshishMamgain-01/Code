package com.learn.coding.dsa.array;

public class SecondLargest {

	public static void main(String[] args) {
		int[] nums = { 10, 20, 4, 45, 99 };
		System.out.println("Second Largest: " + findSecondLargest(nums));
		
	}

	private static int findSecondLargest(int[] nums) {
		
		if(nums.length<2) {			
			throw new IllegalArgumentException("Illegal Argument Exception");
		}
		
		int largest= Integer.MIN_VALUE;
		int secondLargest= Integer.MIN_VALUE;
		
		for(int i=0; i<nums.length;i++) {			
			if(nums[i]>largest) {
				secondLargest=largest;
				largest =nums[i];
			}
			else if(nums[i]>secondLargest &&  nums[i]<largest) {
				secondLargest =nums[i];
			}		
		}
		
		if(secondLargest == Integer.MIN_VALUE) {
			throw new RuntimeException("No second largest");
		}
		
		return secondLargest;
	}

}
