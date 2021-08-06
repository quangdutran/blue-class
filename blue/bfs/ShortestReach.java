package com.company.blue.bfs;

import java.util.*;

public class ShortestReach {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int numberOfQueries = input.nextInt();
        final int weight = 6;

        for (int i = 0; i < numberOfQueries; i++) {
            int totalVertices = input.nextInt();
            int totalEdges = input.nextInt();

            Set<Integer> [] graph = new HashSet[totalVertices + 1];
            for (int k = 0; k < totalVertices + 1; k++) {
                graph[k] = new HashSet<>();
            }
            for (int j = 0; j < totalEdges; j++) {
                int first = input.nextInt();
                int second = input.nextInt();
                graph[first].add(second);
                graph[second].add(first);
            }
            int startVertices = input.nextInt();
            process(totalVertices, totalEdges, startVertices, graph);
        }
        input.close();
    }

    private static void process(int totalVertices, int totalEdges, int startVertices, Set<Integer> [] graph) {
        boolean [] visited = new boolean [totalVertices + 1];
        int [] path = new int [totalVertices + 1];
        for (int i = 0; i < totalVertices + 1; i++) {
            path[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertices);
        visited[startVertices] = true;

        while (!queue.isEmpty()) {
            int currentVertices = queue.remove();
            for (Integer neighbor : graph[currentVertices]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    path[neighbor] = currentVertices;
                }
            }
        }

        for (int i = 1; i < totalVertices + 1; i++) {
            if (i != startVertices) {
                System.out.print(calculateDistance(startVertices, i, path) + " ");
            }
        }
        System.out.println();
    }
    private static int calculateDistance(int start, int finish, int[] path) {
        if (path[finish] == -1) {
            return -1;
        }

        List<Integer> b = new ArrayList<>();
        while (true) {
            b.add(finish);
            finish = path[finish];
            if (start == finish) {
                b.add(finish);
                break;
            }
        }

        return (b.size() - 1) * 6;
    }
}
