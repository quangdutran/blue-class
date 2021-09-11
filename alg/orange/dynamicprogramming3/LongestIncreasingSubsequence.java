package com.company.alg.orange.dynamicprogramming3;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {
    private static ArrayList<Integer> dp;
    static int path [];
    static int last;

    private static int LIS(int [] a) {
        int length = 0;
        dp = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            dp.add(1);
        }
        path = new int[a.length];
        Arrays.fill(path, -1);
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && dp.get(i) < dp.get(j) + 1) {
                    dp.set(i, dp.get(j) + 1);
                    path[i] = j;
                }
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (length < dp.get(i)) {
                length = dp.get(i);
                last = i;
            }
        }
        return length;
    }

    private static void printLIS(int [] a) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = last;
        while (i >= 0) {
            result.add(a[i]);
            i = path[i];
        }

        for (i = result.size() - 1; i >= 0; i--) {
            System.out.printf("%d ", result.get(i));
        }
    }

    public static void main(String[] args) {
//        int [] a = new int[] {5,12,3,10,6,8,14,4,11,7,15};
//        int length = LIS(a);
//        System.out.println("Length of Longest Increasing Subsequence is: " + length);
//        printLIS(a);
        int [] a = new int[] {5,5,2, 2,12,3,10,6,8,14,4,11,7,15};
        int length = lisOptimized(a);
        System.out.println("Length of Longest Increasing Subsequence is: " + length);
        printLisOptimized(a, length);
    }

    private static int lowerBound(int [] a, ArrayList<Integer> sub, int n, int x) {
        int left = 0, right = n;
        int pos = n;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int index = sub.get(mid);
            if (a[index] >= x) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int lisOptimized(int [] a) {
        int length = 1;
        dp = new ArrayList<>();
        dp.add(0);
        path = new int [a.length];
        Arrays.fill(path, -1);
        for (int i = 1; i < a.length; i++) {
            if (a[i] <= a[dp.get(0)]) {
                dp.set(0, i);
            } else if (a[i] > a[dp.get(length - 1)]) {
                path[i] = dp.get(length -1);
                dp.add(i);
                length++;
            } else {
                int pos = lowerBound(a, dp, length, a[i]);
                path[i] = dp.get(pos-1);
                dp.set(pos, i);
            }
        }
        return length;
    }

    private static void printLisOptimized(int [] a, int length) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = dp.get(length -1);
        while (i >= 0) {
            result.add(a[i]);
            i = path[i];
        }
        for (i = result.size() - 1; i >= 0; i--) {
            System.out.printf("%d ", result.get(i));
        }
    }
}
