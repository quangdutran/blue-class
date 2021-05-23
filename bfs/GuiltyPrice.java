package com.company.bfs;

import java.util.*;
import java.util.Arrays;

public class GuiltyPrice {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        List<String[][]> mazes = new ArrayList<>();
        List<boolean[][]> visiteds = new ArrayList<>();
        List<RowCol> startingPoint = new ArrayList<>();
        for (int i = 0; i < numberOfCases; i++) {
            int col = input.nextInt();
            int row = input.nextInt();
            String[][] maze = new String[row][col];
            input.nextLine();

            for (int j = 0; j < row; j++) {
                String line = input.nextLine();
                for (int k = 0; k < col; k++) {
                    maze[j][k] = String.valueOf(line.charAt(k));
                    if (maze[j][k].equals("@")) {
                        startingPoint.add(new RowCol(j,k));
                    }
                }
            }
            mazes.add(maze);
        }
        input.close();

        for (int i = 0; i < numberOfCases; i++) {
            System.out.println("Case " + (i+1) + ": " + processMaze(mazes.get(i), startingPoint.get(i)));
        }
    }

    private static int processMaze(String[][] maze, RowCol startingPoint) {
        boolean [][] visited = new boolean[maze.length][maze[0].length];
        Queue<RowCol> queue = new LinkedList<>();
        int totalSteps = 1;
        queue.add(startingPoint);

        while(!queue.isEmpty()) {
            RowCol currentPosition = queue.remove();
            List<RowCol> unvisitedNeighbor = getUnvisitedNeighbor(maze, visited, currentPosition);
            totalSteps += unvisitedNeighbor.size();
            queue.addAll(unvisitedNeighbor);
        }
        return totalSteps;
    }

    private static List<RowCol> getUnvisitedNeighbor(String[][] maze, boolean[][] visited, RowCol start) {
        List<RowCol> result = new ArrayList<>();
        try {
            //lower neighbor
            if (maze[start.row - 1][start.col].equals(".") && !visited[start.row - 1][start.col]) {
                result.add(new RowCol(start.row -1, start.col));
                visited[start.row - 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //up neighbor
            if (maze[start.row + 1][start.col].equals(".") && !visited[start.row + 1][start.col]) {
                result.add(new RowCol(start.row + 1, start.col));
                visited[start.row + 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //right
            if (maze[start.row][start.col + 1].equals(".") && !visited[start.row][start.col + 1]) {
                result.add(new RowCol(start.row, start.col + 1));
                visited[start.row][start.col + 1] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //left
            if (maze[start.row][start.col - 1].equals(".") && !visited[start.row][start.col - 1]) {
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
