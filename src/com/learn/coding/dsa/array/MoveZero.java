package com.learn.coding.dsa.array;

import java.util.Arrays;

public class MoveZero {
    /*
     * Given an array, move all zeroes to the end while keeping the relative order
     * of the non‑zero elements. Do this in‑place with minimal operations.
     */
    public static void main(String[] args) {

        int[] arrRight = {0, 1, 0, 10, 0, 2};
        moveZeroToRight(arrRight);
        System.out.println("arrRight : " + Arrays.toString(arrRight));

		int[] arrLeft = {0, 1, 0, 13, 0, 2};
		moveZeroToLeft(arrLeft);
		System.out.println("arrLeft : " + Arrays.toString(arrLeft));

        int[] arrRightPointer = {0, 1, 0, 12, 0, 2};
        moveZeroToRightTwoPointer(arrRightPointer);
        System.out.println("arrRightPointer : " + Arrays.toString(arrRightPointer));


        int[] arrLeftPointer = {0, 1, 0, 14, 0, 2};
        moveZeroToLeftTwoPointer(arrLeftPointer);
        System.out.println("arrLeftPointer : " + Arrays.toString(arrLeftPointer));

    }

    private static void moveZeroToRight(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index] = arr[i];
                index++;
            }
        }

        while (index < arr.length) {
            arr[index] = 0;
            index++;
        }
    }

	private static void moveZeroToLeft(int[] arr) {
		int pos = arr.length - 1;
		// position to place next non-zero

		// Step 1: write non-zeros from right to left
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] != 0) {
				arr[pos] = arr[i];
				pos--;
			}
		}

		// Step 2: fill remaining positions with zero
		while (pos >= 0) {
			arr[pos] = 0;
			pos--;
		}
	}

    private static void moveZeroToRightTwoPointer(int[] arr) {
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] != 0) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }
        }
    }

    private static void moveZeroToLeftTwoPointer(int[] arr) {

        int right = arr.length - 1;

        for (int left = arr.length - 1; left >= 0; left--) {
            if (arr[left] != 0) {
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                right--;
            }
        }

    }
}
