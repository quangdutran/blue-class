package com.company.blue.dsu;

import java.util.Arrays;
import java.util.Scanner;

public class Wars {
    private static int [] parent;
    private static int [] rank;

    public static void main(String[] args) {
        final int MAX = 10000;
        Scanner input = new Scanner(System.in);

        rank = new int [20000 + 1];
        parent = new int [20000 + 1];
        Arrays.fill(rank, 1);
        for (int i = 0; i < 20001; i++) {
            parent[i] = i;
        }

        int people = input.nextInt();
        input.nextLine();

        while (input.hasNextLine()) {
            String next = input.nextLine();
            String [] num = next.split(" ");
            int action = Integer.parseInt(num[0]);
            int first = Integer.parseInt(num[1]);
            int second = Integer.parseInt(num[2]);
            if (action == 0) {
                break;
            }

            if (action == 1) {
                if (findSet(first) == findSet(second + MAX)) {
                    System.out.println(-1);
                } else {
                    unionSet(first, second);
                    unionSet(first + MAX, second + MAX);
                }
            } else if (action == 2) {
                if (findSet(first) == findSet(second)) {
                    System.out.println(-1);
                } else {
                    unionSet(first, second + MAX);
                    unionSet(first + MAX, second);
                }
            } else if (action == 3) {
                if (findSet(first) == findSet(second)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else {
                if (findSet(first) == findSet(second + MAX)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }

        }

    }

    private static int findSet(int u) {
        return u == parent[u] ? u : findSet(parent[u]);
    }

    private static void unionSet(int u, int v) {
        int up = findSet(u);
        int vp = findSet(v);
        if (up == vp) {
            return;
        }

        if (rank[up] < rank[vp]) {
            parent[up] = vp;
        } else if (rank[up] > rank[vp]) {
            parent[vp] =up;
        } else {
            parent[up] = vp;
            rank[vp]++;
        }
    }
}
