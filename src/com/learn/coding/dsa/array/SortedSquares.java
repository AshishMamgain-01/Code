package com.learn.coding.dsa.array;

import java.util.Arrays;

public class SortedSquares {

    //sorted array sorted square
	public static int[] sortedSquaresExtraSpace(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		int[] result = new int[arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {
			int leftSquare = arr[left] * arr[left];
			int rightSquare = arr[right] * arr[right];

			if (leftSquare > rightSquare) {
				result[i] = leftSquare;
				left++;
			} else {
				result[i] = rightSquare;
				right--;
			}
		}

		return result;
	}
	// Cleaner version using a position pointer
    public static void sortedSquaresOptimizedClean(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;

        for (int pos = n - 1; pos >= 0; pos--) {
            if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                arr[pos] = arr[left] * arr[left];
                left++;
            } else {
                arr[pos] = arr[right] * arr[right];
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {-7, -3, 2, 3, 11};
        //arr= sortedSquaresExtraSpace(arr);
        System.out.println("Sorted squares (extra space): " + Arrays.toString(arr));
        
        int[] arr1 = {-7, -3, 2, 3, 11};
        sortedSquaresOptimizedClean(arr1);
        System.out.println("Sorted squares (in-place): " + Arrays.toString(arr1));
    }
	

}
