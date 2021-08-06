package com.company.alg.orange.topo;

import java.util.*;

public class AnswerTheBoss {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = input.nextInt();
        int count = 0;
        while(--testCase >= 0) {
            int employees = input.nextInt();
            int relations = input.nextInt();
            List<Integer>[] graph = new List[employees];
            for (int i = 0; i < employees; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < relations; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                graph[second].add(first);
            }

            int degree []  =new int [employees];
            for (int i = 0; i < employees; i++) {
                for (Integer neighbor : graph[i]) {
                    degree[neighbor]++;
                }
            }

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            int rank = 1;
            for (int i = 0; i  < degree.length; i++) {
                if (degree[i] == 0) {
                    minHeap.add(i);
                    map.put(i, rank);
                }
            }
            List<Integer> order = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                int top = minHeap.poll();
                order.add(top);
                List<Integer> childHasZeroDegree = new ArrayList<>();
                for (Integer child : graph[top]) {
                    degree[child]--;
                    if (degree[child] == 0) {
                        childHasZeroDegree.add(child);
                    }
                }
                if (!childHasZeroDegree.isEmpty()) {
                    minHeap.addAll(childHasZeroDegree);
                    int newRank = ++rank;
                    childHasZeroDegree.forEach(c -> map.put(c, newRank));
                }
            }

            System.out.println("Scenario #" +  ++count + ":");
            for (int i = 0; i < employees; i++) {
                System.out.println(map.get(order.get(i)) + " " + order.get(i));
            }
        }
    }
}