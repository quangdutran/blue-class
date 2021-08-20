package com.company.alg.orange.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class TheNumberOnTheBoard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        input.nextLine();
        String line = input.nextLine();
        int [] array = new int [line.length()];
        int sum = 0;
        for (int i = 0; i < line.length(); i++) {
            array[i] = line.charAt(i) - '0';
            sum += array[i];
        }

        if (sum >= k) {
            System.out.println("0");
        } else {
            Arrays.sort(array);
            for (int i = 0; i < line.length(); i++) {
                sum = sum - array[i] + 9;
                if (sum >= k) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }
}
