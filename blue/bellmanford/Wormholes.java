package com.company.blue.bellmanford;

import java.util.Scanner;

public class Wormholes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();

        for (int i = 0; i < numberOfCases; i++) {
            int vertices = input.nextInt();
            int edges = input.nextInt();

            int [] distance = new int [vertices];
            for (int j = 0; j < vertices; j++) {
                distance[j]  = Integer.MAX_VALUE;
            }
            int [] path = new int [vertices];
            Edge [] graph = new Edge[edges];

            for (int j = 0; j < edges; j++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                graph[j] = new Edge(first, second, third);
            }

            bellmanford(distance, path, graph);
            boolean flag = false;
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    flag = true;
                    break;
                }
            }

            System.out.println(flag ? "possible" : "not possible");


        }
        input.close();
    }

    private static void bellmanford(int [] distance, int [] path, Edge [] graph) {
        distance[0] = 0;

        for (int i = 1; i < distance.length - 1; i++) {
            for (Edge edge : graph) {
                int u = edge.source;
                int v = edge.target;
                int w = edge.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
    }

    static class Edge {
        int source;
        int target;
        int weight;
        public Edge(int source, int target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }
}
