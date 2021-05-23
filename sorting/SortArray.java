package com.company.sorting;

import java.util.Scanner;

public class SortArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalNumbers = input.nextInt();
        int [] numbers = new int [totalNumbers];
        for (int i = 0; i < totalNumbers; i++) {
            numbers[i] = input.nextInt();
        }
        input.close();

        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < totalNumbers - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                if (firstIndex == -1) {
                    firstIndex = i;
                } else if (secondIndex > -1) {
                    System.out.println("no");
                    return;
                }
            } else if (firstIndex != -1 && secondIndex == -1 && numbers[i] < numbers[i+1]) {
                secondIndex = i;
            }
        }

        if ((firstIndex == -1 && secondIndex == -1) ) {
            System.out.println("yes");
            System.out.println("1 1" );
            return;
        } else if ((firstIndex == 0 && secondIndex == -1)) {
            System.out.println("yes");
            System.out.println(firstIndex + 1 +" " + totalNumbers);
            return;
        }

        if (firstIndex == 0 && secondIndex > 0 && numbers[firstIndex] < numbers[secondIndex+1]) {
            System.out.println("yes");
            System.out.println(firstIndex + 1 + " " + (secondIndex + 1));
        } else if (firstIndex > 0 && secondIndex == -1 && numbers[totalNumbers -1] > numbers[firstIndex -1]) {
            System.out.println("yes");
            System.out.println(firstIndex + 1 + " " + totalNumbers);
        } else if (firstIndex > 0 && secondIndex > 0 && numbers[secondIndex] > numbers[firstIndex - 1]
                    && numbers[firstIndex] < numbers[secondIndex + 1]) {
            System.out.println("yes");
            System.out.println(firstIndex + 1 + " " + (secondIndex + 1));
        } else {
            System.out.println("no");
        }
    }
}
