package com.company.blue.dsu;


import java.util.Scanner;

/**
 * No multiple edges, no self loop so if there is loop, it satisfied the cycle condition.
 *  Three set rooted tree will satisfy when number of vertices = number of edges (number of vertices making cycle = number of edges in the cycle)
 */
public class Cthulhu {
    private static int [] parent;
    private static int [] rank;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int vertices = input.nextInt();
        int edges = input.nextInt();

        if (vertices != edges) {
            System.out.println("NO");
            return;
        }

        parent = new int[vertices + 1];
        rank = new int[vertices + 1];
        for (int i =0; i <= vertices; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        boolean hasCycle = false;
        for (int i = 0; i < edges; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            boolean checkCycle = unionSet(first, second);
            if (checkCycle) hasCycle = true;
        }

        int oneParent = 0;
        for (int i = 1; i <= vertices; i++) {
            if (i == parent[i]) {
                oneParent++;
            }
        }

        System.out.println(oneParent == 1 && hasCycle ? "FHTAGN!" : "NO");
    }

    private static int findSet(int u) {
        return u == parent[u] ? u : findSet(parent[u]);
    }

    private static boolean unionSet(int u, int v) {
        int up = findSet(u);
        int vp = findSet(v);
        if (up == vp) {
            return true;
        }

        if (rank[up] > rank[vp]) {
            parent[vp] = up;
        } else if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else {
            parent[up] = vp;
            rank[vp]++;
        }
        return false;
    }
}
