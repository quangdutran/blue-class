package com.company.blue.bfs;

import java.util.*;
import java.util.Arrays;

public class Slick {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<int[][]> mazes = new ArrayList<>();
        while (true) {
            int row = input.nextInt();
            int col = input.nextInt();
            if (row == 0 && col == 0) {
                break;
            }

            Queue<RowCol> hasSlick = new LinkedList<>();
            int[][] maze = new int[row][col];
            for (int i = 0 ; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    maze[i][j] = input.nextInt();
                    if (maze[i][j] == 1) {
                        hasSlick.add(new RowCol(i,j));
                    }
                }
            }
            processMaze(maze,hasSlick);
        }
        input.close();


    }


    private static void processMaze(int[][] maze, Queue<RowCol> hasSlick) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        List<Integer> slickSize = new ArrayList<>();

        while (!hasSlick.isEmpty()) {
            RowCol coordinate = hasSlick.remove();
            if (!visited[coordinate.row][coordinate.col]) {
                Queue<RowCol> eachSlick = new LinkedList<>();
                eachSlick.add(coordinate);
                visited[coordinate.row][coordinate.col] = true;
                int totalSize = 1;
                while (!eachSlick.isEmpty()) {
                    RowCol current = eachSlick.remove();
                    List<RowCol> unvisited = getUnvisitedNeighbor(maze, visited, current);
                    totalSize += unvisited.size();
                    eachSlick.addAll(unvisited);
                }
                slickSize.add(totalSize);
            }
        }
        Collections.sort(slickSize);
        List<Integer> distinct = new ArrayList<>();
        for (Integer slick : slickSize) {
            if (!distinct.contains(slick)) {
                distinct.add(slick);
            }
        }
        System.out.println(slickSize.size());
        for (Integer d : distinct) {
            System.out.println(d + " " + Collections.frequency(slickSize, d));
        }
    }

    private static List<RowCol> getUnvisitedNeighbor(int[][] maze, boolean[][] visited, RowCol start) {
        List<RowCol> result = new ArrayList<>();
        try {
            //lower neighbor
            if (maze[start.row - 1][start.col] == 1 && !visited[start.row - 1][start.col]) {
                result.add(new RowCol(start.row -1, start.col));
                visited[start.row - 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //up neighbor
            if (maze[start.row + 1][start.col] == 1 && !visited[start.row + 1][start.col]) {
                result.add(new RowCol(start.row + 1, start.col));
                visited[start.row + 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //right
            if (maze[start.row][start.col + 1] == 1 && !visited[start.row][start.col + 1]) {
                result.add(new RowCol(start.row, start.col + 1));
                visited[start.row][start.col + 1] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //left
            if (maze[start.row][start.col - 1] == 1 && !visited[start.row][start.col - 1]) {
                result.add(new RowCol(start.row, start.col - 1));
                visited[start.row][start.col - 1] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }
        return result;
    }

    static class RowCol {
        private int row;
        private int col;

        public RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            RowCol o = (RowCol) obj;
            return o.col == this.col && o.row == this.row;
        }

        @Override
        public int hashCode() {
            int hash = 13;
            return hash * this.row + hash * this.col;
        }
    }
}
