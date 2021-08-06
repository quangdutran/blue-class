package com.company.alg.orange.backtracking;

public class Permutation {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("00112");
        distinctPermutation(s,0,s.length());
    }

    private static void distinctPermutation(StringBuilder s, int l, int r) {
        if (l >= r) {
            System.out.println(s);
        } else {
            for (int i = l; i < r; i++) {
                boolean check = shouldSwap(s,l,i);
                if (check) {
                    char temp = s.charAt(l);
                    s.setCharAt(l, s.charAt(i));
                    s.setCharAt(i, temp);
                    distinctPermutation(s, l+1, r);
                    temp = s.charAt(l);
                    s.setCharAt(l, s.charAt(i));
                    s.setCharAt(i, temp);
                }
            }
        }
    }

    private static boolean shouldSwap(StringBuilder s, int start, int end) {
        for (int i = start; i < end; i++) {
            if (s.charAt(i) == s.charAt(end)) {
                return false;
            }
        }
        return true;
    }
}
