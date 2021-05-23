package com.company.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class EightPointSets {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Point [] points = new Point[8];

        int [] xValues = new int [8];
        int [] sortedX = new int [8];
        int [] yValues = new int[8];
        int [] sortedY = new int [8];
        for (int i = 0; i < 8; i++) {
            points[i] = new Point();
            points[i].x = input.nextInt();
            points[i].y = input.nextInt();
            xValues[i] = points[i].x;
            yValues[i] = points[i].y;
            sortedX[i] = points[i].x;
            sortedY[i] = points[i].y;
        }
        input.close();

        Arrays.sort(sortedX);
        Arrays.sort(sortedY);

        int countDistinctX = 1;
        int [] distinctX = new int[3];
        distinctX[0] = sortedX[0];
        int countDistinctY = 1;
        int [] distinctY = new int[3];
        distinctY[0] = sortedY[0];
        for (int i = 0; i < 7; i++) {
            if (sortedX[i] < sortedX[i+1]) {
                countDistinctX++;
                if (countDistinctX > 3) {
                    System.out.println("ugly");
                    return;
                }
                int temp = countDistinctX - 1;
                distinctX[temp] = sortedX[i+1];
            }
            if (sortedY[i] < sortedY[i+1]) {
                countDistinctY++;
                if (countDistinctY > 3) {
                    System.out.println("ugly");
                    return;
                }
                int temp = countDistinctY -1;
                distinctY[temp] = sortedY[i+1];
            }
        }
        if (countDistinctX != 3 || countDistinctY != 3) {
            System.out.println("ugly");
            return;
        }

        Arrays.sort(points, Collections.reverseOrder());
        boolean valid = points[0].y == distinctY[0] && points[1].y == distinctY[0] && points[2].y == distinctY[0] &&
                        points[0].x == distinctX[0] && points[1].x == distinctX[1] && points[2].x == distinctX[2]
                && points[3].y == distinctY[1] && points[4].y == distinctY[1]
                && points[3].x == distinctX[0] && points[4].x == distinctX[2]
                && points[5].y == distinctY[2] && points[6].y == distinctY[2] && points[7].y == distinctY[2]
                && points[5].x == distinctX[0] && points[6].x == distinctX[1] && points[7].x == distinctX[2];
        if (valid) {
            System.out.println("respectable");
        } else {
            System.out.println("ugly");
        }

    }

    public static class Point implements Comparable<Point> {
        int x;
        int y;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            } else {
                Point p = (Point) obj;
                return x == p.x && y == p.y;
            }
        }

        @Override
        public int compareTo(Point o) {
            if (o.y > y || o.y < y) {
                return o.y - y;
            } else {
                return o.x - x;
            }
        }
    }
}
