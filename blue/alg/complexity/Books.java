package com.company.blue.alg.complexity;

import java.util.Scanner;

public class Books {
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int numberOfBooks = input.nextInt();
        int numberOfFreeTime = input.nextInt();
        int [] bookTime = new int[numberOfBooks];
        for(int i = 0; i < numberOfBooks; i++) {
            bookTime[i] = input.nextInt();
        }
        input.close();

        int index = 0;
        int finalCount = 0;
        while (index < numberOfBooks) {
            int totalTime = 0;
            int count = 0;
            for (int i = index; i < numberOfBooks; i++) {
                totalTime += bookTime[i];
                count++;
                if (totalTime == numberOfFreeTime) {
                    if (count > finalCount) {
                        finalCount = count;
                    }
                    break;
                } else if (totalTime > numberOfFreeTime) {
                    if (count - 1 > finalCount) {
                        finalCount = count - 1;
                    }
                    break;
                } else if (i == numberOfBooks - 1 && count > finalCount) {
                    //End of array but totalTime still less than free time
                    finalCount = count;
                }
            }
            if (finalCount == numberOfBooks - index) {
                break;
            }
            index++;
        }
        System.out.println(finalCount);
    }
}
