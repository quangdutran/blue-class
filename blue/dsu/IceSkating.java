package com.company.blue.dsu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class IceSkating {

    static class DSU {
        private static int [] parent;
        private static int [] rank;

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
            } else if (rank[vp] > rank[up]) {
                parent[up] = vp;
            } else {
                parent[up] = vp;
                rank[vp]++;
            }
        }

        public int count() {
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (i == parent[i]) {
                    count++;
                }
            }
            return count;
        }
    }
    private static Point [] pointSet;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int points = input.nextInt();

        pointSet = new Point[points];
        DSU dsu = new DSU(points);

        for (int i = 0; i < points; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            Point p = new Point(first, second);
            pointSet[i] = p;
        }

        for (int i = 0; i < points; i++) {
            for (int j = i +1; j < points; j++) {
                if (isJoinable(pointSet[i], pointSet[j])) {
                    dsu.unionSet(i, j);
                }
            }
        }
        System.out.println(dsu.count() -1);
    }

    private static boolean isJoinable(Point a, Point b) {
        return (a.x == b.x) || (a.y == b.y);
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point p = (Point) obj;
                return p.x == this.x && p.y == this.y;
            } else {
                return false;
            }
        }
    }
}
