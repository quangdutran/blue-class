package com.company.alg.orange.bitmanipulation;


import java.util.BitSet;
import java.util.Scanner;

//UAV
public class SplittingNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            int number = input.nextInt();
            if (number == 0) {
                return;
            }

            int a = 0;
            int b = 0;
            BitSet bs = BitSet.valueOf(new long[]{number});

        }
    }
}
