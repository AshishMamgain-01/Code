package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeOverlapVaritation2 {

	public static void main(String[] args) {		
		
		int[][] intervals = { { -3, -1 }, {1, 3 }, { 4, 6 }, { 16, 92 }};
		
		int[] newInterval = {2,10};

		int[][] mergedIntervals = merge(intervals, newInterval);

		// Print result
		for (int[] interval : mergedIntervals) {
			System.out.println(Arrays.toString(interval));
		}
	}
	
	
	private static int[][] merge(int[][] intervals, int[] newInterval) {
		
		Arrays.sort(intervals, Comparator.comparing(a -> a[0]));
		int i=0;
		List<int[]> result = new ArrayList<int[]>();
		
		while(i<intervals.length && intervals[i][1] < newInterval[0])
		{
			result.add(intervals[i]);
			i++;
		}
		
		while(i<intervals.length && intervals[i][0] <= newInterval[1] ) {
			newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
			newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
			i++;
		}
		result.add(newInterval);
		
		while(i<intervals.length) {
			result.add(intervals[i]);
			i++;
		}		
		
		return result.toArray(new int[result.size()][]);
	}

}
