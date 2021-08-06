package com.company.blue.midterm;

import java.util.*;

public class BombMine {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        while (true) {
            int row = input.nextInt();
            int col = input.nextInt();
            if (row == 0 && col == 0) {
                break;
            }
            List<RowCol> bombs = new ArrayList<>();
            int rowsHasBomb = input.nextInt();
            for (int i = 0; i < rowsHasBomb; i++) {
                int rowNum = input.nextInt();
                int bombNum = input.nextInt();
                for (int j = 0; j < bombNum; j++) {
                    bombs.add(new RowCol(rowNum, input.nextInt()));
                }
            }
            RowCol start = new RowCol(input.nextInt(), input.nextInt());
            RowCol end = new RowCol(input.nextInt(), input.nextInt());
            System.out.println(processMaze(row, col, bombs, start, end));
        }
    }

    private static int [][] createMaze(int row, int col, List<RowCol> bombs) {
        int [][] maze = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = 1;
            }
        }
        for (RowCol bomb : bombs) {
            maze[bomb.row][bomb.col] = 0;
        }
        return maze;
    }

    private static int [][] createDist(int row, int col) {
        int [][] dist = new int [row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        return dist;
    }

    private static int processMaze(int row, int col, List<RowCol> bombs, RowCol start, RowCol end) {
        int [][] maze = createMaze(row, col, bombs);
        boolean [][] visited = new boolean[row][col];
        int distance [][] = createDist(row, col);

        LinkedList<RowCol> queue = new LinkedList<>();
        visited[start.row][start.col] = true;
        distance[start.row][start.col]  = 0;

        queue.add(start);

        while (!queue.isEmpty()) {
            RowCol top = queue.remove();
            for (RowCol neighbor : getUnvisitedNeighbor(maze, visited, top)) {
                queue.add(neighbor);
                distance[neighbor.row][neighbor.col] = distance[top.row][top.col] + 1;
                if (neighbor.equals(end)) {
                    return distance[neighbor.row][neighbor.col];
                }
            }
        }

        return -1 ;
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
//    public static class Node implements Comparable<Node> {
//        private RowCol id;
//        private Integer dist;
//
//        public Node(RowCol id, int dist) {
//            this.id = id;
//            this.dist = dist;
//        }
//
//        @Override
//        public int compareTo(Node o) {
//            return this.dist.compareTo(o.dist);
//        }
//    }

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
