package com.company.blue.alg.complexity;

import java.util.Scanner;

public class SerejaDima {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalNumber = input.nextInt();
        int [] array = new int[totalNumber];

        for (int i = 0; i < totalNumber; i++) {
            array[i] = input.nextInt();
        }
        input.close();

        int leftmost = 0;
        int rightmost = totalNumber - 1;
        int sereja = 0;
        int dima = 0;
        for (int i = 0; i < totalNumber; i++) {
            int greater;
            if (array[leftmost] > array[rightmost]) {
                greater = array[leftmost];
                leftmost++;
            } else {
                greater = array[rightmost];
                rightmost--;
            }
            if (i % 2 == 0) {
                sereja += greater;
            } else {
                dima += greater;
            }
        }

        System.out.println(sereja + " " + dima);
    }
}
