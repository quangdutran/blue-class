package com.company.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SendingEmail {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = input.nextInt();
        for (int i = 0; i < testCase; i++) {
            int numberOfNode = input.nextInt();
            int numberOfEdge = input.nextInt();
            int start = input.nextInt();
            int end = input.nextInt();
            List<Node> [] graph = new List[numberOfNode];
            for (int j = 0; j < numberOfNode; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < numberOfEdge; j++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int weight = input.nextInt();
                graph[first].add(new Node(second, weight));
                graph[second].add(new Node(first, weight));
            }
            System.out.println("Case #" + (i + 1) +": " + processGraph(graph, start, end));
        }
        input.close();
    }

    private static String processGraph(List<Node> [] graph, int start, int end) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int [] distance = new int [graph.length];
        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        dijkstra(graph, heap, distance, start);
        if (distance[end] == Integer.MAX_VALUE) {
            return "unreachable";
        } else {
            return distance[end] + "";
        }
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
