package com.company.alg.orange.numbertheory;

import java.util.Scanner;

/**
 * UVa 10820
 *
 * (x,y) that could not be derived anymore when they are mutually primes together. Basically, the answer is how many
 *  pair of mutual primes number together x 2 (because (2,3) and (3,2) count as 2) and - 1 ( (1,1) count twice)
 */
public class SendATable {
    private static int prime[] = new int [50010];
    private static int sum[] = new int [50010];

    private static int phi(int n) {
        int result = n;
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result = result / i * (i-1);
            }
        }

        if (n > 1) {
            result = result / n * (n-1);
        }
        return result;
    }

    private static void init() {
        for (int i = 1; i < sum.length; i++) {
            int phi = phi(i);
            sum[i] = sum[i-1] + phi;
        }
    }

    public static void main(String[] args) {
        init();
        Scanner input = new Scanner(System.in);
        while(true) {
            int number = input.nextInt();
            if (number == 0) {
                break;
            }
            System.out.println(sum[number] * 2 - 1);
        }
        input.close();
    }

}
