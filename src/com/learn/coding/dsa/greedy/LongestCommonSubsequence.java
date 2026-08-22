package com.learn.coding.dsa.greedy;

import java.util.Arrays;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		LongestCommonSubsequence sol = new LongestCommonSubsequence();
		String text1 = "AGGTAB";
		String text2 = "GXTXAYB";
		System.out.println("LCS String: " + sol.longestCommonSubsequence(text1, text2));
	}

	private String longestCommonSubsequence(String text1, String text2) {
		// TODO Auto-generated method stub

		int len1 = text1.length();
		int len2 = text2.length();

		int dp[][] = new int[len1 + 1][len2 + 1];
		for (int[] row : dp) {
		    Arrays.fill(row, -1);
		}

		lcsUtil(len1, len2, text1, text2, dp);
		
		return reconstructString(len1,len2,text1,text2,dp);
		
	}

	private String reconstructString(int len1, int len2, String text1, String text2, int[][] dp) {
		// TODO Auto-generated method stub
		
		StringBuilder len = new StringBuilder();
		int i =len1;
		int j =len2;
		
		while(i>0 && j>0) {
			if(text1.charAt(i-1) == text2.charAt(j-1)) {
				len.append(text1.charAt(i-1));
				//move daigonally;
				i--;
				j--;
			}
			else if(dp[i-1][j] > dp[i][j-1]) {
				i--;
			}
			else {
				j--;
			}
		}		
		return len.reverse().toString();
	}

	private String reconstructString1(int len1, int len2, String text1, String text2, int[][] dp) {
		
		StringBuilder lcs = new StringBuilder();
		int i = len1, j = len2;
		//start from back
		while(i > 0 && j > 0){
            if(text1.charAt(i-1) == text2.charAt(j-1)){
                lcs.append(text1.charAt(i-1)); // add char
                i--; j--; // move diagonally
            } else {
                if(dp[i-1][j] > dp[i][j-1]){
                    i--; // move up
                } else {
                    j--; // move left
                }
            }
        }
        return lcs.reverse().toString();
	}

	private int lcsUtil(int len1, int len2, String text1, String text2, int[][] dp) {

		if (len1 == 0 || len2 == 0)
			return 0;

		if (dp[len1][len2] != -1)
			return dp[len1][len2];

		if (text1.charAt(len1 - 1) == text2.charAt(len2 - 1)) {
			dp[len1][len2] = 1 + lcsUtil(len1 - 1, len2 - 1, text1, text2, dp);
		} else {
			dp[len1][len2] = Math.max(
					lcsUtil(len1 - 1, len2, text1, text2, dp),
					lcsUtil(len1, len2 - 1, text1, text2, dp));
		}
		return dp[len1][len2];
	}

} 
