package com.company.alg.orange.dynamicprogramming;

public class CoinChangeBottomUp {
    private static int coinChange(int total, int [] coins) {
        int n = coins.length;
        int [] dp = new int [total + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= total; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[total];
    }

    public static void main(String[] args) {
        int total = 10;
        int [] coins = new int[]{1,2,5,10};

        System.out.printf("Number of solution: %d", coinChange(total, coins));
    }
}
