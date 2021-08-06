package com.company.blue.dfs;

import java.util.*;

public class LakesInBerlands {
    static char[][] matrix;
    static int row;
    static int col;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        row = input.nextInt();
        col = input.nextInt();
        int k = input.nextInt();
        input.nextLine();
        matrix = new char[row][col];
        visited = new boolean[row][col];
        List<RowCol> starts = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            String line = input.nextLine();
            for (int j = 0; j < col; j++) {
                matrix[i][j] = line.charAt(j);
                if (i > 0 && i < row - 1 && j > 0 && j < col - 1 && matrix[i][j] == '.') {
                    starts.add(new RowCol(i, j));
                }
            }
        }

        List<Lake> lakes = new ArrayList<>();
        for (RowCol start : starts) {
            if (!visited[start.row][start.col]) {
                int size = dfs(start);
                if (size > 0) {
                    lakes.add(new Lake(size, start));
                }
            }
        }

        Collections.sort(lakes);
        int totalBuried = 0;
        if (lakes.size() > k) {
            for (int i = 0; i < lakes.size() - k; i++) {
                Lake buried = lakes.get(i);
                totalBuried += buried.size;
                buryWater(buried.start);
            }
        }
        System.out.println(totalBuried);
        printMatrix();
    }

    private static void buryWater(RowCol start) {
        Stack<RowCol> stack = new Stack<>();
        stack.add(start);
        matrix[start.row][start.col] = '*';

        while (!stack.isEmpty()) {
            RowCol top = stack.pop();
            for (RowCol neighbor : getWaterNeighbor(top)) {
                matrix[neighbor.row][neighbor.col] = '*';
                stack.add(neighbor);
            }
        }
    }

    private static void printMatrix() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static int dfs(RowCol start) {
        Stack<RowCol> stack = new Stack<>();

        stack.add(start);
        visited[start.row][start.col] = true;
        int size = 1;
        boolean hasEdgeWater = false;

        while (!stack.isEmpty()) {
            RowCol top = stack.pop();
            for (RowCol neighbor : getWaterNeighbor(top)) {
                if (!visited[neighbor.row][neighbor.col]) {
                    stack.add(neighbor);
                    visited[neighbor.row][neighbor.col] = true;
                    if (isOnEdge(neighbor)) {
                        hasEdgeWater = true;
                    }
                    size++;
                }
            }
        }

        return hasEdgeWater ? 0 : size;
    }

    private static boolean isOnEdge(RowCol point) {
        return point.row == 0 || point.row == row - 1 || point.col == 0 || point.col == col - 1;
    }

    private static List<RowCol> getWaterNeighbor(RowCol point) {
        List<RowCol> neighbors = new ArrayList<>();
        //up
        if (checkValidIndex(point.row + 1, point.col) && checkIsWater(point.row + 1, point.col)) {
            neighbors.add(new RowCol(point.row + 1, point.col));
        }
        //down
        if (checkValidIndex(point.row - 1, point.col) && checkIsWater(point.row - 1, point.col)) {
            neighbors.add(new RowCol(point.row - 1, point.col));
        }
        //right
        if (checkValidIndex(point.row, point.col + 1) && checkIsWater(point.row, point.col + 1)) {
            neighbors.add(new RowCol(point.row, point.col + 1));
        }
        //left
        if (checkValidIndex(point.row, point.col - 1) && checkIsWater(point.row, point.col - 1)) {
            neighbors.add(new RowCol(point.row, point.col - 1));
        }

        return neighbors;
    }

    private static boolean checkValidIndex(int row, int col) {
        if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length - 1) {
            return false;
        } else return true;
    }

    private static boolean checkIsWater(int row, int col) {
        return matrix[row][col] == '.';
    }

    static class RowCol {
        private int row;
        private int col;

        public RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Lake implements Comparable<Lake> {
        private int size;
        private RowCol start;

        public Lake(int size, RowCol start) {
            this.size = size;
            this.start = start;
        }

        @Override
        public int compareTo(Lake o) {
            return size - o.size;
        }
    }
}
