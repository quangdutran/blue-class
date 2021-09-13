package com.company.blueclass.alg.orange.kmp;

public class KMP {
    private static void KMPPreprocess(String p, int [] prefix) {
        prefix[0] = 0;
        int m = p.length();
        int j = 0;
        int i = 1;
        while (i < m) {
            if (p.charAt(i) == p.charAt(j)) {
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

    private static void KMPSearch(String t, String p, int [] prefix) {
        int n = t.length();
        int m = p.length();
        int i = 0, j = 0;
        while (i < n) {
            if (t.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.printf("Patttern at index %d\n", i - j);
                j = prefix[j-1];
            } else if (i < n && t.charAt(i) != p.charAt(j)) {
                if (j != 0) {
                    j = prefix[j-1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String t = "ACMIACMIAACMIACMIBCACMIACMIB";
        String p = "ACMIACMIB";
        int [] prefix = new int [p.length()];
        KMPPreprocess(p, prefix);
        KMPSearch(t,p, prefix);
    }
}
