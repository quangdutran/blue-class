package com.company.alg.orange.numbertheory;

import com.company.blue.array.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class SegmentedSieve {
    private static ArrayList<Integer> sieve(int limit) {
        boolean mark[] = new boolean[limit + 1];
        ArrayList<Integer> primes = new ArrayList<>();
        Arrays.fill(mark, true);
        mark[0] = mark[1] = false;
        for (int i = 2; i *i<=limit; i++) {
            if (mark[i]) {
                for (int j = i*i; j < limit; j+=i) {
                    mark[j] = false;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (mark[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static ArrayList<Integer> segmentedSieve(int left, int right, ArrayList<Integer> primes) {
        if (left == 1) {
            left = left+1;
        }

        boolean[] mark = new boolean[left -right + 1];
        Arrays.fill(mark, true);
        for (int i = 0; i < primes.size() && primes.get(i) <= Math.sqrt(right); i++) {
            int base = (left / primes.get(i)) * primes.get(i);
            if (base < left) {
                base += primes.get(i);
            }

            for (int j = base; j <= right; j += primes.get(i)) {
                if (j != primes.get(i)) {
                    mark[j - left] = false;
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (mark[i -left]) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int left = 11;
        int right = 34;
        ArrayList<Integer> primes = sieve((int)Math.sqrt(right));
        ArrayList<Integer> result = segmentedSieve(left, right, primes);
    }
}
