package com.company.blueclass.alg.orange.dynamicprogramming4;

import java.util.Scanner;

public class PoloPenguinAndTheTest {
    private static class Item {
        int profit;
        int weight;
        Item(int p, int w) {
            profit = p;
            weight = w;
        }
    }

    private static int [][] dp;
    private static int knapsack(Item [] items, int maxWeightAllowed) {
        dp = new int [items.length+1][maxWeightAllowed + 1];

        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= maxWeightAllowed; j++) {
                if (items[i].weight > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], items[i].profit + dp[i-1][j - items[i].weight]);
                }
            }
        }
        return dp[items.length-1][maxWeightAllowed];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            int numberOfItems = input.nextInt();
            int totalWeightAllowed = input.nextInt();
            Item [] items = new Item[numberOfItems + 1];
            items[0] = new Item(0,0);
            for (int i = 1; i <= numberOfItems; i++) {
                items[i] = new Item(input.nextInt() * input.nextInt(), input.nextInt());
            }
            System.out.println(knapsack(items, totalWeightAllowed));
        }
    }
}
