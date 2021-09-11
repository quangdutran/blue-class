package com.company.alg.orange.dynamicprogramming2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * SPOJ: https://www.youtube.com/watch?v=vol3BDkSrbM
 */
public class Aibophobia {
    private static int dp[][];

    private static int findPalindrome(String s, int x, int y) {
        if (x >= s.length() || y <= x) {
            return 0;
        }
        if (dp[x][y] != -1) {
            return dp[x][y]; // This is key of dynamic programming, save result to use later
        }
        if (s.charAt(x) != s.charAt(y)) {
            int appendFront = findPalindrome(s, x, y -1) + 1;
            int appendBack = findPalindrome(s, x+1, y) + 1;
            dp[x][y] = Math.min(appendBack, appendFront);
        } else {
            dp[x][y] = findPalindrome(s,x+1, y-1);
        }
        return dp[x][y];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        dp = new int[word.length()+1][word.length()+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println(findPalindrome(word, 0, word.length()-1));
    }
}
