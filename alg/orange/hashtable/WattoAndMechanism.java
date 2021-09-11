package com.company.alg.orange.hashtable;


import java.util.Scanner;
import java.util.TreeSet;

/**
 * Codeforces
 */
public class WattoAndMechanism {
    private static class Pair implements Comparable<Pair> {
        Long first;
        Long second;
        Pair(Long first, Long second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return first.equals(o.first) ? second.compareTo(o.second) : first.compareTo(o.first);
        }
    }

    private static final int MAX = (int)1e6 + 1;
    private static final int BASE = 257;
    private static final long TABLE_SIZE_1 = (long)1e9 + 7;
    private static final long TABLE_SIZE_2 = (long)1e9 + 9;

    private static long[] pow1 = new long[MAX];
    private static long[] pow2 = new long[MAX];

    private static TreeSet<Pair> dict = new TreeSet<>(); //Use tree set to avoid rewrite equal() and hash() for Pair
    //If use HashSet then have to rewrite hash() and equal()

    private static void init() {
        pow1[0] = pow2[0] = 1;
        for (int i = 1; i < MAX; i++) {
            pow1[i] = (pow1[i-1] * BASE) % TABLE_SIZE_1;
            pow2[i] = (pow2[i-1] * BASE) % TABLE_SIZE_2;
        }
    }

    private static long polyHash(String keys, long MOD) {
        long hashValue = 0;
         for (int i = 0; i < keys.length(); i++) {
            hashValue = (hashValue * BASE + (keys.charAt(i) - 'a' + 1)) % MOD;  //plus 1 to avoid 0
        }
        return hashValue;
    }


    private static boolean check(String s) {
        //hash the query string
        long hash1 = polyHash(s, TABLE_SIZE_1);
        long hash2 = polyHash(s, TABLE_SIZE_2);

        //Go through each characters of the query string
        //calculate the difference hash and plus the original hash
        //check in the dict if these values exist
        for (int i = 0; i < s.length(); i++) {
            for (char c = 'a'; c <= 'c'; c++) {
                if (c != s.charAt(i)) {
                    long difference1 = ((c - s.charAt(i)) * pow1[s.length() - i - 1]) % TABLE_SIZE_1 + TABLE_SIZE_1;
                    long sum1 = (difference1 + hash1) % TABLE_SIZE_1;
                    long difference2 = ((c - s.charAt(i)) * pow2[s.length() - i - 1]) % TABLE_SIZE_2 + TABLE_SIZE_2;
                    long sum2 = (difference2 + hash2) % TABLE_SIZE_2;
                    if (dict.contains(new Pair(sum1, sum2))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            dict.add(new Pair(polyHash(s, TABLE_SIZE_1), polyHash(s, TABLE_SIZE_2)));
        }
        init();
        for (int i = 0; i < m; i++) {
            String t = sc.next();
            if (check(t)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        sc.close();
    }

}
