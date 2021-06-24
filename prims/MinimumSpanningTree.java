package com.company.prims;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
    private static int [] dist;
    private static int [] path;
    private static boolean [] visited;

    private void prim(int start, List<List<Node>> graph) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int nodes = graph.size();
        dist = new int [nodes];
        path = new int [nodes];
        visited = new boolean[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        minHeap.add(new Node(start, 0));
        dist[start] = 0;

        while (!minHeap.isEmpty()) {
            Node min = minHeap.poll();
            if (min.dist != dist[min.id]) {
                continue;
            }
            visited[min.id] = true;

            for (Node neighbor : graph.get(min.id)) {
                if (!visited[neighbor.id] && neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = neighbor.dist;
                    minHeap.add(new Node(neighbor.id, neighbor.dist));
                    path[neighbor.id] = min.id;
                }
            }
        }
    }

    private void printMST() {
        int n = dist.length;
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            if (path[i] == -1) {
                continue;
            }
            totalWeight += dist[i];
            System.out.printf("%d %d: %d\n", path[i],i,dist[i]);
        }
        System.out.println("Weight: " + totalWeight);
    }

    class Node implements Comparable<Node> {
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
