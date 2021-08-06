package com.company.alg.orange.bitmanipulation;

import java.util.Scanner;

public class AishAndXor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int prefixSum [] = new int[length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= length; i++) {
            prefixSum[i] = prefixSum[i - 1] + input.nextInt();
        }

        int query = input.nextInt();
        for (int i = 0; i < query; i++) {
            int left = input.nextInt();
            int right = input.nextInt();
            int numberOfBit1 = prefixSum[right] - prefixSum[left - 1];
            //int xorValue = numberOfBit1 & 1;
            int xorValue = numberOfBit1 % 2 == 0 ? 0 : 1;
            System.out.println(xorValue + " " + (right - left + 1 - numberOfBit1));
        }
    }
}
