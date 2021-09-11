package com.company.alg.orange.midterm;

import java.util.Scanner;

/**
 * GeeksForGeeks
 *
 * This looks like Hanoi Tower problem but formular is 2^n - 1
 *
 * But constraint is 1≤N≤10^9, use modular exponentiation
 * ​​
 */
public class ExaminationPapers {

    private static int MOD = (int) (1e9 + 7);

    /**
     * Calculate a^b mod m with a and b super big
     * @param a
     * @param b
     * @param m
     * @return
     */
   private static long modularExponentiation(long a, long b, int m) {
       long result = 1;
       a %= m;
       while (b>0) {
           if (b % 2 == 1) {
                result = (result*a) % m;
           }
           b/=2;
           a = (a*a) % m;
       }
       return result;
   }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int n = input.nextInt();
            System.out.println((modularExponentiation(2,n,MOD) - 1) % MOD);
        }
    }
}
