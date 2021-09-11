package com.company.alg.orange.dynamicprogramming2;

import java.util.Scanner;

public class AdvancedFruits {
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

    private static void diff(String s1, String s2, int m, int n) {
        if (m > 0 && n > 0 && s1.charAt(m-1) == s2.charAt(n-1)) {
            diff(s1,s2, m-1, n-1);
            System.out.print(s1.charAt(m-1));
        } else if (n > 0 && (m == 0 || dp[m][n-1] >= dp[m-1][n])) {
            diff(s1,s2, m, n-1);
            System.out.print(s2.charAt(n-1));
        } else if (m > 0 && (n==0 || dp[m][n-1] < dp[m-1][n])) {
            diff(s1,s2,m-1,n);
            System.out.print(s1.charAt(m-1));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String [] fruits = line.split(" ");
            dp = new int[fruits[0].length()][fruits[1].length()];
            LCS(fruits[0], fruits[1]);
            diff(fruits[0], fruits[1], fruits[0].length(), fruits[1].length());
        }
    }
}
