package com.company.alg.orange.dynamicprogramming2;

import java.util.Scanner;

public class LCSOfThreeStrings {
    private static int[][][] dp;

    private static int LCS(String s1, String s2, String s3) {
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                for (int k = 0; k <= s3.length(); k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0;
                    } else if (s1.charAt(i - 1) == s2.charAt(j - 1) && s1.charAt(i-1) == s3.charAt(k-1)) {
                        dp[i][j][k] = 1 + dp[i - 1][j - 1][k-1];
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k-1]);
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()][s3.length()];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        while (--cases >= 0) {
            String line1 = input.nextLine();
            String line2 = input.nextLine();
            String[] words = line2.split(" ");
            dp = new int [words[0].length() + 1][words[1].length() + 1][words[2].length()+1];
            System.out.println(LCS(words[0], words[1], words[2]));
        }
    }


}
