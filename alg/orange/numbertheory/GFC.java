package com.company.alg.orange.numbertheory;

public class GFC {
    static long maxPrimeFactors(long n)
    {if (n <= 1) {
        return 0;
    } else {
        int div = 2;
        while (div < n) {
            if (n % div != 0) {
                div++;
            } else {
                n = n / div;
                div = 2;
            }
        }
        return n;
    } }

    // Driver code
    public static void main(String[] args)
    {
        Long n = 80798284478113l;
        System.out.println(maxPrimeFactors(n));
    }
}
