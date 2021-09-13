package com.company.blueclass.alg.orange.dynamicprogramming4;

public class Knapsack01 {
    private static class Item {
        int profit;
        int weight;
        Item(int p, int w) {
            profit = p;
            weight = w;
        }
    }

    private static int [][] dp;
    private static int knapsack(Item[] items, int totalSizeAllowed) {
        dp = new int[items.length + 1][totalSizeAllowed+1];
        for (int i = 1; i < items.length; i++) {
            for (int j = 0; j <= totalSizeAllowed; j++) {
                if (items[i].weight > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int temp1 = items[i].profit + dp[i-1][j - items[i].weight];
                    int temp2 = dp[i-1][j];
                    dp[i][j] = Math.max(temp1, temp2);
                }
            }
        }
        return dp[items.length-1][totalSizeAllowed];
    }

    public static void main(String[] args) {
        Item [] items = new Item[6];
        items[0] = new Item(0,0); // dynamic programming always start with a base case
        items[1] = new Item(1,1);
        items[2] = new Item(2,1);
        items[3] = new Item(2,2);
        items[4] = new Item(4,6);
        items[5] = new Item(10,4);
        int bagSize = 10;
        int result = knapsack(items, bagSize);
        printItems(items, bagSize);
        System.out.printf("Total value %d\n", result);
    }

    private static void printItems(Item [] items, int bagSize) {
        System.out.println("List items:");
        for (int i = items.length -1; i > 0; i--) {
            if (dp[i][bagSize] != dp[i-1][bagSize]) {
                System.out.printf("[%d, %d]\n", items[i].profit, items[i].weight);
                bagSize -= items[i].weight;
            }
        }
    }

}
