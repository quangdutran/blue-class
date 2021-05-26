package com.company.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Commandos {
    private static int [] distance;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        for (int i = 0; i < numberOfCases; i++) {
            int numberOfNodes = input.nextInt();
            List<Node> [] graph = new List[numberOfNodes];
            int numberOfEdge = input.nextInt();
            for (int j = 0; j < numberOfNodes; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < numberOfEdge; j++) {
                int first = input.nextInt();
                int secondd = input.nextInt();
                graph[first].add(new Node(secondd, 1));
                graph[secondd].add(new Node(first, 1));
            }
            int start = input.nextInt();
            int end = input.nextInt();
            distance = new int [graph.length];

            processGraph(graph, start);

            int max = distance[0];
            int indexMax = 0;
            for (int j = 1; j < graph.length; j++) {
                if (distance[j] > max) {
                    max = distance[j];
                    indexMax = j;
                }
            }

            if (end != indexMax) {
                processGraph(graph, indexMax);
                max += distance[end];
            }
            System.out.println("Case " + (i+1) + ": " + max);
        }
        input.close();
    }

    private static void processGraph(List<Node>[] graph, int start) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        dijkstra(graph, heap, distance, start);
    }

    private static void dijkstra(List<Node>[] graph, PriorityQueue<Node> heap, int [] distance, int start) {
        heap.add(new Node(start, 0));//start point so weight is 0
        distance[start] = 0;

        while (!heap.isEmpty()) {
            Node top = heap.remove();
            int currentNodeId = top.id;
            int weight = top.dist;
            if (distance[currentNodeId] != weight) {
                continue;
            }
            for (Node neighbor : graph[currentNodeId]) {
                if (weight + neighbor.dist < distance[neighbor.id]) {
                    distance[neighbor.id] = weight + neighbor.dist;
                    heap.add(new Node(neighbor.id, distance[neighbor.id]));
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        private Integer id;
        private Integer dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist.compareTo(o.dist);
        }
    }
}
