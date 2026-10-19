package com.learn.coding.dsa.array;

import java.util.Arrays;
import java.util.List;

public class Wissen {

	/*
	 * You are on a plane and you can watch two movies during this flight. You are
	 * given List<Integer> movieDurations which includes all the movie durations.
	 * You are also given duration of the flight which is in d minutes. Now, you
	 * need to pick two movies and the total duration of two movies is less than or
	 * equal to (d - 30min). Find the pair of movies with the longest total duration
	 * and return their indexes. If multiple found, return the pair with longest
	 * movie. Example1: Input: movieDurations = [90, 85, 75, 60, 120, 150, 125], d =
	 * 250 Output: [0, 6] Explaination: movieDuration[0] + movieDuration[6] = 90 +
	 * 125 = 215 is the maximum number within 220 (250 min - 30 min).
	 */

	public static List<Integer> findMovies(List<Integer> movieDurations, int d) {

		int[] result = { -1, -1 };
		int size = movieDurations.size();
		int targetDuration = d - 30;
		int bestSum = 0;
		int movies[][] = new int[size][2];
		int longestDuration = 0;

		for (int i = 0; i < size; i++) {
			movies[i][0] = movieDurations.get(i);
			movies[i][1] = i;
		}

		Arrays.sort(movies, (a, b) -> Integer.compare(a[0], b[0]));

		System.out.println(Arrays.deepToString(movies));

		int left = 0;
		int right = size - 1;

		while (left < right) {

			int sum = movies[left][0] + movies[right][0];
			int maxDuration = Math.max(movies[left][0], movies[right][0]);

			if (sum > targetDuration) {
				right--;
			} else {
				if ((sum > bestSum) || (sum == bestSum && maxDuration > longestDuration)) {
					bestSum = sum;
					longestDuration = maxDuration;
					result[0] = movies[left][1];
					result[1] = movies[right][1];
				}
				left++;
			}
		}
		return Arrays.asList(result[0], result[1]);
	}

	public static void main(String[] args) {
		List<Integer> movieDurations = Arrays.asList(90, 85, 75, 60, 120, 150, 125);
		int flightDuration = 250;
		List<Integer> result = findMovies(movieDurations, flightDuration);
		System.out.println(result);
	}

}
