package com.company.array;

import java.util.Scanner;

public class BearGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfMin = input.nextInt();

        int[] interestingMinutes = new int[numberOfMin];
        for (int i = 0; i < numberOfMin; i++) {
            interestingMinutes[i] = input.nextInt();
        }
        input.close();

        if (interestingMinutes[0] > 15) {
            System.out.println(15);
            return;
        }

        if (interestingMinutes.length == 1) {
            System.out.println(15 + interestingMinutes[0]);
            return;
        } else {
            for (int i = 0; i < numberOfMin - 1; i++) {
                if (interestingMinutes[i] + 15 < interestingMinutes[i+1]) {
                    System.out.println(interestingMinutes[i] + 15);
                    return;
                }
            }
            System.out.println(interestingMinutes[numberOfMin - 1] + 15 > 90 ? 90 : interestingMinutes[numberOfMin - 1] + 15);
        }
    }
}
