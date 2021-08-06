package com.company.blue.dsu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class GraphConnectivity {
    private static int MAX = 26;
    private static int [] parent;
    private static boolean [] hasNode;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        input.nextLine();
        while(--cases >= 0) {
            parent = new int [MAX];
            hasNode = new boolean[MAX];
            int hasNodeIndex = -1;
            for(int i = 0; i < MAX; i++) {
                parent[i] = i;
            }
            while (input.hasNextLine()) {
                String next = input.nextLine();
                if (next.length() == 0) {
                    break;
                }
                if (next.length() == 1) {
                    hasNodeIndex = getIndexChar(next.charAt(0));
                } else {
                    unionSet(getIndexChar(next.charAt(0)), getIndexChar(next.charAt(1)));
                }
            }
            for (int i = 0; i <= hasNodeIndex; i++) {
                hasNode[i] = true;
            }
            int ans = 0;
            for (int i = 0; i < MAX; i++) {
                if (i == parent[i] && hasNode[i]) {
                    ans++;
                }
            }
            System.out.println(ans);
            System.out.println();

        }
    }

    private static int getIndexChar(char c) {
        return c - 'A';
    }

    private static int findSet(int u) {
        if (u == parent[u]) {
            return u;
        } else {
            return findSet(parent[u]);
        }
    }

    private static void unionSet(int u, int v) {
        int up = findSet(u);
        int vp = findSet(v);
        if (up == vp) {
            return;
        }
        parent[vp] = up;
    }
}
