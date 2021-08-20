package com.company.alg.orange.greedy;

import java.util.*;

public class BuildingPermutation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = input.nextInt();
        int [] array = new int[total];
        for (int i = 0; i < total; i++) {
            array[i] = input.nextInt();
        }
        Arrays.sort(array);
        long result = 0;
        for (int i = 0; i < total; i++) {
            result += Math.abs(i + 1 - array[i]);
        }
        System.out.println(result);
    }
}
