package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Prayatna {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        for (int i = 0; i < numberOfCases; i++) {
            int vertices = input.nextInt();
            int edges = input.nextInt();
            List<List<Integer>> graph = new ArrayList<>();
            for (int k = 0; k < vertices; k++) {
                graph.add(new ArrayList<>());
            }
            for (int j = 0; j < edges; j++) {
                int first = input.nextInt();
                int second = input.nextInt();
                graph.get(first).add(second);
                graph.get(second).add(first);
            }
            DFS(graph);
        }
        input.close();
    }


    private static void DFS(List<List<Integer>> graph) {
        int [] path = new int[graph.size()];
        boolean [] visited = new boolean[graph.size()];
        for (int i =0; i < graph.size(); i++) {
            path[i] = -1;
        }

        DFS(0, visited, graph, path);
        int count = 1;
        for (int i = 1; i < graph.size(); i++)  {
            if (!visited[i]) {
                DFS(i, visited, graph, path);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void DFS(int start, boolean [] visited, List<List<Integer>> graph, int [] path) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        while(!stack.isEmpty()) {
            int current = stack.pop();
            for (Integer neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                    path[neighbor] = current;
                }
            }
        }
    }

}
