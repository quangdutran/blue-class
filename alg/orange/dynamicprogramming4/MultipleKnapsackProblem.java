package com.company.blueclass.alg.orange.dynamicprogramming4;

public class MultipleKnapsackProblem {
    private static class Item {
        int profit;
        int weight;
        Item(int p, int w) {
            profit = p;
            weight = w;
        }
    }

    private static int [] dp;
    private static int knapsack(Item [] items, int totalWeight) {
        dp = new int[totalWeight + 1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (Item item : items) {
                if (item.weight <= i) {
                    dp[i] = Math.max(item.profit + dp[i - item.weight], dp[i]);
                }
            }
        }
        return dp[totalWeight];
    }

    public static void main(String[] args) {
        Item[] items = new Item[5];
        items[1] = new Item(1,1);
        items[2] = new Item(2,1);
        items[3] = new Item(2,2);
        items[4] = new Item(4,6);
        items[0] = new Item(10,4);
        System.out.println(knapsack(items, 10));
    }
}
