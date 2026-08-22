package com.learn.coding.dsa.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeOverlapVariation1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 9, 14 }, { 8, 10 }, { 15, 18 } };

		int[][] mergedIntervals = merge(intervals);

		// Print result
		for (int[] interval : mergedIntervals) {
			System.out.println(Arrays.toString(interval));

		}
	}

	private static int[][] merge(int[][] intervals) {
		// TODO Auto-generated method stub
		
		Arrays.sort(intervals,Comparator.comparingInt(a -> a[0]));
		int start = intervals[0][0];
		int end = intervals[0][1];
		
		List<int []> result = new ArrayList<>();
		
		for(int i=1;i<intervals.length;i++) {
			int currenStart = intervals[i][0];
			int currentEnd = intervals[i][1];
			
			if(currenStart<end) {
				end =Math.max(end, currentEnd);
			}
			else{
				result.add(new int[] {start,end});
				start = currenStart;
				end =currentEnd;
				
			}
		}
		result.add(new int[] {start,end});
		
		return result.toArray(new int[result.size()][]);
	}

}
