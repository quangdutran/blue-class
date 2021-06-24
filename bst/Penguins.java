package com.company.bst;

import java.util.Scanner;

public class Penguins {
    public static void main(String[] args) {
        final String EM = "Emperor Penguin";
        final String LI = "Little Penguin";
        final String MA = "Macaroni Penguin";
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        int [] frequency = new int [3];
        for (int i = 0; i < cases; i++) {
            String line = input.nextLine();
            if (EM.equals(line)) {
                frequency[0]++;
            } else if (LI.equals(line)) {
                frequency[1]++;
            } else {
                frequency[2]++;
            }
        }
        int indexMax = 0;
        int max = frequency[0];
        for (int i = 1; i < 3; i++) {
            if (frequency[i] > max) {
                max = frequency[i];
                indexMax = i;
            }
        }
        if (indexMax == 0) {
            System.out.println(EM);
        } else if (indexMax == 1) {
            System.out.println(LI);
        } else {
            System.out.println(MA);
        }
    }
}
