package com.company.blueclass.alg.orange.kmp;

import java.util.Scanner;

/**
 * Codechef: classic KMP
 */
public class GiantAndSifat {
    private static void KMPPreprocess(String keyword, int [] prefix) {
        prefix[0] = 0;
        int m = keyword.length();
        int j = 0;
        int i = 1;
        while (i < m) {
            if (keyword.charAt(i) == keyword.charAt(j)) {
                j++;
                prefix[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = prefix[j-1];
                } else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
    }

    private static int KMPSearch(String longText, String keyword, int [] prefix) {
        int n = longText.length();
        int m = keyword.length();
        int i = 0, j = 0, count = 0;
        while (i < n) {
            if (longText.charAt(i) == keyword.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                count++;
                j = prefix[j-1];
            } else if (i < n && longText.charAt(i) != keyword.charAt(j)) {
                if (j != 0) {
                    j = prefix[j-1];
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        int count = 0;
        while (--cases >= 0) {
            String longText = input.nextLine().replace(" ", "");
            String keyword = input.nextLine();
            int prefix [] = new int [keyword.length()];
            KMPPreprocess(keyword, prefix);
            int result = KMPSearch(longText,keyword, prefix);
            System.out.println("Case " + ++count + ": " + result);
        }
    }
}
