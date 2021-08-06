package com.company.blue.bellmanford;

import java.util.Scanner;

public class NegativeWeight {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int n = input.nextInt();
            int m = input.nextInt();
            int q = input.nextInt();
            int s = input.nextInt();
            if (n ==0 && m == 0 && q == 0 && s == 0) {
                break;
            }

            Edge[] graph = new Edge [m];
            int [] ends = new int [q];

            for (int i = 0; i < m; i++) {
                graph[i] = new Edge(input.nextInt(), input.nextInt(), input.nextInt());
            }
            for (int i = 0; i < q; i++) {
                ends[i] = input.nextInt();
            }

            int [] distance = new int [n];
            int [] path = new int [n];
            for (int i = 0; i < n; i++) {
                distance[i] = Integer.MAX_VALUE;
                path[i] = -1;
            }

            if (n == 2) {
                checkCaseTwoNodes(graph, s);
                return;
            }

            bellmanford(distance, path, graph, s);
            boolean [] flags = new boolean [distance.length];
            hasLoop(distance, graph, flags);

            for (int e : ends) {
                if (distance[e] == Integer.MAX_VALUE) {
                    System.out.println("Impossible");
                } else if (flags[e]) {
                    System.out.println("-Infinity");
                } else {
                    System.out.println(distance[e]);
                }
            }
            System.out.println();
        }

        input.close();
    }

    private static void checkCaseTwoNodes(Edge [] graph, int s) {
        if (graph.length == 1) {
            System.out.println(graph[0].weight);
        } else if (graph.length == 2 && graph[0].weight + graph[1].weight < 0) {
            System.out.println("-Infinity");
        } else {
            System.out.println(graph[1 - s].weight);
        }
    }

    private static void bellmanford(int [] distance, int [] path, Edge [] graph, int s) {
        distance[s] = 0;

        for (int i = 1; i < distance.length - 1; i++) {
            for (Edge e : graph) {
                int u = e.source;
                int v = e.target;
                int w = e.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    path[v] = u;
                }
            }
        }
    }

    private static void hasLoop(int [] distance, Edge [] graph, boolean [] flags) {
        for (int i = 1; i < distance.length - 1; i++) {
            for (Edge e : graph) {
                int u = e.source;
                int v = e.target;
                int w = e.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    flags[v] = true;
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
