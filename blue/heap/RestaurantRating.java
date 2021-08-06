package com.company.blue.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RestaurantRating {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfQueries = input.nextInt();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int count = 0;
        for (int i = 0; i < numberOfQueries; i++) {
            if (input.nextInt() == 1) {
                maxHeap.add(input.nextInt());
                count++;
            } else {
                int oneThird = count / 3;
                if (oneThird == 0) {
                    System.out.println("No reviews yet");
                } else {
                    while (minHeap.size() < oneThird) {
                        int r = maxHeap.remove();
                        minHeap.add(r);
                    }
                    while (minHeap.size() > oneThird) {
                        maxHeap.add(minHeap.remove());
                    }
                    if (maxHeap.peek() > minHeap.peek()) {
                        int max = maxHeap.remove();
                        int min = minHeap.remove();
                        maxHeap.add(min);
                        minHeap.add(max);
                    }

                    System.out.println(minHeap.peek());


                }
            }
        }
        input.close();
    }
}
