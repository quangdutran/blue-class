package com.company.alg.orange.hashtable;

import java.util.Objects;
import java.util.Scanner;

public class TheMonkAndPrateek {

    private static int hash(int n) {
        String numStr = n + "";
        int sumN = 0;
        for (char c : numStr.toCharArray()) {
            sumN += c - '0';
        }
        return n ^ sumN;
    }

    public static void main(String[] args) {
        int max = (int) 1e7;
        Integer [] hashTable = new Integer[max];

        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        int [] array = new int [total];
        int maxHash = 0;
        int minCollision = Integer.MAX_VALUE;
        int countCollision = 0;
        int maxCollisionNumber = 1;
        for (int i = 0; i < total; i++) {
            array[i] = input.nextInt();
            int index = hash(array[i]);
            maxHash = Math.max(maxHash, index);
            if (hashTable[index] == null) {
                hashTable[index] = 1;
            } else { //collision happens
                countCollision++;
                hashTable[index]++;

                if (hashTable[index] > maxCollisionNumber) {
                    minCollision = index;
                    maxCollisionNumber = hashTable[index];
                } else if (hashTable[index] == maxCollisionNumber) {
                    minCollision = Math.min(minCollision, index);
                }
            }
        }
        if (countCollision == 0) {
            System.out.println(maxHash + " 0");
        } else {
            System.out.println(minCollision + " " + countCollision );
        }
        input.close();
    }

}
