package com.company;

import java.util.Scanner;

public class Chocolate {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfBars = input.nextInt();
        int [] times = new int [numOfBars];
        for (int i = 0; i < numOfBars; i++) {
            times[i] = input.nextInt();
        }
        input.close();

        if (numOfBars == 1) {
            System.out.println(1 + " " + 0);
            return;
        } else if (numOfBars == 2) {
            System.out.println(1 + " " + 1);
            return;
        } else {
            int aliceBarIndex = 0;
            int bobBarIndex = numOfBars - 1;

            int aliceTime = times[0];
            int bobTime = times[numOfBars - 1];

            int aliceTotalBars = 1;
            int bobTotalBars = 1;

            while (aliceTotalBars + bobTotalBars < numOfBars) {
                if (aliceTime <= bobTime) {
                    ++aliceTotalBars;
                    ++aliceBarIndex;
                    aliceTime += times[aliceBarIndex];
                } else {
                    ++bobTotalBars;
                    --bobBarIndex;
                    bobTime += times[bobBarIndex];
                }
            }

            System.out.println(aliceTotalBars + " " + bobTotalBars);
        }
    }
}
