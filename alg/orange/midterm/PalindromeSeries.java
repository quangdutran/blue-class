package com.company.alg.orange.midterm;

import java.util.Scanner;

/**
 * GeeksForGeeks
 */
public class PalindromeSeries {

    private static boolean isPalidromeRecursive(String str, int low, int high) {
        if (low >= high) {
            return true;
        }

        if (str.charAt(low) != str.charAt(high)) {
            return false;
        }

        return isPalidromeRecursive(str, low + 1, high - 1);
    }

    private static boolean isPalidrome(String str) {
        return isPalidromeRecursive(str, 0, str.length() - 1);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();

        while(--cases >= 0) {
            String line = input.nextLine();
            int length = line.length();
            long sum = 0;
            StringBuilder sub = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sum += line.charAt(i) - '0';
                sub.append(convert(line. charAt(i)));
            }
            System.out.println(sub.toString());
            System.out.println(createStr(sub.toString(), sum));
            System.out.println(isPalidrome(createStr(sub.toString(), sum)) ? "YES" : "NO");
        }
    }

    private static String createStr(String sb, long length) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (count > sb.length() - 1) {
                count = 0;
            }
            result.append(sb.charAt(count));
            count++;
        }
        return result.toString();
    }

    private static char convert(char c) {
        return (char) (c + 49);
    }
}
