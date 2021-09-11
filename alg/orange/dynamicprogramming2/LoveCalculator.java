package com.company.alg.orange.dynamicprogramming2;

public class LoveCalculator {

    private static int dp[][];

    private static int LCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

}
