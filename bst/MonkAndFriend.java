package com.company.bst;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class MonkAndFriend {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            int n = input.nextInt();
            int m = input.nextInt();
            TreeSet<Long> treeSet = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                long candies = input.nextLong();
                treeSet.add(candies);
            }
            for (int i = 0; i < m; i++) {
                long key = input.nextLong();
                if (treeSet.contains(key)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                    treeSet.add(key);
                }
            }
        }
    }
}
