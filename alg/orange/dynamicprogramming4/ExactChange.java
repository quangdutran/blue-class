package com.company.blueclass.alg.orange.dynamicprogramming4;


import java.util.Scanner;

/**
 * UVA
 */
public class ExactChange {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int bill = input.nextInt();
            int money = input.nextInt();
            int moneyValues [] = new int [money];
            for (int i = 0; i < money; i++) {
                moneyValues[i] = input.nextInt();
            }

            int [] dp = new int [20000];
            dp[0] = 0;
            for (int i = 1; i < dp.length; i++) {
                dp[i] = Integer.MAX_VALUE;
            }

            for (int moneyValue : moneyValues) {
                for (int j = bill; j >= 0; j--) {
                    if (dp[j] != Integer.MAX_VALUE) {
                        dp[j + moneyValue] = Math.min(dp[j + moneyValue], dp[j] + 1); // 1 here is the coin 'moneyValue'
                    }
                }
            }

            for (int i = bill; i < 20000; i++) {
                if (dp[i] < Integer.MAX_VALUE) {
                    System.out.println(i + " " + dp[i]);
                    break;
                }
            }
        }
    }
}
