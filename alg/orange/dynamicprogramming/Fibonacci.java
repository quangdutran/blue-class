package com.company.alg.orange.dynamicprogramming;

public class Fibonacci {
    private static int [] dp;


    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n- 1 ) + fibonacci(n - 2);
        }
    }

    private static int fibonacciTopDown(int n) {
        if (n == 0) {
            return dp[0];
        }
        if (dp[n] != 0) {
            return dp[n];
        } else {
            return fibonacciTopDown(n - 1) + fibonacciTopDown(n-2);
        }
    }

    private static int fibonacciBottomUp(int n) {
         dp[0] = 0;
         dp[1] = 1;
         for (int i = 2; i <= n; i++) {
             dp[i] = dp[i-1] + dp[i-2];
         }
         return dp[n];
    }

    public static void main(String[] args) {
        //Top down
        int n = 6;
        dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        System.out.println(fibonacciTopDown(n));

        //Bottom up
        dp = new int[n + 1];
        System.out.println(fibonacciBottomUp(n));
    }
}
