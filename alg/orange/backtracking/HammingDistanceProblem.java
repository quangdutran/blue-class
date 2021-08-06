package com.company.alg.orange.backtracking;

import java.util.Scanner;

public class HammingDistanceProblem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int totalLength = input.nextInt();
            int numberOfBit1 = input.nextInt();
            int startBit1At = totalLength - numberOfBit1 + 1;
            StringBuilder bitString = new StringBuilder();
            for (int i = 1; i <= totalLength; i++) {
                bitString.append((i < startBit1At) ? "0" : "1");
            }
            distinctPermutation(bitString, 0, totalLength);
            System.out.println();
        }
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
