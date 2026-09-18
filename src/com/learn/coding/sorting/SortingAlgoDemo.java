package com.learn.coding.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingAlgoDemo {

	/*
	 * Bubble Sort
	 * Idea: Repeatedly swap adjacent elements if they are in the wrong order.
	 *
	 * Steps:
	 * 1. Start from the left and compare adjacent pairs.
	 * 2. Swap if left > right.
	 * 3. The largest element bubbles to the end after each pass.
	 * 4. Repeat until a pass makes no swaps.
	 *
	 * Complexity: O(n^2) average/worst, O(n) best with the early-exit check.
	 * Stable: Yes.
	 * Limitations: Too slow for large datasets.
	 * Use case: Teaching sorting basics; very small arrays.
	 */
	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			boolean swapped = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}

	/*
	 * Selection Sort
	 * Idea: Select the smallest remaining element and place it at the next
	 * unsorted position.
	 *
	 * Steps:
	 * 1. Find the minimum in the unsorted part.
	 * 2. Swap it with the first unsorted element.
	 * 3. Repeat for the remaining positions.
	 *
	 * Complexity: Always O(n^2).
	 * Stable: No.
	 * Limitations: Always quadratic, even on sorted input.
	 * Use case: When swaps are expensive but comparisons are cheap.
	 */
	public static void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/*
	 * Insertion Sort
	 * Idea: Build a sorted prefix one element at a time.
	 *
	 * Steps:
	 * 1. Take the next element as the key.
	 * 2. Shift larger elements in the sorted prefix one position right.
	 * 3. Insert the key into its correct position.
	 *
	 * Complexity: O(n^2) worst, O(n) on nearly sorted data.
	 * Stable: Yes.
	 * Limitations: Slow for large, random arrays.
	 * Use case: Small arrays or nearly sorted input.
	 */
	public static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	/*
	 * Merge Sort
	 * Idea: Divide the array into halves, sort each half, then merge.
	 *
	 * Steps:
	 * 1. Split the array into two halves.
	 * 2. Recursively sort each half.
	 * 3. Merge the two sorted halves.
	 *
	 * Complexity: O(n log n) always.
	 * Stable: Yes.
	 * Limitations: Needs O(n) extra space.
	 * Use case: Large datasets, stable sort, linked lists.
	 */
	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	private static void merge(int[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int i = 0; i < n1; i++) {
			L[i] = arr[left + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = arr[mid + 1 + j];
		}

		int i = 0, j = 0, k = left;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
			}
		}
		while (i < n1) {
			arr[k++] = L[i++];
		}
		while (j < n2) {
			arr[k++] = R[j++];
		}
	}

	/*
	 * Quick Sort
	 * Idea: Partition around a pivot, then recursively sort the partitions.
	 *
	 * Steps:
	 * 1. Choose a pivot (here: last element).
	 * 2. Rearrange so smaller-or-equal values go left, larger go right.
	 * 3. Recursively sort left and right partitions.
	 *
	 * Complexity: O(n log n) average, O(n^2) worst if pivot choice is poor.
	 * Stable: No.
	 * Limitations: Worst case on already sorted data with a naive pivot.
	 * Use case: Fast in-place sort; common in libraries.
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
	}

	/*
	 * Heap Sort
	 * Idea: Build a max heap and repeatedly extract the maximum.
	 *
	 * Steps:
	 * 1. Build a max heap.
	 * 2. Swap the root with the last heap element.
	 * 3. Reduce heap size and heapify the root.
	 * 4. Repeat until the array is sorted.
	 *
	 * Complexity: O(n log n) always.
	 * Stable: No.
	 * Limitations: Usually slower than Quick Sort in practice.
	 * Use case: In-place guaranteed O(n log n) when stability is not required.
	 */
	public static void heapSort(int[] arr) {
		int n = arr.length;

		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}

		for (int i = n - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		}
	}

	private static void heapify(int[] arr, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && arr[left] > arr[largest]) {
			largest = left;
		}
		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}

		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			heapify(arr, n, largest);
		}
	}

	/*
	 * Counting Sort
	 * Idea: Count how many times each value appears, then write them in order.
	 *
	 * Steps:
	 * 1. Count the frequency of each value (0..k).
	 * 2. Convert counts into prefix sums (positions).
	 * 3. Place elements into an output array from the end (stable).
	 * 4. Copy the output back into the original array.
	 *
	 * Complexity: O(n + k), where k is the maximum value.
	 * Stable: Yes.
	 * Limitations: Only non-negative integers in a limited range.
	 * Use case: Integers, characters, or IDs with a small value range.
	 */
	public static void countingSort(int[] arr, int k) {
		int n = arr.length;
		int[] count = new int[k + 1];
		int[] output = new int[n];

		for (int i = 0; i < n; i++) {
			count[arr[i]]++;
		}

		for (int i = 1; i <= k; i++) {
			count[i] += count[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}

		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	/*
	 * Radix Sort
	 * Idea: Sort numbers digit by digit with a stable subroutine (counting sort).
	 *
	 * Steps:
	 * 1. Find the maximum value to know how many digits to process.
	 * 2. Sort by the least significant digit using counting sort.
	 * 3. Repeat for each more significant digit.
	 *
	 * Complexity: O(n * d), where d is the number of digits.
	 * Stable: Yes (because the digit sort is stable).
	 * Limitations: Integers (or strings); needs a stable digit sort.
	 * Use case: Large integers, strings, phone numbers.
	 */
	public static void radixSort(int[] arr) {
		int max = getMax(arr);

		for (int exp = 1; max / exp > 0; exp *= 10) {
			countingSortByDigit(arr, exp);
		}
	}

	private static int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	private static void countingSortByDigit(int[] arr, int exp) {
		int n = arr.length;
		int[] output = new int[n];
		int[] count = new int[10];

		for (int i = 0; i < n; i++) {
			int digit = (arr[i] / exp) % 10;
			count[digit]++;
		}

		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {
			int digit = (arr[i] / exp) % 10;
			output[count[digit] - 1] = arr[i];
			count[digit]--;
		}

		for (int i = 0; i < n; i++) {
			arr[i] = output[i];
		}
	}

	/*
	 * Bucket Sort
	 * Idea: Distribute values into buckets, sort each bucket, then concatenate.
	 *
	 * Steps:
	 * 1. Create n buckets.
	 * 2. Place each value into a bucket based on its range (assumes [0, 1)).
	 * 3. Sort each bucket individually.
	 * 4. Concatenate the buckets back into the array.
	 *
	 * Complexity: O(n) average on uniform data, O(n^2) worst.
	 * Stable: Depends on the per-bucket sort.
	 * Limitations: Sensitive to distribution; input assumed in [0, 1).
	 * Use case: Uniformly distributed floating-point values.
	 */
	@SuppressWarnings("unchecked")
	public static void bucketSort(float[] arr) {
		int n = arr.length;
		if (n <= 0) {
			return;
		}

		List<Float>[] buckets = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			buckets[i] = new ArrayList<>();
		}

		for (float num : arr) {
			int bucketIndex = (int) (n * num);
			buckets[bucketIndex].add(num);
		}

		for (List<Float> bucket : buckets) {
			Collections.sort(bucket);
		}

		int index = 0;
		for (List<Float> bucket : buckets) {
			for (float num : bucket) {
				arr[index++] = num;
			}
		}
	}

	private static int[] copyOf(int[] source) {
		return Arrays.copyOf(source, source.length);
	}

	private static int maxValue(int[] arr) {
		int max = arr[0];
		for (int value : arr) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] original = { 64, 34, 25, 12, 22, 11, 90 };
		System.out.println("Original: " + Arrays.toString(original));

		int[] bubble = copyOf(original);
		bubbleSort(bubble);
		System.out.println("Bubble Sort:    " + Arrays.toString(bubble));

		int[] selection = copyOf(original);
		selectionSort(selection);
		System.out.println("Selection Sort: " + Arrays.toString(selection));

		int[] insertion = copyOf(original);
		insertionSort(insertion);
		System.out.println("Insertion Sort: " + Arrays.toString(insertion));

		int[] merge = copyOf(original);
		mergeSort(merge, 0, merge.length - 1);
		System.out.println("Merge Sort:     " + Arrays.toString(merge));

		int[] quick = copyOf(original);
		quickSort(quick, 0, quick.length - 1);
		System.out.println("Quick Sort:     " + Arrays.toString(quick));

		int[] heap = copyOf(original);
		heapSort(heap);
		System.out.println("Heap Sort:      " + Arrays.toString(heap));

		int[] counting = copyOf(original);
		countingSort(counting, maxValue(counting));
		System.out.println("Counting Sort:  " + Arrays.toString(counting));

		int[] radix = copyOf(original);
		radixSort(radix);
		System.out.println("Radix Sort:     " + Arrays.toString(radix));

		float[] bucketInput = { 0.42f, 0.32f, 0.23f, 0.52f, 0.25f, 0.47f, 0.51f };
		System.out.println("Bucket original: " + Arrays.toString(bucketInput));
		bucketSort(bucketInput);
		System.out.println("Bucket Sort:    " + Arrays.toString(bucketInput));
	}

}
