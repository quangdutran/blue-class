package com.company.alg.orange.divideconquer;

import java.util.*;


/**
 * Codeforces 429D
 *
 * Idea: Look at the formular, it represent the distance between two points (i, sum till a[i]) and (j, sum till a[j])
 */

public class TrickyFunction {
    static final int INF = Integer.MAX_VALUE;

    static double calculateDistance(Point a , Point b) {
        return Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y, 2);
    }

    static double bruteforce(ArrayList<Point> points, int left, int right) {
        double min_dist = INF;
        for (int i = left; i < right; i++) {
            for (int j =  i + 1; j < right; j++) {
                min_dist = Math.min(min_dist, calculateDistance(points.get(i), points.get(j)));
            }
        }
        return min_dist;
    }

    static double stripCloset(ArrayList<Point> pointSet, int left, int right, int mid, double min_dist) {
        Point pointMid = pointSet.get(mid);
        ArrayList<Point> splittedPoints = new ArrayList<>();
        for (int i = left; i < right; i++) {
            if (Math.abs(pointSet.get(i).x - pointMid.x) <= min_dist) {
                splittedPoints.add(pointSet.get(i));
            }
        }

        Collections.sort(splittedPoints, Comparator.comparingDouble(o -> o.y));
        double smallest = INF;
        int l = splittedPoints.size();
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l && Math.pow(splittedPoints.get(j).y - splittedPoints.get(i).y, 2) <min_dist; j++) { //Power 2 of y subtraction because in this problem, the distance is not being calculated by square root
                double d = calculateDistance(splittedPoints.get(i), splittedPoints.get(j));
                smallest = Math.min(smallest, d);
            }
        }
        return smallest;
    }

    static double minimalDistance(ArrayList<Point> pointSet, int left, int right) {
        if (right - left <= 3) {
            return bruteforce(pointSet, left, right);
        }
        int mid = (right+left) / 2;
        double distLeft = minimalDistance(pointSet, left, mid);
        double distRight = minimalDistance(pointSet, mid + 1, right);
        double distMin = Math.min(distLeft, distRight);
        return Math.min(distMin, stripCloset(pointSet, left, right, mid, distMin));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int [] array = new int [length];
        ArrayList<Point> pointSet = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int num = input.nextInt();
            if (i == 0) {
                array[i] = num;
            } else {
                array[i] = array[i-1] + num;
            }
            pointSet.add(new Point(i + 1, array[i]));
        }
        pointSet.sort(Comparator.comparing(o -> o.x));
        System.out.println((int)minimalDistance(pointSet, 0, length));
    }
}
