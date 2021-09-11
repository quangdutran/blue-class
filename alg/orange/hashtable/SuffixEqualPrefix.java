package com.company.alg.orange.hashtable;

import java.util.Scanner;

public class SuffixEqualPrefix {

    public static long MOD = (long)1e9 + 7;
    public static int MAXN = (int)1e6 + 1;
    public static long [] hashValues = new long [MAXN];
    public static long [] powerOf26 = new long [MAXN]; //Save the power values of 26, avoiding calculate many times

    private static long polyHash(String key) {
        long hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (key.charAt(i) - 'a' + (26*hashValue) % MOD) % MOD;
            hashValues[i+1] = hashValue;
        }
        return hashValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s, x;
        powerOf26[0] = 1;

        //Initialize power of 26 array
        for (int i = 1; i < MAXN; i++)
            powerOf26[i] = (powerOf26[i - 1] * 26) % MOD;

        int test = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= test; i++) {
            String word = sc.nextLine();
            polyHash(word);
            int count = 0;
            int len = 1;
            while (len < word.length()) {
                if (hashValues[len] == ((hashValues[word.length()] - (hashValues[word.length() - len] * powerOf26[len]) % MOD) + MOD) % MOD) {
                    count++;
                }
                len++;
            }
            System.out.println("Case "+i+": " + count);
        }

    }

    private static long getHash(int i, int j) {
        return (hashValues[j] - hashValues[i-1] * powerOf26[j-i] + MOD) % MOD;
    }

}
