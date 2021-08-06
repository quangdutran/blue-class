package com.company.blue.grokking;

import java.util.Scanner;

/**
 * Can jump till the end of array?
 * Jump max step at a[i] to next
 * [1,3,2,1,0,6,7] = false - stuck at index 4
 * [20,3,2,1,0,6,7] = true
 *
 * O(n)
 */
public class Issue173 {
    public static void main(String[] args) {
        int [] a1 = new int [] {1,3,2,1,0,6,7};
        int [] a2 = new int [] {20,3,2,1,0,6,7};
        int [] a3 = new int [] {1,3,2,1,1,6,3,4,4,2,3,1,0,6};
        int [] a4 = new int [] {1,0,0,0,1};
        System.out.println(canJump(a1));
        System.out.println(canJump(a2));
        System.out.println(canJump(a3));
        System.out.println(canJump(a4));
    }

    private static boolean canJump(int [] array) {
        int lastIndex = array.length - 1;
        for (int i = array.length - 2; i >= 0; i--) {
            if (i + array[i] >= lastIndex) {
                lastIndex = i;
            }
        }
        return lastIndex == 0;
    }
}
