package com.company.alg.orange.greedy;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Same noted time means they are in the same room, so just sort the array, count the occurrence
 */
public class PetyaAndCatacombs {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int array [] = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }
        Arrays.sort(array);
        int reusult = 1;
        for (int i = 1; i < n; i++) {
            if (array[i] == array[i-1]) {
                reusult++;
            }
        }
        System.out.println(reusult);
    }
}
