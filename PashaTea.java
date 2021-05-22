package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class PashaTea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfGirl = input.nextInt();
        int totalCapacity = input.nextInt();
        int [] cups = new int[2*numberOfGirl];
        for (int i = 0; i < 2 * numberOfGirl; i++) {
            cups[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(cups);


        int startIndexForBoy = numberOfGirl;

        double maxWaterForGirl = (double) ((double) totalCapacity/3)/numberOfGirl;
        double waterForGirl;
        if (maxWaterForGirl >= cups[0]) {
            waterForGirl = cups[0];
        } else {
            waterForGirl = maxWaterForGirl;
        }


        double substract = 0.000001;
        double closest = (double) cups[startIndexForBoy] / 2;
        if (closest < waterForGirl) {
            waterForGirl = closest;
        }
        while (cups[startIndexForBoy] < waterForGirl * 2) {
            waterForGirl -= substract;
        }

        System.out.println(numberOfGirl * 3 * waterForGirl);

    }
}
