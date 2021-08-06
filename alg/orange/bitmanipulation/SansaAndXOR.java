package com.company.alg.orange.bitmanipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SansaAndXOR {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        while(--numberOfCases >= 0) {
            int length = input.nextInt();
            int [] array = new int [length];
            List<Integer> validNum = new ArrayList<>();
            for (int i = 0; i< length; i++) {
                array[i] = input.nextInt();
                if (isOddOccurence(i+1, length)) {
                    validNum.add(array[i]);
                }
            }
            System.out.println(validNum.stream().reduce((a,b) -> a^b).orElse(0));
        }
    }

    private static boolean isOddOccurence(int indexBase1, int length) {
        return (indexBase1 * (length - indexBase1 + 1)) % 2 != 0;
    }
}
