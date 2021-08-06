package com.company.blue.finaltest;

import java.util.Scanner;

public class UbiquitousReligions {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (input.hasNextLine()) {
            int vertices = input.nextInt();
            int edges = input.nextInt();
            if (vertices == 0 && edges == 0) {
                break;
            }
            DSU dsu = new DSU(vertices + 1);
            for (int i = 0; i < edges; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                dsu.unionSet(first, second);
            }
            System.out.println("Case " + ++count + ": " + dsu.count());
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

        public int count() {
            int count = 0;
            for (int i = 1; i < parent.length; i++) {
                if (i == parent[i]) {
                    count++;
                }
            }
            return count;
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
}
