package com.company.blue.binarysearch;

import java.util.Arrays;
import java.util.Scanner;

public class Eko {
    static long [] trees;
    static long totalLog;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int treeSize = input.nextInt();
        totalLog = input.nextLong();
        trees = new long [treeSize];
        for (int i = 0; i < treeSize; i++) {
            trees[i] = input.nextLong();
        }
        Arrays.sort(trees);
        System.out.println(getTheSawValue(0, (int)trees[treeSize - 1]));
    }

    private static int getTheSawValue(int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (getCurrentTotalLog(mid, getUpperTree(mid)) == totalLog) {
                return mid;
            }
            else if (getCurrentTotalLog(mid, getUpperTree(mid)) > totalLog) {
                return getTheSawValue(mid + 1, right);
            } else {
                return getTheSawValue(left, mid - 1);
            }
        }
        return right;
    }



    private static long getCurrentTotalLog(int sawValue, int fromWhichTree) {
        long total = 0;
        for (int i = fromWhichTree; i < trees.length; i++) {
            total += (trees[i] - sawValue);
        }
        return total;
    }

    private static int getUpperTree(int sawValue) {
        return getUpperBound(0, trees.length - 1, sawValue);
    }

    private static int getUpperBound(int left, int right, int sawValue) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            if (trees[mid] > sawValue) {
                return getUpperBound(left, mid, sawValue);
            } else {
                return getUpperBound(mid + 1, right, sawValue);
            }
        }
        return right;
    }
}
