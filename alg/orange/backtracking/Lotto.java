package com.company.alg.orange.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lotto {
    private static List<Integer> originalList;
    static List<Integer> array;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            int first = input.nextInt();
            if (first == 0) {
                return;
            }
            originalList = new ArrayList<>();
            for (int i = 0; i < first; i++) {
                originalList.add(input.nextInt());
            }
            array = new ArrayList<>();
            combination(first, 0, 6);
        }
    }

    private static void combination(int n, int left, int k){
        if (k == 0) {
            printAns(array);
            System.out.println();
            return;
        }
        for (int i = left; i < n; i++) {
            array.add(originalList.get(i));
            combination(n, i+1, k-1);
            array.remove(array.size() - 1);
        }
    }

    private static void printAns(List<Integer> aray) {
        for (Integer integer : aray) {
            System.out.print(integer + " ");
        }
    }
}
