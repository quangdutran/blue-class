package com.company.blue.prims;

import java.util.*;

public class CobbledStreet {
    private static int [] dist;
    private static boolean [] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();

        while(--cases >= 0) {
            int price = input.nextInt();
            int vertices = input.nextInt();
            List<Node> [] graph = new List[vertices + 1];
            for (int i = 1; i < vertices + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            dist = new int [vertices + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited = new boolean[vertices + 1];
            int edges = input.nextInt();
            for (int i = 0; i < edges; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                graph[first].add(new Node(second, third));
                graph[second].add(new Node(first, third));
            }

            prim(graph);
            long sum = 0L;
            for (int i = 1; i <= vertices; i++) {
                sum += dist[i];
            }
            sum *= price;
            System.out.println(sum);
        }
    }

    private static void prim(List<Node> [] graph) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        minHeap.add(new Node(1, 0));
        dist[1] = 0;

        while (!minHeap.isEmpty()) {
            Node min = minHeap.poll();
            int currentId = min.id;
            int currentDist = min.dist;
            visited[currentId] = true;
            for (Node neighbor : graph[currentId]) {
                if (!visited[neighbor.id] && neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = neighbor.dist;
                    minHeap.add(neighbor);
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        private int id;
        private Integer dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist.compareTo(o.dist);
        }
    }
}
