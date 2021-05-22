package com.company;

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
        boolean visited [] = new boolean[vertices + 1];
        int path [] = new int[vertices + 1];
        for (int j = 0; j < vertices + 1; j++) {
            path[j] = -1;
        }
        for (int i = 1; i < vertices + 1; i++) {
            if (!visited[i]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                visited[i] = true;
                while (!stack.isEmpty()) {
                    int vertex = stack.pop();
                    for (int depend : graph.get(vertex)) {
                        if (visited[depend] ) {
                            System.out.println("YES");
                            return;
                        }
                        stack.push(depend);
                        visited[depend] = true;
                    }
                }
            }
        }
        System.out.println("NO");
    }

//    private boolean checkPath(int start, int end, int[]path) {
//
//    }
}
