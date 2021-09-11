package com.company.alg.orange.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * SPOJ: Backtracking will face time limit (this is forward, point to the row below)
 * Idea: go through each row, get the max stones for each tile but backwards, point to the row above
 */
public class PhilosophersStone {

    static int[][] maze;
    static int rows;
    static int columns;
    static boolean [][] visited;
    static int MAX = 0;
    static List<RowCol> movable = Arrays.asList(new RowCol(1,-1), new RowCol(1,0), new RowCol(1,1));
    static List<RowCol> movableUpward = Arrays.asList(new RowCol(-1,-1), new RowCol(-1,0), new RowCol(-1,1));



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            rows = input.nextInt();
            columns = input.nextInt();
            maze = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    maze[i][j] = input.nextInt();
                }
            }

            int [][] dp = new int[rows][columns];
            for (int i = 0; i < columns; i++) {
                dp[0][i] = maze[0][i];
            }

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int max = 0;
                    for (RowCol upNeighbor : getUpwardNeighbor(new RowCol(i,j))) {
                        max = Math.max(max, maze[i][j] + dp[upNeighbor.x][upNeighbor.y]);
                    }
                    dp[i][j] = max;
                }
            }

            MAX = 0;
            for (int i = 0; i < columns; i++) {
                MAX = Math.max(MAX, dp[rows-1][i]);
            }
            System.out.println(MAX);
        }
    }

    private static List<RowCol> getUpwardNeighbor(RowCol tile) {
        List<RowCol> result = new ArrayList<>();
        for (RowCol rowCol : movableUpward) {
            RowCol next = new RowCol(tile.x + rowCol.x, tile.y + rowCol.y);
            if (insideBoard(next)) {
                result.add(next);
            }
        }
        return result;
    }


    public static void main_backTracking(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            rows = input.nextInt();
            columns = input.nextInt();

            maze = new int[rows][columns];
            visited = new boolean[rows][columns];
            MAX = 0;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    maze[i][j] = input.nextInt();
                }
            }

            for (int i = 0; i < columns; i++) {
                move(new RowCol(0,i), maze[0][i]);
            }
            System.out.println(MAX);
        }
    }

    private static void move(RowCol tile, int stone) {
        visited[tile.x][tile.y] = true;
        for (RowCol neighbor : getUnvisitedNeighbor(tile)) {
            stone += maze[neighbor.x][neighbor.y];
            move(neighbor, stone);
            stone -= maze[neighbor.x][neighbor.y];
        }
        MAX = Math.max(MAX, stone);
        visited[tile.x][tile.y] = false;
    }

    private static List<RowCol> getUnvisitedNeighbor(RowCol tile) {
        List<RowCol> result = new ArrayList<>();
        for (RowCol rowCol : movable) {
            RowCol next = new RowCol(tile.x + rowCol.x, tile.y + rowCol.y);
            if (insideBoard(next) && !visited[next.x][next.y]) {
                result.add(next);
            }
        }
        return result;
    }

    private static boolean insideBoard(RowCol tile) {
        return tile.x >= 0 && tile.x < rows && tile.y >= 0 && tile.y < columns;
    }

    static class RowCol {
        int x;
        int y;

        public RowCol(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
