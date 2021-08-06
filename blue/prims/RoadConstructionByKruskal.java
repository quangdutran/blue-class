package com.company.blue.prims;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RoadConstructionByKruskal {

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

    static class Node implements Comparable<Node>{
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        input.nextLine();
        int countCase = 0;
        while (--cases >= 0) {

            int roads = input.nextInt();
            input.nextLine();
            HashMap<String, Integer> cityIndex = new HashMap<>();
            int cityIndexCount = -1;
            PriorityQueue<Node> minHeap = new PriorityQueue<>();

            //Read input
            while (input.hasNextLine()) {
                String next = input.nextLine();
                if (next.isEmpty()) {
                    break;
                }

                String split[] = next.split(" ");
                String city1 = split[0];
                String city2 = split[1];
                int weight = Integer.parseInt(split[2]);
                if (!cityIndex.containsKey(city1)) {
                    cityIndex.put(city1, ++cityIndexCount);
                }
                if (!cityIndex.containsKey(city2)) {
                    cityIndex.put(city2, ++cityIndexCount);
                }
                minHeap.add(new Node(cityIndex.get(city1), cityIndex.get(city2), weight));
            }

            DSU sdu = new DSU(cityIndex.size());
            int lengthOfSpanningTree = cityIndex.size() - 1;// vertices - 1
            int count = 0;
            int ans = 0;

            while (!minHeap.isEmpty() && count <= lengthOfSpanningTree) {
                Node top = minHeap.poll();
                if (sdu.findSet(top.a) != sdu.findSet(top.b)) {
                    count++;
                    sdu.unionSet(top.a, top.b);
                    ans += top.weight;
                }
            }

            System.out.println("Case " + ++countCase + ": " + (count != lengthOfSpanningTree ? "Impossible" : ans));
        }
    }

}
