package com.company.alg.orange.topo;

import java.util.*;

public class TopologicalSorting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int edges = input.nextInt();
        ArrayList<Integer> [] graph = new ArrayList[vertices + 1];
        for (int i = 0; i <= vertices; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            graph[first].add(second);
        }


        int [] degree = new int[vertices + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (ArrayList<Integer> integers : graph) {
            for (Integer integer : integers) {
                degree[integer]++;
            }
        }

        for (int i = 1; i <= vertices; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int top = queue.poll();
            result.add(top);

            for (int neighbor : graph[top]) {
                degree[neighbor]--;
                if (degree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }



            if (result.size() != vertices) {
                System.out.println("Sandro fails");
                return;
            }


        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
