package com.company.blue.finaltest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Freckles {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();

        while (--cases >=0) {
            int vertices = input.nextInt();
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                double first = input.nextDouble();
                double second = input.nextDouble();
                points.add(new Point(first, second));
            }

            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    double distance = checkPath(points.get(i).x, points.get(i).y, points.get(j).x, points.get(j).y);
                    minHeap.add(new Node(i,j,distance));
                }
            }
            DSU dsu = new DSU(vertices);
            int count = 0;
            double ans = 0.0;
            while (!minHeap.isEmpty() && count <= vertices - 1) {
                Node top = minHeap.poll();
                if (dsu.findSet(top.a) != dsu.findSet(top.b)) {
                    count++;
                    dsu.unionSet(top.a, top.b);
                    ans += top.weight;
                }
            }

            System.out.println(String.format("%.2f", ans));
            System.out.println();
        }
    }

    private static double checkPath(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 -y2, 2));
    }

    static class DSU {
        private int parent[];
        private int rank[];

        public DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int findSet(int u) {
            return u == parent[u] ? u : findSet(parent[u]);
        }

        public void unionSet(int u, int v) {
            int up = findSet(u);
            int vp = findSet(v);
            if (up == vp) {
                return;
            }

            if (rank[up] > rank[vp]) {
                parent[vp] = up;
            } else if (rank[up] < rank[vp]) {
                parent[up] = vp;
            } else {
                parent[up] = vp;
                rank[vp]++;
            }
        }
    }

    static class Point {
        private double x;
        private double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node>{
        private int a;
        private int b;
        private Double weight;

        public Node(int a, int b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight.compareTo(o.weight);
        }
    }
}
