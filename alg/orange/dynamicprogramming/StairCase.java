package com.company.alg.orange.dynamicprogramming;

public class StairCase {
    private static int [] dp;
    private static int staircaseTopDown(int n) {
        if (n < 0) {
            return 0;
        }
        if (dp[n] != 0) {
            return dp[n];
        } else {
            return dp[n] = staircaseTopDown(n -1 ) + staircaseTopDown(n - 2);
        }
    }

    private static int staircaseBottomUp(int n) {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 7;
        dp = new int[n+1];
        dp[0] = 1;
        System.out.println(staircaseTopDown(n));

        dp = new int[n+1];
        System.out.println(staircaseBottomUp(n));
    }
}
