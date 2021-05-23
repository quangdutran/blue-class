package com.company.alg.complexity;

import java.util.Scanner;

public class Wrath {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfPeople = input.nextInt();
        int [] wrath = new int [numOfPeople + 1];
        for (int i = 1; i < numOfPeople + 1; i++) {
            wrath[i] = input.nextInt();
        }
        input.close();

        int deadPeopleIndex = numOfPeople + 1;
        int alive = 0;
        for (int i = numOfPeople; i > 0; i--) {
            if (i < deadPeopleIndex) {
                alive++;
            }
            if (wrath[i] > 0 && (i - wrath[i]) < deadPeopleIndex) {
                    deadPeopleIndex = i - wrath[i];
            }
            if (deadPeopleIndex < 0) {
                break;
            }
        }
        System.out.println(alive);
    }
}
