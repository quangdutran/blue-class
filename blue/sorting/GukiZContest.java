package com.company.blue.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GukiZContest {
    public static void main(String[] args) {
        Scanner input  = new Scanner(System.in);
        int [] position = new int [2001];
        for (int i = 0; i < position.length; i++) {
            position[i] = 0;
        }
        int numberOfStudents = input.nextInt();
        Integer [] ranking = new Integer [numberOfStudents];
        int [] rankingOriginal = new int [numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            ranking[i] = input.nextInt();
            rankingOriginal[i] = ranking[i];
        }
        input.close();
        Arrays.sort(ranking, Collections.reverseOrder());

        if (ranking.length == 1) {
            System.out.println(1);
            return;
        }
        for (int i = 0; i < ranking.length; i++) {
            if (i == 0) {
                position[ranking[i]] = 1;
            } else {
                if (ranking[i] < ranking[i-1]) {
                    position[ranking[i]] = 1 + i;
                }
            }
        }

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print(position[rankingOriginal[i]] + " ");
        }
    }
}
