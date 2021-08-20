package com.company.alg.orange.numbertheory;

import java.util.Scanner;

public class LargestPrimeDivisor {

    private static boolean isPrime(long n) {
        for (long i = 2; i*i <= n; i++){
            if (n%i == 0) {
                return false;
            }
        }
        return n > 1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            long number = input.nextLong();
            if (number < 0) {
                number = number * -1;
            }
            long temp = number;
            if (number == 0) {
                break;
            }
            long max = 1;
            int count = 0;
            for (long i = 2; i*i <= number; i++) {
                if (number % i == 0) {
                    while (number % i == 0) {
                        number /= i;
                    }
                    count++;
                    max = Math.max(max, i);
                }
            }
            if (number != temp && number > 1) {
                max = Math.max(max, number);
                count++;
            }
            System.out.println(count <= 1 ? -1 : max);
        }
        input.close();
    }
}
