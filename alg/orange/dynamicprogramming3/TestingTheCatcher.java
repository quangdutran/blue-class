package com.company.alg.orange.dynamicprogramming3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * UVA: Same with LCI but in decreasing order
 */
public class TestingTheCatcher {

    private static int longestDecreasingSubsequence(Integer [] a) {
        ArrayList<Integer> dp = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            dp.add(1);
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] <= a[j] && dp.get(j) + 1 > dp.get(i)) {
                    dp.set(i, dp.get(j) + 1);
                }
            }
        }

        int MAX = 0;
        for (int i : dp) {
            MAX = Math.max(MAX, i);
        }
        return MAX;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = 0;
        while (input.hasNextLine()) {
            int number = input.nextInt();
            if (number == -1 ) {
                break;
            }
            ArrayList<Integer> list = new ArrayList<>();
            while (number != -1) {
                list.add(number);
                number = input.nextInt();
            }
            Integer [] a = new Integer [list.size()];
            for (int i = 0; i < list.size(); i++) {
                a[i] = list.get(i);
            }
            System.out.println("Test #" + ++cases + ":");
            System.out.println("  maximum possible interceptions: " + longestDecreasingSubsequence(a));
            System.out.println();
        }
    }
}
