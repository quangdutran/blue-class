package com.company.alg.orange.numbertheory;


import java.util.Scanner;

/**
 * UVA, basically find phi of n
 */
public class IrreducibleBasicFractions {
    private static int phi(int n) {
        int result = n;
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                while (n%i == 0) {
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


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            int number = input.nextInt();
            if (number == 0) {
                break;
            }

            System.out.println(phi(number));
        }
        input.close();
    }
}
