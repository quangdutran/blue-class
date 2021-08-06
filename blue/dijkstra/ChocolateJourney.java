package com.company.blue.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ChocolateJourney {
    static List<Node>[] graph;
    static int [] distanceStart;
    static int [] distanceEnd;
    static int [] path;
    static int [] cititesWithChoco;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int edges = input.nextInt();
        int numberOfChocoCities = input.nextInt();
        int expiryTime = input.nextInt();
        cititesWithChoco = new int [numberOfChocoCities];
        graph = new List[vertices + 1];

        distanceStart = new int [vertices + 1];
        distanceEnd = new int[vertices + 1];
        path = new int [vertices + 1];

        for (int i = 0; i < numberOfChocoCities; i++) {
            cititesWithChoco[i] = input.nextInt();
        }

        for (int i = 0; i < vertices + 1; i++) {
            graph[i] = new ArrayList<>();
            distanceStart[i] = Integer.MAX_VALUE;
            distanceEnd[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < edges; i++) {
            int index = input.nextInt();
            int adjacent = input.nextInt();
            int w = input.nextInt();
            graph[index].add(new Node(adjacent, w));
            graph[adjacent].add(new Node(index, w));
        }

        int start = input.nextInt();
        int end = input.nextInt();

        checkMinPath(start, end, expiryTime);

    }

    private static void checkMinPath(int start, int end, int expiryTime) {
        djikstraStart(start);
        djikstraEnd(end);

        int minTime = Integer.MAX_VALUE;
        for (int i : cititesWithChoco) {
            if (distanceStart[i] + distanceEnd[i] < minTime && distanceEnd[i] <= expiryTime) {
                minTime = distanceStart[i] + distanceEnd[i];
            }
        }

        if (minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }
    }

    private static void djikstraStart(int start) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        minHeap.add(new Node(start, 0));
        distanceStart[start] = 0;

        while (!minHeap.isEmpty()) {
            Node root = minHeap.poll();
            int id = root.id;
            int weight = root.dist;

            for (Node n : graph[id]) {
                if (weight + n.dist < distanceStart[n.id]) {
                    distanceStart[n.id] = weight + n.dist;
                    minHeap.add(new Node(n.id, distanceStart[n.id]));
                }
            }
        }
    }

    private static void djikstraEnd(int start) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        minHeap.add(new Node(start, 0));
        distanceEnd[start] = 0;

        while (!minHeap.isEmpty()) {
            Node root = minHeap.poll();
            int id = root.id;
            int weight = root.dist;

            for (Node n : graph[id]) {
                if (weight + n.dist < distanceEnd[n.id]) {
                    distanceEnd[n.id] = weight + n.dist;
                    minHeap.add(new Node(n.id, distanceEnd[n.id]));
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
