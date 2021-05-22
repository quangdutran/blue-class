package com.company.heap;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MonkMultilplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int [] array = new int [size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }
        input.close();



        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });



        for (int i = 0; i < size; i++) {
            heap.add(array[i]);
            if (i < 2) {
                System.out.println(-1);
            } else if (i == 2) {
                System.out.println(BigInteger.valueOf(array[0]).multiply(BigInteger.valueOf(array[1])).multiply(BigInteger.valueOf(array[2])));
            } else {
                int first = heap.remove();
                int second = heap.remove();
                int third = heap.remove();

                System.out.println(BigInteger.valueOf(first).multiply(BigInteger.valueOf(second)).multiply(BigInteger.valueOf(third)));
                heap.add(third);
                heap.add(second);
                heap.add(first);
            }
        }
    }
}
