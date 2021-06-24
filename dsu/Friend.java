package com.company.dsu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Friend {
    private static int MAX = 30000;
    private static int [] parent;
    private static int [] rank;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int n = input.nextInt();
            int m = input.nextInt();

            parent = new int [n + 1];
            rank = new int[n + 1];

            for (int i = 1; i < parent.length; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                unionSet(first, second);
            }

            for (int i = 1; i < parent.length; i++) {
                parent[i] = findSet(i);
            }

            HashMap<Integer, Integer> freq = new HashMap<>();
            int max = 0;
            for (int i : parent) {
                if (freq.containsKey(i)) {
                    freq.put(i, freq.get(i) + 1);
                } else {
                    freq.put(i, 1);
                }
            }
            for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
                max = Math.max(max, e.getValue());
            }

            System.out.println(max);
        }
    }

    private static int findSet(int u) {
        if (u == parent[u]) {
            return u;
        } else {
            return findSet(parent[u]);
        }

//        while (u != parent[u]) {
//            u = parent[u];
//        }
//        return u;
    }

    private static void unionSet(int u, int v) {
        int parentU = findSet(u);
        int parentV = findSet(v);
        if (parentU == parentV) {
            return;
        }

        if (rank[parentU] > rank[parentV]) {
            parent[parentV] = parentU;
        } else if (rank[parentU] < rank[parentV]) {
            parent[parentU] = parentV;
        } else {
            parent[parentU] = parentV;
            rank[parentV]++;
        }
    }
}
