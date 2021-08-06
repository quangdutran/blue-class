package com.company.alg.orange.bitmanipulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PowerOfTwo {
    public static void main(String[] args) {
        System.out.println("ABC".matches(""));
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int length = input.nextInt();
            int [] array = new int [length];
            for (int i = 0; i < length; i++) {
                array[i] = input.nextInt();
            }
            System.out.println(check(array) ? "YES" : "NO");
        }
    }

    private static boolean check(int [] array) {
        for (int i  = 0; i < 30; i++) {
            List<Integer> list = new ArrayList<>();
            for (int n : array) {
                if (checkBitNEqual1(i, n)) {
                    list.add(n);
                }
            }
            int andValue = list.stream().reduce((a,b) -> a&b).orElse(0);
            if (andValue == Math.pow(2,i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkBitNEqual1(int n, int number) {
        return ((number>>n) & 1) == 1;
    }
}
