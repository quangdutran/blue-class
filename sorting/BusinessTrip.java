package com.company.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class BusinessTrip {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int minHeight = input.nextInt();
        int [] monthlyHigh = new int[12];
        for (int i = 0; i < 12; i++) {
            monthlyHigh[i] = input.nextInt();
        }
        input.close();

        if (minHeight == 0) {
            System.out.println(0);
            return;
        }

        Arrays.sort(monthlyHigh);

        int grow = 0;
        int count = 0;
        for (int i = 11; i > -1; i--) {
            count++;
            grow += monthlyHigh[i];
            if (grow >= minHeight) {
                break;
            }
        }

        System.out.println(grow < minHeight ? -1 : count);

    }
}
