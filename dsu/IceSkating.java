package com.company.dsu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class IceSkating {
    private static Point [] pointSet;
    private static Point [] parent;
    private static int [] rank;
    private static HashMap<Point, Integer> map;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int points = input.nextInt();

        parent = new Point[points];
        rank = new int [points];
        Arrays.fill(rank, 1);
        pointSet = new Point[points];
        map = new HashMap<>();

        for (int i = 0; i < points; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            Point p = new Point(first, second);
            pointSet[i] = p;
            parent[i] = p;
            map.put(p, i);
        }

        for (int i = 0; i < points; i++) {
            for (int j = i + 1; j < points; j++) {
                if (isJoinable(pointSet[i], pointSet[j])) {
                    unionSet(pointSet[i], pointSet[j]);
                }
            }
        }

        int setNum = 0;
        for (int i = 0; i < points; i++) {
            if (pointSet[i].equals(parent[i])) {
                setNum++;
            }
        }

        System.out.println(setNum - 1);
    }

    private static boolean isJoinable(Point a, Point b) {
        return a.x == b.x || a.y == b.y;
    }

    private static Point findParent(Point p) {
        int index = map.get(p);
        return pointSet[index].equals(parent[index]) ? pointSet[index] : findParent(parent[index]);
    }

    private static void unionSet(Point a, Point b) {
        Point ap = findParent(a);
        Point bp = findParent(b);

        if (ap.equals(bp)) {
            return;
        }

        int indexA = map.get(a);
        int indexB = map.get(b);

        if (rank[indexA] > rank[indexB]) {
            parent[indexB] = ap;
        } else if (rank[indexA] < rank[indexB]) {
            parent[indexA] = bp;
        } else {
            parent[indexA] = bp;
            rank[indexB]++;
        }
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
