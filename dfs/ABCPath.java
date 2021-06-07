package com.company.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ABCPath {
    static private int A_VALUE = 65;
    static private int Z_VALUE = 90;
    static private char [][] matrix;
    static private boolean visited [][];
    static private int maxRoute = 0;
    static int dist [][];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (true) {
            int row = input.nextInt();
            int col = input.nextInt();

            if (row == 0 && col == 0) {
                break;
            }
            input.nextLine();

            matrix = new char [row][col];
            visited = new boolean [row][col];
            dist = new int [row][col];
            maxRoute = 0;
            for (int i = 0; i < row; i++) {
                String line = input.nextLine();
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = line.charAt(j);
                }
            }
            int ans = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 'A') {
                        ans = Math.max(ans, dfs(new RowCol(i,j)));
                    }
                }
            }

            System.out.println("Case " + ++count + ": " + ans);
        }
        input.close();
    }

    public static int dfs(RowCol start) {
        int s = 0;
        for (RowCol neighbor : getNeighbor(start)) {
            if (matrix[neighbor.row][neighbor.col] - matrix[start.row][start.col] == 1) {
                if (!visited[neighbor.row][neighbor.col]) {
                    dfs(neighbor);
                }
                s = Math.max(s, dist[neighbor.row][neighbor.col]);
            }
        }

        dist[start.row][start.col] = s + 1;
        //visited[start.row][start.col] = true;
        return dist[start.row][start.col];
    }

    private static List<RowCol> getNeighbor(RowCol point) {
        List<RowCol> neighbors = new ArrayList<>();
        //up
        if (checkValidIndex(point.row + 1, point.col)) {
            neighbors.add(new RowCol(point.row + 1, point.col));
        }
        //down
        if (checkValidIndex(point.row - 1, point.col)) {
            neighbors.add(new RowCol(point.row - 1, point.col));
        }
        //right
        if (checkValidIndex(point.row, point.col + 1)) {
            neighbors.add(new RowCol(point.row , point.col + 1));
        }
        //left
        if (checkValidIndex(point.row, point.col -1)) {
            neighbors.add(new RowCol(point.row, point.col - 1));
        }
        //up left corner
        if (checkValidIndex(point.row - 1, point.col - 1)) {
            neighbors.add(new RowCol(point.row - 1, point.col -1));
        }
        //up right corner
        if (checkValidIndex(point.row - 1, point.col + 1)) {
            neighbors.add(new RowCol(point.row - 1, point.col + 1));
        }
        //down right corner
        if (checkValidIndex(point.row + 1, point.col + 1)) {
            neighbors.add(new RowCol(point.row + 1, point.col + 1));
        }
        //down left corner
        if (checkValidIndex(point.row + 1, point.col - 1)) {
            neighbors.add(new RowCol(point.row + 1, point.col - 1));
        }
        return neighbors;
    }

    private static boolean checkValidIndex(int row, int col) {
        if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length - 1) {
            return false;
        } else return true;
    }

    static class RowCol {
        private int row;
        private int col;

        public RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
