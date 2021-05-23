package com.company.dfs;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BishuAndGirlfriend {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        List<Integer> [] graph = new List[vertices + 1];
        for (int i = 0; i < vertices + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < vertices - 1; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            graph[first].add(second);
            graph[second].add(first);
        }

        int numberOfGirls = input.nextInt();
        int [] girls = new int [numberOfGirls];
        for (int i = 0; i < numberOfGirls; i++) {
            girls[i] = input.nextInt();
        }
        input.close();

        int [] path = new int[vertices + 1];
        boolean [] visited = new boolean[vertices + 1];
        for (int i =0; i < vertices + 1; i++) {
            path[i] = -1;
        }

        DFS(graph, path, visited);

        int steps = 1000;
        int girlPosition = graph.length - 1;

        int distance [] = new int [numberOfGirls];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = countStep(1, girls[i], path);
        }
        for (int i = 0; i < girls.length; i++) {
            if (distance[i] < steps) {
                steps = distance[i];
                girlPosition = girls[i];
            } else if (distance[i] == steps) {
                girlPosition = Math.min(girlPosition, girls[i]);
            }
        }

        System.out.println(girlPosition);

    }

    private static int countStep(int start, int finish, int [] path) {
        List<Integer> b = new ArrayList<>();
        while (true) {
            b.add(finish);
            finish = path[finish];
            if (start == finish) {
                b.add(finish);
                break;
            }
        }
        return b.size();
    }

    private static void DFS(List<Integer>[] graph, int [] path, boolean [] visited) {
        Stack<Integer> stack = new Stack<>();

        stack.add(1);
        visited[1] = true;

        while(!stack.isEmpty()) {
            int current = stack.pop();
            for (Integer neighbor : graph[current]) {
                if (!visited[neighbor]) {
                    stack.add(neighbor);
                    visited[neighbor] = true;
                    path[neighbor] = current;
                }
            }
        }
    }
}
