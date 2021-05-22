package com.company.heap;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AddAll {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int number = input.nextInt();
            if (number == 0) break;
            int [] array = new int [number];
            for (int i = 0; i < number; i++) {
                array[i] = input.nextInt();
            }
            handle(array);
        }
        input.close();
    }

    private static void handle(int [] array) {
        PriorityQueue<BigInteger> heap = new PriorityQueue<>();
        if (array.length == 2) {
            System.out.println(array[0] + array[1]);
        } else {
//            Arrays.sort(array);
//            BigInteger result = BigInteger.valueOf(array.length - 1).multiply(BigInteger.valueOf(array[0]))
//                    .add(BigInteger.valueOf(array.length - 1).multiply(BigInteger.valueOf(array[1])));
//            for (int i = 2; i < array.length; i++) {
//                result = result.add(BigInteger.valueOf(array.length - i)
//                        .multiply(BigInteger.valueOf(array[i])));
//            }
            BigInteger result = BigInteger.ZERO;
            for (int i = 0; i < array.length; i++) {
                heap.add(BigInteger.valueOf(array[i]));
            }
            while (heap.size() > 1) {
                BigInteger sum = heap.remove().add(heap.remove());
                result = result.add(sum);
                heap.add(sum);
            }
            System.out.println(result);
        }

    }
}
