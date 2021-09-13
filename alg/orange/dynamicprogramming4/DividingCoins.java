package com.company.blueclass.alg.orange.dynamicprogramming4;

import java.util.Scanner;

public class DividingCoins {

    private static int [][] dp;
    private static int knapsack(Integer [] items, int totalWeight) {
        dp = new int[items.length + 1][totalWeight + 1];

        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= totalWeight; j++) {
                if (items[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], items[i] + dp[i-1][j - items[i]]);
                }
            }
        }

        return dp[items.length - 1][totalWeight];
    }

    public static void main_(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int totalCoins = input.nextInt();
            Integer [] coins = new Integer[totalCoins+1];
            int total = 0;
            for (int i = 1; i <= totalCoins; i++) {
                int profit = input.nextInt();
                total += profit;
                coins[i] = profit;
            }

            int half = total / 2;

            int firstHalf = knapsack(coins, half);
            int otherHalf = total - firstHalf;
            System.out.println(Math.abs(firstHalf - otherHalf));
        }
    }

    public static void main(String[] args) {
        Integer coins []  = new Integer[4];
        coins[1] = 500;

    }

}
