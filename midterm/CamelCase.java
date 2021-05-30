package com.company.midterm;

import java.util.Scanner;

public class CamelCase {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        int count = 1;
        word = word.trim();
        for (int i = 0; i < word.length(); i++) {
            if (isCapital(word.charAt(i))) {
                count++;
            }
        }
        System.out.println(word.isEmpty() ? 0 : count);
        input.close();
    }

    private static boolean isCapital(char c) {
        int val = (int) c;
        return val >= 65 && val <= 90 ? true : false;
    }
}
