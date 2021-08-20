package com.company.alg.orange.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;


public class RomaAndChanging {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();

        int [] array = new int [n];
        long sum = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
            sum += array[i];
            minHeap.add(array[i]);
        }

        for (int i = 0; i < k; i++) {
            int top = minHeap.poll();
            sum = sum - top + (top * -1);
            minHeap.add(top * -1);
        }
        System.out.println(sum);
    }
}
