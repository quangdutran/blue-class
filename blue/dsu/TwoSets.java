package com.company.blue.dsu;

import java.util.Arrays;
import java.util.Scanner;

public class TwoSets {
    private static int [] parent;
    private static int [] rank;
    private static int [] set;
    private static int [] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int a = input.nextInt();
        int b = input.nextInt();

        if (size % 2 != 0) {
            System.out.println("NO");
        }

        array = new int[size];
        set = new int[size];
        Arrays.fill(set, -1);

        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (a - array[i] == array[j]) {
                    set[i] =  0;
                    set[j] = 0;
                } else if (b - array[i] == array[j]) {
                    set[i] = 1;
                    set[j] = 1;
                }
            }
        }

        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (set[i] == -1) {
                flag = false;
            }
        }

        if (flag) {
            System.out.println("YES");
            for (int i : set) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println("NO");
        }
    }

    private static int findSet(int u) {
        return (u == parent[u]) ? u : findSet(parent[u]);
    }

    private static void unionSet(int u, int v) {
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
            parent[vp] = up;
            rank[up]++;
        }
    }
}
