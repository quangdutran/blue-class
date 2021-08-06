package com.company.blue;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfButtons = input.nextInt();

        int[] fastenArray = new int[numberOfButtons];
        for (int i = 0; i < numberOfButtons; i++) {
            fastenArray[i] = input.nextInt();
        }
        input.close();

        if (numberOfButtons == 1) {
            if (fastenArray[0] == 1) {
                System.out.println("YES");
            } else System.out.println("NO");
        } else {
            int flag = 0;
            for (int i = 0; i < numberOfButtons; i++) {
                if (fastenArray[i] == 0) {
                    flag++;
                    if (flag > 1) {
                        System.out.println("NO");
                        return;
                    }
                }
            }
            if (flag == 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
