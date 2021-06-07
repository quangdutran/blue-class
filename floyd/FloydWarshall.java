package com.company.floyd;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {
    static int [][] adjacentMatrix;
    static int [][] distance;
    static int [][] path;
    public static void main(String[] args) {
        setup();
        floyd();
        printPath(0,4);
    }

    private static void printPath(int from, int to) {
        System.out.println("From " + from + " to " + to + " costs " + distance[from][to]);
        getPath(from,to);
        getPathRecursive(from,to);
    }

    private static void getPath(int from, int to) {
        if (from == to) {
            System.out.println(from);
            return;
        }

        if (path[from][to] == -1) {
            System.out.println("NO PATH");
            return;
        }

        List<Integer> pathPrint = new ArrayList<>();
        while (true) {
            pathPrint.add(to);
            to = path[from][to];
            if (to == from) {
                break;
            }
        }
        for (int i = pathPrint.size() - 1; i >=0; i--) {
            System.out.print(pathPrint.get(i) + " ");
        }
    }

    private static void getPathRecursive(int from, int to) {
        if (from == to) {
            System.out.println(from);
        } else {
            if (path[from][to] == -1) {
                System.out.println("NO PATH");
            } else {
                getPathRecursive(from, path[from][to]);
                System.out.print(to + " ");
            }
        }
    }

    private static boolean checkGraphHasNegativeLoop() {
        for (int i = 0; i < 6; i++) {
            if (distance[i][i] < 0) {
                return true;
            }
         }
        return false;
    }

    private static void floyd() {
        for (int k = 0; k < 6; k++) {
            for (int i = 0; i < 6; i ++) {
                if (distance[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < 6; j++) {
                    if (distance[k][j] != Integer.MAX_VALUE && distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }

    private static void setup() {
        adjacentMatrix = new int[6][6];
        adjacentMatrix[0][0] = 0;
        adjacentMatrix[0][1] = 1;
        adjacentMatrix[0][2] = Integer.MAX_VALUE;
        adjacentMatrix[0][3] = Integer.MAX_VALUE;
        adjacentMatrix[0][4] = Integer.MAX_VALUE;
        adjacentMatrix[0][5] = Integer.MAX_VALUE;
        adjacentMatrix[1][0] = Integer.MAX_VALUE;
        adjacentMatrix[1][1] = 0;
        adjacentMatrix[1][2] = 5;
        adjacentMatrix[1][3] = -2;
        adjacentMatrix[1][4] = Integer.MAX_VALUE;
        adjacentMatrix[1][5] = 7;
        adjacentMatrix[2][0] = Integer.MAX_VALUE;
        adjacentMatrix[2][1] = Integer.MAX_VALUE;
        adjacentMatrix[2][2] = 0;
        adjacentMatrix[2][3] = Integer.MAX_VALUE;
        adjacentMatrix[2][4] = Integer.MAX_VALUE;
        adjacentMatrix[2][5] = -1;
        adjacentMatrix[3][0] = 2;
        adjacentMatrix[3][1] = Integer.MAX_VALUE;
        adjacentMatrix[3][2] = -1;
        adjacentMatrix[3][3] = 0;
        adjacentMatrix[3][4] = 4;
        adjacentMatrix[3][5] = Integer.MAX_VALUE;
        adjacentMatrix[4][0] = Integer.MAX_VALUE;
        adjacentMatrix[4][1] = Integer.MAX_VALUE;
        adjacentMatrix[4][2] = Integer.MAX_VALUE;
        adjacentMatrix[4][3] = 3;
        adjacentMatrix[4][4] = 0;
        adjacentMatrix[4][5] = Integer.MAX_VALUE;
        adjacentMatrix[5][0] = Integer.MAX_VALUE;
        adjacentMatrix[5][1] = Integer.MAX_VALUE;
        adjacentMatrix[5][2] = Integer.MAX_VALUE;
        adjacentMatrix[5][3] = Integer.MAX_VALUE;
        adjacentMatrix[5][4] = 1;
        adjacentMatrix[5][5] = 0;

        distance = new int [6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                distance[i][j] = adjacentMatrix[i][j];
            }
        }

        //path[i][j] = -1 means no route, otherwise, it is the first node
        path = new int [6][6];
        path[0][1] = 0;
        path[0][0] = -1;
        path[0][2] = -1;
        path[0][3] = -1;
        path[0][4] = -1;
        path[0][5] = -1;
        path[1][0] = -1;
        path[1][1] = -1;
        path[1][2] = 1;
        path[1][3] = 1;
        path[1][4] = -1;
        path[1][5] = 1;
        path[2][0] = -1;
        path[2][1] = -1;
        path[2][2] = -1;
        path[2][3] = -1;
        path[2][4] = -1;
        path[2][5] = 2;
        path[3][3] = -1;
        path[3][0] = 3;
        path[3][1] = -1;
        path[3][2] = 3;
        path[3][4] = 3;
        path[3][5] = -1;
        path[4][0] = -1;
        path[4][1] = -1;
        path[4][2] = -1;
        path[4][3] = 4;
        path[4][4] = -1;
        path[4][5] = -1;
        path[5][0] = -1;
        path[5][1] = -1;
        path[5][2] = -1;
        path[5][3] = -1;
        path[5][4] = 5;
        path[5][5] = -1;
    }
}
