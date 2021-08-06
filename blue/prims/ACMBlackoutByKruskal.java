package com.company.blue.prims;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ACMBlackoutByKruskal {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            List<Node> allNodes = new ArrayList<>();
            int N = input.nextInt();
            DSU dsu = new DSU(N + 1);
            int M = input.nextInt();
            for (int i = 0; i < M; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                minHeap.add(new Node(first, second, third));
                allNodes.add(new Node(first, second, third));
            }
            int count = 0;

            int min1 = 0;
            int min2 = Integer.MAX_VALUE;
            List<Node> mst = new ArrayList<>();
            while (!minHeap.isEmpty() && count <= N - 1) {
                Node top = minHeap.poll();
                if (dsu.findSet(top.a) != dsu.findSet(top.b)) {
                    count++;
                    dsu.unionSet(top.a, top.b);
                    min1 += top.weight;
                    mst.add(top);
                }
            }

            for (Node n : mst) {
                int min = 0;
                minHeap = new PriorityQueue<>();
                dsu = new DSU(N +1);
                for (Node node : allNodes) {
                    if (!(n.a == node.a && n.b == node.b)) {
                        minHeap.add(node);
                    }
                }
                int c = 0;
                while (!minHeap.isEmpty() && c <= N - 1) {
                    Node top = minHeap.poll();
                    if (dsu.findSet(top.a) != dsu.findSet(top.b)) {
                        c++;
                        dsu.unionSet(top.a, top.b);
                        min += top.weight;
                    }
                }
                min2 = Math.min(min, min2);
            }
            System.out.println(min1 + " " + min2);
        }
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

    static class Node implements Comparable<Node> {
        private int a;
        private int b;
        private Integer weight;

        public Node(int a, int b, int weight) {
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
