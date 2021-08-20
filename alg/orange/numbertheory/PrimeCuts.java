package com.company.alg.orange.numbertheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrimeCuts {

    private static List<Integer> sieveOfEratosthenes(int n) {
        boolean mark[] = new boolean[n+1];
        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(1);
        Arrays.fill(mark, true);
        mark[0] = mark[1] = false;

        for (int i = 2; i*i <= n; i++) {
            if (mark[i]) {
                for (int j = i * i; j <= n; j+=i) { // j could start as i * 2 but to avoid duplication, use i*i
                    mark[j] = false;
                }
            }
        }

        for (int i = 2; i <=n; i++) {
            if (mark[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static void getListNumber(List<Integer> primes, int c) {
        int startIndex = 0;
        int endIndex;
        if (primes.size() <= 2*c - 1) {
            for (Integer prime : primes) {
                System.out.print(prime + " ");
            }
            return;
        } else if (primes.size() % 2 == 0) {
            startIndex = primes.size() / 2 - 1 - c + 1;
            endIndex = 2*c;
        } else {
            startIndex = primes.size() / 2 - c + 1;
            endIndex = 2*c - 1;
        }
        int count = 0;
        while (count < endIndex) {
            System.out.print(primes.get(startIndex) + " ");
            count++;
            startIndex++;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            int first = input.nextInt();
            int second = input.nextInt();

            List<Integer> primes = sieveOfEratosthenes(first);

            System.out.print(first + " " + second + ": ");
            getListNumber(primes, second);
            System.out.println();
        }
    }
}
