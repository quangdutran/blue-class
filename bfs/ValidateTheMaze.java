package com.company.bfs;

import java.util.Arrays;
import java.util.*;

public class ValidateTheMaze {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        List<String[][]> mazes = new ArrayList<>();
        for (int i = 0; i < numberOfCases; i++) {
            int row = input.nextInt();
            int col = input.nextInt();
            String[][] maze = new String[row][col];
            input.nextLine();

            for (int j = 0; j < row; j++) {
                String line = input.nextLine();
                for (int k = 0; k < col; k++) {
                    maze[j][k] = String.valueOf(line.charAt(k));
                }
            }

            mazes.add(maze);
        }

        input.close();

        for (String[][] maze : mazes) {
            validateMaze(maze);
        }


    }

    private static void validateMaze(String[][] maze) {
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];

        //There are exactly 2 openin
        HashSet<RowCol> set = new HashSet<>();

        //First row
        for (int i = 0; i < col; i++) {
            if (maze[0][i].equals(".")) {
                set.add(new RowCol(0, i));
            }
        }
        //last row
        for (int i = 0; i < col; i++) {
            if (maze[row - 1][i].equals(".")) {
                set.add(new RowCol(row - 1, i));
            }
        }
        //first column
        for (int i = 0; i < row; i++) {
            if (maze[i][0].equals(".")) {
                set.add(new RowCol(i, 0));
            }
        }
        //last column
        for (int i = 0; i < row; i++) {
            if (maze[i][col-1].equals(".")) {
                set.add(new RowCol(i, col - 1));
            }
        }


        if (set.size() != 2) {
            System.out.println("invalid");
            return;
        }

        List<RowCol> list = new ArrayList<>(set);

        Queue<List<Integer>> queue = new LinkedList<>(getUnvisitedNeighbor(maze, visited, list.get(0).row, list.get(0).col));

        while (!queue.isEmpty()) {
            List<Integer> neighbor = queue.remove();
            if (neighbor.get(0) == list.get(1).row && neighbor.get(1) == list.get(1).col) {
                System.out.println("valid");
                return;
            }
            queue.addAll(getUnvisitedNeighbor(maze, visited, neighbor.get(0), neighbor.get(1)));
        }

        System.out.println("invalid");
    }

    private static List<List<Integer>> getUnvisitedNeighbor(String[][] maze, boolean[][] visited, int startRow, int startCol) {
        List<List<Integer>> result = new ArrayList<>();
        try {
            //lower neighbor
            if (maze[startRow - 1][startCol].equals(".") && !visited[startRow - 1][startCol]) {
                result.add(Arrays.asList(startRow - 1, startCol));
                visited[startRow - 1][startCol] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //up neighbor
            if (maze[startRow + 1][startCol].equals(".") && !visited[startRow + 1][startCol]) {
                result.add(Arrays.asList(startRow + 1, startCol));
                visited[startRow + 1][startCol] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //right
            if (maze[startRow][startCol + 1].equals(".") && !visited[startRow][startCol + 1]) {
                result.add(Arrays.asList(startRow, startCol + 1));
                visited[startRow][startCol + 1] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //left
            if (maze[startRow][startCol - 1].equals(".") && !visited[startRow][startCol - 1]) {
                result.add(Arrays.asList(startRow, startCol - 1));
                visited[startRow][startCol - 1] = true;
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
