package com.company;

import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int firstSize = input.nextInt();
        int secondSize = input.nextInt();

        int firstPick = input.nextInt();
        int secondPick = input.nextInt();

        int [] firstArray = new int[firstSize];
        int [] secondArray = new int[secondSize];

        for (int i = 0; i < firstSize; i++) {
            firstArray[i] = input.nextInt();
        }

        for (int i = 0; i < secondSize; i++) {
            secondArray[i] = input.nextInt();
        }

        int largestNumPickedFromFirstArray = firstArray[firstPick - 1];
        int count = 0;
        for (int i = 0; i < secondSize; i++) {
            if (secondArray[i] > largestNumPickedFromFirstArray) {
                ++count;
                if (count >= secondPick) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }
}
