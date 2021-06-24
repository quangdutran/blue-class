package com.company.prims;

import java.util.*;

public class ConnectingCampus {
    static double [] dist;
    static boolean [] visited;
    static int [] path;
    static Point existingPoints [];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();

        List<Node>[] graph = new List[nodes + 1];

        for (int i = 0; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }
        Point [] points = new Point[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            points[i] = new Point(x,y);
        }

        int existingRoute = input.nextInt();
        existingPoints = new Point[existingRoute];
        for (int i = 0; i < existingRoute; i++) {
            existingPoints[i] = new Point(input.nextInt(), input.nextInt());
        }

        makeGraph(points, graph);
        prim(1, graph);

        int n = dist.length;
        int totalWeight = 0;

        for (int i = 1; i < n; i++) {
            if (dist[i] == -1) {
                continue;
            }
            if (!containPoint(path[i], i)) {
                totalWeight += dist[i];
            }
        }

        System.out.println(totalWeight);
    }

    private static boolean containPoint(int x, int y) {
        for (Point existingPoint : existingPoints) {
            if( (existingPoint.x == x && existingPoint.y == y) || (existingPoint.x == y && existingPoint.y == x) ) {
                return true;
            }
        }
        return false;
    }

    private static void prim(int start, List<Node> [] graph) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        int nodes = graph.length;
        dist = new double [nodes];
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

            for (Node neighbor : graph[min.id]) {
                if (!visited[neighbor.id] && neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = neighbor.dist;
                    minHeap.add(new Node(neighbor.id, neighbor.dist));
                    path[neighbor.id] = min.id;
                }
            }
        }
    }

    private static void makeGraph(Point [] points, List<Node>[] graph) {

        for (int i = 1; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = calculateDist(points[i], points[j]);
                graph[i].add(new Node(j, distance));
                graph[j].add(new Node(i, distance));
            }
        }
    }

    static class Node implements Comparable<Node> {
        private Integer id;
        private Double dist;

        public Node(int id, double dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist.compareTo(o.dist);
        }
    }

    static double calculateDist(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
