package com.company;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [] frequency = new int[100001];
        for (int i = 1; i < 100001; i++) {
            frequency[i] = 0;
        }
        int totalNumber = input.nextInt();
        int k = input.nextInt();
        int [] numbers = new int[totalNumber + 1];
        for (int i = 1; i < totalNumber + 1; i++) {
            numbers[i] = input.nextInt();
        }
        input.close();


        int countDistinctValue = 0;
        int right = 0;
        int left = 1;
        while (right < totalNumber && countDistinctValue < k) {
            right++;
            frequency[numbers[right]]++;
            if (frequency[numbers[right]] == 1) {
                countDistinctValue++;
            }

            if (countDistinctValue == k) {
                break;
            }
        }

        if (countDistinctValue < k) {
            System.out.println("-1 -1");
            return;
        }

        while (left < right && countDistinctValue == k) {
            left++;
            frequency[numbers[left-1]]--;
            if (frequency[numbers[left-1]] == 0) {
                countDistinctValue--;
            }

            if (countDistinctValue < k) {
                left = left - 1;
                break;
            }
        }

        System.out.println(left + " " + right);
    }
}
