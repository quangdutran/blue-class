package com.company.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AllIsWell {
    static char [][] matrix;
    static String goal = "ALLIZZWELL";
    static boolean [][] visited;
    static boolean found;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfTestCase = input.nextInt();

        while (numberOfTestCase-- > 0) {
            int row = input.nextInt();
            int col = input.nextInt();
            input.nextLine();
            matrix = new char[row][col];
            visited = new boolean[matrix.length][matrix[0].length];
            found = false;

            for (int i = 0; i < row; i++) {
                String line = input.nextLine();
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 'A' && !found) {
                        dfs(new RowCol(i,j), 1);
                    }
                }
            }

            System.out.println(found ? "YES" : "NO");
        }
    }

    private static void dfs(RowCol point, int countPath) {
        if (countPath == goal.length()) {
            found = true;
            return;
        }

        for (RowCol neighbor : getNeighbor(point)) {
            if (!visited[neighbor.row][neighbor.col] && matrix[neighbor.row][neighbor.col] == goal.charAt(countPath)) {
                visited[neighbor.row][neighbor.col] = true;
                dfs(neighbor, countPath + 1);
                visited[neighbor.row][neighbor.col] = false;
            }
        }
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
