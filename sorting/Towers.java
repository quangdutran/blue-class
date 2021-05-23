package com.company.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class Towers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [] frequency = new int[1001];
        for (int i = 0; i < frequency.length; i++) {
            frequency[i] = 0;
        }
        int totalBars = input.nextInt();
        int [] length = new int [totalBars];
        for (int i = 0 ; i < totalBars; i++) {
            length[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(length);
        int counter = 1;
        int frequencyMax = 0;
        int countDistinct = 1;
        for (int i = 0; i < totalBars - 1; i++) {
            if (counter > frequencyMax) {
                frequencyMax = counter;
            }
            if (length[i+1] > length[i]) {
                counter = 1;
                countDistinct++;
            } else if (length[i+1] == length[i]) {
                counter++;
            }
        }

        if (counter > frequencyMax) {
            frequencyMax = counter;
        }

        System.out.println(frequencyMax + " " + countDistinct);
    }
}
