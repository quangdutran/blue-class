package com.company.heap;

import java.math.BigInteger;
import java.util.*;

public class Promotion {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfDays = input.nextInt();
        List<List<Integer>> allDayBills = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            allDayBills.add(new ArrayList<>());
            int numberOfBills = input.nextInt();
            for (int j = 0; j < numberOfBills;j++) {
                int bill = input.nextInt();
                allDayBills.get(i).add(bill);
            }
        }
        input.close();
        handle(allDayBills);
    }

    private static void handle(List<List<Integer>> allDayBills) {
        BigInteger result = BigInteger.ZERO;
        int [] billFrequency = new int [(int) (Math.pow(10,6) + 1)];


        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < allDayBills.size(); i++) {
            for (Integer dailyBill : allDayBills.get(i)) {
                maxHeap.add(dailyBill);
                minHeap.add(dailyBill);
                billFrequency[dailyBill]++;
            }

            if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
                while(billFrequency[maxHeap.peek()] == 0) {
                    maxHeap.remove();
                }
                while(billFrequency[minHeap.peek()] == 0) {
                    minHeap.remove();
                }
                Integer max = maxHeap.remove();
                Integer min = minHeap.remove();
                result = result.add(BigInteger.valueOf(max - min));
                billFrequency[max]--;
                billFrequency[min]--;
            }
        }
        System.out.println(result);
    }
}
