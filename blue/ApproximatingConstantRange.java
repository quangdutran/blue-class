package com.company.blue;

import java.util.Scanner;

public class ApproximatingConstantRange {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalNumber = input.nextInt();
        int [] array = new int[totalNumber];
        for (int i = 0; i < totalNumber; i++) {
            array[i] = input.nextInt();
        }
        input.close();

        if (totalNumber == 2) {
            System.out.println(2);
            return;
        }



        int maxLength = 2;
        for (int i = 0; i < totalNumber; i++) {
            int counter = i;
            int max = array[i];
            int min = array[i];

            while (max - min <= 1) {
                counter++;
            }

            if (i <= totalNumber - maxLength) {
                break;
            }
        }
    }
}
