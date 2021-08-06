package com.company.blue.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TravellingCost {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfRoads = input.nextInt();
        List<Node>[] graph = new List[501];
        for (int i = 0; i < 501; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfRoads; i++) {
            int index = input.nextInt();
            int adjacent = input.nextInt();
            int weight = input.nextInt();
            graph[index].add(new Node(adjacent, weight));
            graph[adjacent].add(new Node(index, weight));
        }
        int start = input.nextInt();
        int endNodes = input.nextInt();
        int [] ends = new int [endNodes];
        for (int i = 0; i < endNodes; i++) {
            ends[i] = input.nextInt();
        }
        input.close();

        processGraph(graph, start, ends);
    }

    private static void processGraph(List<Node> [] graph, int start, int [] ends) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int [] distance = new int [graph.length];
        int [] path = new int [graph.length];
        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        dijkstra(graph, heap, distance, path, start);

        for (int end : ends) {
            if (distance[end] == Integer.MAX_VALUE) {
                System.out.println("NO PATH");
            } else {
                System.out.println(distance[end]);
            }
        }
    }

    private static void dijkstra(List<Node>[] graph, PriorityQueue<Node> heap, int [] distance, int [] path, int start) {
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
                    path[neighbor.id] = currentNodeId;
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
