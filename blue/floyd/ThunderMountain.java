package com.company.blue.floyd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThunderMountain {
    static double [][] matrix;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        int count = 0;
        while (--cases >= 0) {
            int vertices = input.nextInt();
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                points.add(new Point(input.nextInt(), input.nextInt()));
            }
            matrix = new double[vertices][vertices];

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (i != j) {
                        Point a = points.get(i);
                        Point b = points.get(j);
                        double distance = calculateDistance(a,b);
                        if (distance <= 10.0) {
                            matrix[i][j] = distance;
                        } else {
                            matrix[i][j] = Double.MAX_VALUE;
                        }
                    } else {
                        matrix[i][j] = 0.0;
                    }
                }
            }
            floyd();

            double max = 0;

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (i != j && matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                }
            }
            System.out.println("Case #" + ++count + ":");
            System.out.println(max == Double.MAX_VALUE ? "Send Kurdy" : String.format("%,.4f",max));
            System.out.println();
        }
    }

    private static void floyd() {
        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][k] == Double.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][k] != Double.MAX_VALUE && matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double calculateDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x-a.x,2) + Math.pow(b.y-a.y,2));
    }
}
