package com.company.alg.orange.dynamicprogramming;

import java.util.Scanner;

/**
 * SPOJ: https://www.youtube.com/watch?v=Y3y64ge9ioU / https://www.youtube.com/watch?v=o1i7JYWbwOE
 *
 * Idea: If next number combined with previous number that create new number < 26 then dp at that index = sum of two previous indices
 */
public class AlphaCode {
    private static long [] dp;

    private static long count(String digits) {
        if (digits.length() == 1) return 1;

        dp = new long [digits.length()];
        dp[0] = 1;
        int val = getValueForBothChar(digits.charAt(0), digits.charAt(1));
        dp[1] = (val <= 26 && val >= 10 && digits.charAt(1) != '0') ? 2 : 1;

        for (int i = 2; i < digits.length(); i++) {
            int value = getValueForBothChar(digits.charAt(i-1), digits.charAt(i));
            if (value == 0 || (value > 26 && digits.charAt(i) == '0')) {
                return 0;
            }
            //Check single digit
            if (digits.charAt(i) - '0' > 0) {
                dp[i] += dp[i-1];
            }
            //Check combination with previous number 10 <= num <= 26

            if (value >= 10 && value <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[digits.length() - 1];
    }

    private static int getValueForBothChar(char prev, char next) {
        return 10 * (prev - '0') + (next - '0');
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String line = input.nextLine();
            if (line.equals("0") || line.isEmpty()) {
                break;
            }
            System.out.println(count(line));
        }
    }
}
