package com.company.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MiceAndMaze {

    private static int maxTimeAllowed;
    private static int exit;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCells = input.nextInt();
        exit = input.nextInt();
        maxTimeAllowed = input.nextInt();
        int numberOfConnections = input.nextInt();
        List<Node>[] graph = new List[numberOfCells + 1];
        for (int i = 0; i < numberOfCells + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfConnections; i++) {
            int index = input.nextInt();
            int adjacent = input.nextInt();
            int weight = input.nextInt();
            graph[index].add(new Node(adjacent, weight));
        }
        input.close();

        int ans = 0;
        for (int i = 1; i < numberOfCells + 1; i++) {
            if (i == exit) {
                ans++;
            } else {
                ans += thisMouseCanEscape(graph, i) ? 1 : 0;
            }
        }
        System.out.println(ans);
    }

    private static boolean thisMouseCanEscape(List<Node>[] graph, int start) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int [] distance = new int [graph.length + 1];
        for (int i = 0; i < graph.length + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        heap.add(new Node(start, 0));
        distance[start] = 0;

        while (!heap.isEmpty()) {
            Node top = heap.remove();
            int currentNodeId = top.id;
            int weight = top.dist;
            if (distance[currentNodeId] != weight || distance[currentNodeId] > maxTimeAllowed) {
                continue;
            }
            for (Node neighbor : graph[currentNodeId]) {
                if (weight + neighbor.dist < distance[neighbor.id]) {
                    distance[neighbor.id] = weight + neighbor.dist;
                    heap.add(new Node(neighbor.id, distance[neighbor.id]));
                }
            }
        }

        return distance[exit] <= maxTimeAllowed;
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
