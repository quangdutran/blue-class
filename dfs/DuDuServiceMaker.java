package com.company.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DuDuServiceMaker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        for (int i = 0; i < numberOfCases; i++) {
            int numberOfVertices = input.nextInt();
            int numberOfDependencies = input.nextInt();
            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j < numberOfVertices + 1; j++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < numberOfDependencies; j++) {
                int doc1 = input.nextInt();
                int doc2 = input.nextInt();
                graph.get(doc1).add(doc2);
            }

            handleGraph(graph, numberOfVertices);

        }
        input.close();
    }

    private static void handleGraph(List<List<Integer>> graph, int vertices) {
        int visited [] = new int[vertices + 1];

        for (int i = 1; i < vertices + 1; i++) {
            if (dfs(graph, i, visited)) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    private static boolean dfs(List<List<Integer>> graph, int start, int [] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = 1;
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            visited[vertex] = 1;
            if (graph.get(vertex).isEmpty()) {
                visited[vertex] = 2;
            }
            for (int depend : graph.get(vertex)) {
                if (visited[depend] == 1) {
                    return true;
                } else if (visited[depend] == 0) {
                    stack.push(depend);
                }
            }
        }

        visited[start] = 2;
        return false;
    }

    private static boolean dfsRecursive(List<List<Integer>> graph, int start, int [] visited) {
        visited[start] = 1;

        for (int neighbor : graph.get(start)) {
            if (visited[neighbor] == 1) {
                return true;
            } else if (visited[neighbor] == 0) {
                if (dfsRecursive(graph, neighbor, visited)) {
                    return true;
                }
            }
        }
        visited[start] = 2;
        return false;
    }
}
