package com.company.blue.bfs;

import java.util.*;

public class IceCave {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int r = input.nextInt();
        int c = input.nextInt();
        char [][] maze = new char[r][c];
        input.nextLine();
        for (int i = 0; i < r; i++) {
            String line = input.nextLine();
            for (int j = 0; j < c; j++) {
                maze[i][j] = line.charAt(j);
            }
        }


        int r1 = input.nextInt() - 1;
        int c1 = input.nextInt() - 1;
        int r2 = input.nextInt() - 1;
        int c2 = input.nextInt() - 1;
        input.close();
        processMaze(maze, new RowCol(r1,c1), new RowCol(r2,c2));

    }
    
    static void processMaze(char[][] maze, RowCol start, RowCol end) {
        boolean [][] visited = new boolean[maze.length][maze[0].length];
        RowCol[][] path = new RowCol[maze.length][maze[0].length];
        Queue<RowCol> queue = new LinkedList<>();
        queue.add(start);

        boolean hasPath = false;

        while(!queue.isEmpty()) {
            RowCol currentPosition = queue.remove();
            if (currentPosition.row == end.row && currentPosition.col == end.col
                    && maze[end.row][end.col] == 'X') {
                System.out.println("YES");
                return;
            } else if (currentPosition.row == end.row && currentPosition.col == end.col) {
                hasPath = true;
            }
            List<RowCol> unvisitedNeighbor = getUnvisitedNeighbor(maze, visited, currentPosition, end);
            queue.addAll(unvisitedNeighbor);
            for (RowCol rowCol : unvisitedNeighbor) {
                path[rowCol.row][rowCol.col] = currentPosition;
            }
        }

        System.out.println(hasPath ? checkPath(path, start, end, maze) ? "YES" : "NO" : "NO");
    }

    //Finish cell becomes thin ice, now need to find solid ice neighbor cell to move there
    //and move back to finish
    private static boolean checkPath(RowCol [][] path, RowCol start, RowCol end, char [][] maze) {
        RowCol finish = new RowCol(end.row, end.col);
        while (true) {
            end = path[end.row][end.col];
            maze[end.row][end.col] = 'X';
            if (start.row == end.row && start.col == end.col) {
                break;
            }
        }

        //check if there is any solid neighbor

        //up
        if (finish.row + 1 <= maze.length - 1 &&
                finish.row + 1 >= 0 && maze[finish.row + 1][finish.col] == '.') {
            return true;
        }
        //lower
        if (finish.row - 1 <= maze.length - 1
                && finish.row - 1 >= 0 && maze[finish.row - 1][finish.col] == '.') {
            return true;
        }
        //left
        if (finish.col - 1 <= maze[0].length
                && finish.col - 1 >= 0 && maze[finish.row][finish.col - 1] == '.') {
            return true;
        }
        //right
        if (finish.col + 1 <= maze[0].length
                && finish.col + 1 >= 0 && maze[finish.row][finish.col + 1] == '.') {
            return true;
        }
        return false;
    }

    private static List<RowCol> getUnvisitedNeighbor(char[][] maze, boolean[][] visited, RowCol start, RowCol end) {
        List<RowCol> result = new ArrayList<>();
        try {
            //lower neighbor
            if ((maze[start.row - 1][start.col] == '.' || (start.row - 1 == end.row && start.col == end.col)) 
                    && !visited[start.row - 1][start.col]) {
                result.add(new RowCol(start.row -1, start.col));
                visited[start.row - 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //up neighbor
            if ((maze[start.row + 1][start.col] == '.' || (start.row + 1 == end.row && start.col == end.col))
                    && !visited[start.row + 1][start.col]) {
                result.add(new RowCol(start.row + 1, start.col));
                visited[start.row + 1][start.col] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //right
            if ((maze[start.row][start.col + 1] == '.' || (start.row == end.row && start.col + 1 == end.col))
                    && !visited[start.row][start.col + 1]) {
                result.add(new RowCol(start.row, start.col + 1));
                visited[start.row][start.col + 1] = true;
            }
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            //left
            if ((maze[start.row][start.col - 1] == '.' || (start.row== end.row && start.col - 1 == end.col))
                    && !visited[start.row][start.col - 1]) {
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
    }
}
