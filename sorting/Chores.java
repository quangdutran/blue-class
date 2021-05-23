package com.company.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class Chores {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalChores = input.nextInt();
        int peyta = input.nextInt();
        int vasya = input.nextInt();
        int [] choreComplexity = new int [totalChores];
        for (int i = 0; i < totalChores; i++) {
            choreComplexity[i] = input.nextInt();
        }
        input.close();
        Arrays.sort(choreComplexity);
        if (choreComplexity[vasya - 1] == choreComplexity[vasya]) {
            System.out.println(0);
        } else if (choreComplexity[vasya - 1] < choreComplexity[vasya]) {
            System.out.println(choreComplexity[vasya] - choreComplexity[vasya - 1]);
        }
    }
}
