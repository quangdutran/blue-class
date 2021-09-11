package com.company.alg.orange.dynamicprogramming;

import java.util.Scanner;

/**
 * Based K means there is no single number in the number greater or equal than k
 */
public class KBasedNumber {
    static int count_numbers(int k, int n,
                             boolean flag) {
        if (n == 1) {
            // If 0 wasn't chosen previously then return k - 1
            return flag ? k - 1 : 1;
        }

        // If 0 wasn't chosen previously
        if (flag)
            return (k - 1) * (count_numbers(k, n - 1, false) +
                    count_numbers(k, n - 1, true));
        else
            return count_numbers(k, n - 1, true);
    }

    private static int countBottomUp(int k, int n) {
        if (n == 1) {
            return k;
        } else {
            int endWith0 [] = new int[n+1];
            int notEndWith0 [] = new int[n+1];
            endWith0[1] = 0;
            notEndWith0[1] = k -1;
            for (int i = 2; i <= n; i++) {
                endWith0[i] = notEndWith0[i-1];
                notEndWith0[i] = (notEndWith0[i-1] + endWith0[i-1]) * (k-1);
            }
            return endWith0[n] + notEndWith0[n];
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        //System.out.println(count_numbers(k, n, true));
        System.out.println(countBottomUp(k,n));
    }
}
