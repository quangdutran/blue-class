package com.company.prims;

import java.util.*;
import java.util.stream.Stream;

public class MST {
    private static int [] dist;
    private static int [] path;
    private static boolean [] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        List<Node> [] graph = new List[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            int third = input.nextInt();
            graph[first].add(new Node(second, third));
            graph[second].add(new Node(first, third));
        }
        prim(1, graph);
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += dist[i];
        }
        System.out.println(sum);
    }

    private static void prim(int start, List<Node> [] graph) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int nodes = graph.length;
        dist = new int [nodes];
        path = new int [nodes];
        visited = new boolean[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        minHeap.add(new Node(start, 0));
        dist[start] = 0;

        while (!minHeap.isEmpty()) {
            Node min = minHeap.poll();
//            if (min.dist != dist[min.id]) {
//                continue;
//            }
            int u = min.id;
            visited[u] = true;

            for (Node neighbor : graph[u]) {
                int v = neighbor.id;
                int w = neighbor.dist;
                if (!visited[v] && w < dist[v]) {
                    dist[v] = w;
                    minHeap.add(new Node(v, w));
                }
            }
        }
    }

    private void printMST() {
        int n = dist.length;
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            if (dist[i] == -1) {
                continue;
            }
            totalWeight += dist[i];
            System.out.printf("%d %d: %d\n", path[i],i,dist[i]);
        }
        System.out.println("Weight: " + totalWeight);
    }

    static class Node implements Comparable<Node> {
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
