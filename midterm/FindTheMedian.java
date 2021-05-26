package com.company.midterm;

import java.util.Arrays;
import java.util.Scanner;

public class FindTheMedian {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int [] array = new int [size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        input.close();
        Arrays.sort(array);
        System.out.println(array[size/2]);
    }
}
