package com.company.alg.orange.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 * UVA: Same as classic coin change problem
 */
public class IngenuousCubrency {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                return;
            }
            int total = Integer.parseInt(line);
            int [] coins = initCoins(total);
            System.out.println(coinChange(total, coins));
        }
        input.close();
    }

    private static int [] initCoins(int total) {
        int count;
        for (count = 0; count <= 21; count++) {
            if (total < count * count * count) {
                break;
            }
        }

        int [] coins = new int[count];
        for (int i = 0; i < count; i++) {
            coins[i] = i * i * i;
        }
        return Arrays.copyOfRange(coins, 1, count);
    }

    private static long coinChange(int total, int coins []) {
        long [] dp = new long [total + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= total; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[total];
    }
}
