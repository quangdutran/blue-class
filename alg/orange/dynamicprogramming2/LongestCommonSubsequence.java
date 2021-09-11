package com.company.alg.orange.dynamicprogramming2;

import java.util.Arrays;

public class LongestCommonSubsequence {

    private static int LCSRecursive(String s1, String s2, int m, int n) {
        if (n == 0 || m == 0) {
            return 0;
        }
        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            return 1 + LCSRecursive(s1,s2, m-1, n-1);
        } else {
            return Math.max(LCSRecursive(s1,s2,m-1,n), LCSRecursive(s1,s2,m,n-1));
        }
    }

    private static int [][] dp;
    private static int LCSTopDown(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            dp[m][n] = 0;
            return dp[m][n];
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            dp[m][n] = 1 + LCSTopDown(s1,s2,m-1,n-1);
            return dp[m][n];
        } else {
            dp[m][n] = Math.max(LCSTopDown(s1,s2,m-1,n), LCSTopDown(s1,s2,m,n-1));
            return dp[m][n];
        }
    }

    private static void LCSTopDownDemo() {
        String s1 = "ATCJDZAEFGY";
        String s2 = "BADCJEFGYT";
        dp = new int[s1.length()+1][s2.length()+1];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Length of LCD is: " + LCSTopDown(s1,s2,s1.length(), s2.length()));
    }

    private static int LCSBottomUp(String s1, String s2, int m, int n) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(m-1) == s2.charAt(n-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[m-1][n], dp[m][n-1]);
                }
            }
        }
        return dp[m][n];
    }

    private static void LCSBottomUpDemo() {
        //String s1 = "ATCJDZAEFGY";
        //String s2 = "BADCJEFGYT";
        String s1 = "geeks";
        String s2 = "geeksforgeeks";
        dp = new int[s1.length()+1][s2.length()+1];
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Length of LCD is: " + LCSBottomUp(s1,s2,s1.length(), s2.length()));
    }

    public static void main(String[] args) {
        LCSBottomUpDemo();
        LCSTopDownDemo();
    }
}
