package com.company.blue.bst;

import java.util.*;

public class DistinctCount {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        while (--test >= 0) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            int size = input.nextInt();
            int k = input.nextInt();
            for (int i = 0; i < size; i++) {
                int num = input.nextInt();
                treeSet.add(num);
            }
            int treeSize = treeSet.size();
            if (treeSize == k) {
                System.out.println("Good");
            } else if (treeSize > k) {
                System.out.println("Average");
            } else {
                System.out.println("Bad");
            }
         }

    }
}
