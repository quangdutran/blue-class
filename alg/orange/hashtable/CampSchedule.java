package com.company.alg.orange.hashtable;


import java.util.Scanner;

/**
 * Codeforces: 1137B: https://www.youtube.com/watch?v=x1DAVAIBGVk
 */
public class CampSchedule {
    private static final int BASE = 29;
    private static final int MOD = (int)1e9 + 7;
    private static long[] hash;
    private static long[] pow;

    private static void polyHash(String s) {
        hash = new long [s.length() + 1];
        pow = new long [s.length() + 1];
        pow[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            hash[i+1] = (s.charAt(i) + (BASE * hash[i])) % MOD;
            pow[i+1] = (pow[i] * BASE) % MOD;
        }
    }

    private static long getHash(int from, int to) {
        return (hash[to] - (hash[from-1] * pow[to-from+1]) % MOD + MOD) % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        int n = s.length();
        int m = t.length();

        int[] cnt = new int[2]; // Number of bit 0 and 1 in S minus number of bit 0 and 1 in T
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - '0']++;
        }
        for (int i = 0; i < m; i++) {
            cnt[t.charAt(i) - '0']--;
        }

        if (cnt[0] < 0 || cnt[1] < 0) { // If length of S less than length of T
            System.out.println(s);
            return;
        }
        polyHash(t);

        int maxPrefixDuplicate = 0;

        //Go from left to right in string T
        //Because we want themaxPrefixDuplicate to be left most index
//        for (int i = 2; i < m; i++) {
//            if (getHash(1, i) == getHash(m-i+1,m)) {
//                maxPrefixDuplicate = m-i+1;
//            }
//        }

        //Or go from right to left
        for (int i = m - 1; i > 0; i--) {
            if (getHash(1, i) == getHash(m - i + 1, m)) {
                maxPrefixDuplicate = i;
                break;
            }
        }

        int[] numberOfDuplicatedBit = new int[2];
        for (int i = maxPrefixDuplicate; i < m; i++) {
            numberOfDuplicatedBit[t.charAt(i) - '0']++;
        }
        StringBuilder sb = new StringBuilder(t);
        t = t.substring(maxPrefixDuplicate, m);

        while (cnt[0] >= numberOfDuplicatedBit[0] && cnt[1] >= numberOfDuplicatedBit[1]) {
            sb.append(t);
            cnt[0] -= numberOfDuplicatedBit[0];
            cnt[1] -= numberOfDuplicatedBit[1];
        }

        for (int i = 0; i < cnt[0]; i++) {
            sb.append('0');
        }
        for (int i = 0; i < cnt[1]; i++) {
            sb.append('1');
        }
        System.out.println(sb.toString());
    }
}
