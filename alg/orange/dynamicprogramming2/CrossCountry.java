package com.company.alg.orange.dynamicprogramming2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * SPOJ: use LCS
 */
public class CrossCountry {
    private static int [][] dp;

    private static int LCS(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i <= list1.size(); i++) {
            for (int j = 0; j <= list2.size(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (list1.get(i - 1).equals(list2.get(j - 1))) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[list1.size()][list2.size()];
    }



    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        while(--cases >= 0) {
            String line = input.nextLine();
            List<String> cards = new ArrayList<>();
            while (!line.equals("0")) {
                cards.add(line);
                line = input.nextLine();
            }

            List<List<Integer>> finalCards = cards.stream().map(CrossCountry::formatCard).collect(Collectors.toList());
            int MAX = 0;
            List<Integer> anna = finalCards.get(0);
            for (int i = 1; i < finalCards.size(); i++) {
                dp = new int[anna.size()+1][finalCards.get(i).size()+1];
                MAX = Math.max(MAX, LCS(anna,finalCards.get(i)));
            }
            System.out.println(MAX);
        }
    }

    private static List<Integer> formatCard(String card) {
        String [] num = card.split(" ");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num.length - 1; i++) {
            result.add(Integer.parseInt(num[i]));
        }
        return result;
    }
}
