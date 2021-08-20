package com.company.alg.orange.divideconquer;


import java.util.*;

class Point {
    double x;
    double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosetPairProblem {

    static final int INF = Integer.MAX_VALUE;

    static double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y, 2));
    }

    static double bruteforce(ArrayList<Point> points, int left, int right) {
        double min_dist = INF;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j < right; j++) {
                min_dist = Math.min(min_dist, calculateDistance(points.get(i), points.get(j)));
            }
        }
        return min_dist;
    }

    static double stripCloset(ArrayList<Point> point_set, int left, int right, int mid, double dist_min) {
        Point point_mid = point_set.get(mid);
        ArrayList<Point> splitted_points = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (Math.abs(point_set.get(i).x - point_mid.x) <= dist_min) {
                splitted_points.add(point_set.get(i));
            }
        }

        Collections.sort(splitted_points, Comparator.comparingDouble(o -> o.y));
        double smallest = INF;
        int l = splitted_points.size();
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l && (splitted_points.get(j).y - splitted_points.get(i).y) < dist_min; j++) {
                double d = calculateDistance(splitted_points.get(i), splitted_points.get(j));
                smallest = Math.min(smallest, d);
            }
        }
        return smallest;
    }

    static double minimalDistance(ArrayList<Point> point_set, int left, int right) {
        if (right - left <= 3) {
            return bruteforce(point_set, left, right);
        }
        int mid = (right + left) / 2;
        double dist_left = minimalDistance(point_set, left, mid);
        double dist_right = minimalDistance(point_set, mid + 1, right);
        double dist_min = Math.min(dist_left, dist_right);
        return Math.min(dist_min, stripCloset(point_set, left, right, mid, dist_min));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            int line = input.nextInt();
            if (line == 0) {
                return;
            }
            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < line; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                points.add(new Point(first, second));
            }
            points.sort(Comparator.comparing(o -> o.x));
            double ans = minimalDistance(points, 0, line);
            if (ans > 10000) {
                System.out.println("INFINITY");
            } else {
                System.out.printf("%.4f", ans);
                System.out.println();
            }
        }
    }
}
