package com.company;

import java.util.Scanner;

public class GeorgeRound {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int minGoodRoundNum = input.nextInt();
        int preparedRoundNum = input.nextInt();
        int [] goodRounds = new int[minGoodRoundNum];
        int [] preparedRounds = new int[preparedRoundNum];
        for (int i = 0; i < minGoodRoundNum; i++) {
            goodRounds[i] = input.nextInt();
        }
        for (int i = 0; i < preparedRoundNum; i++) {
            preparedRounds[i] = input.nextInt();
        }
        input.close();

        int count = 0;
        int initialIndex = 0;
        outerloop:
        for (int i = 0; i < minGoodRoundNum; i++) {
            for (int j = initialIndex; j < preparedRoundNum; j++) {
                if (goodRounds[i] <= preparedRounds[j]) {
                    count++;
                    initialIndex = j + 1;
                    break;
                } else if (j == preparedRoundNum - 1) { //all prepared time < all good time
                    break outerloop;
                }
            }
        }
        System.out.println(goodRounds.length - count);
    }
}
