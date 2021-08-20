package com.company.alg.orange.greedy;

import java.math.BigDecimal;
import java.util.*;

public class MakingJumps {
    static int[][] board = new int[10][10];
    static boolean[][] visited = new boolean[10][10];
    static List<Point> moves = Arrays.asList(new Point(-2,-1), new Point(-2,1), new Point(-1, -2), new Point(-1,2), new Point(1,-2),
            new Point(1, 2), new Point(2,-1), new Point(2,1));

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int caseCount = 0;
        while (input.hasNextLine()) {
            int total = input.nextInt();
            if (total == 0) {
                break;
            }
            fillMaze();
            visited = new boolean[10][10];
            Point [] pairs = new Point[total];
            int cell = 0;

            int start_col = 0;
            for (int i = 0; i < total; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                if (i == 0)
                    start_col = first;
                cell += second;
                pairs[i] = new Point(first, second);
            }

            buildMatrix(Arrays.asList(pairs));
            int count = dfs(new Point(0,start_col));
            int result = cell - count;
            String append = result == 1 ? "" : "s";
            System.out.println("Case "+ ++caseCount +", "+result+" square"+ append  +" can not be reached.");
        }
    }

    private static void fillMaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = 1;
            }
        }
    }

    private static int dfs(Point start) {
        visited[start.x][start.y] = true;
        int count = 0;
        for (Point neighbor : getValidNeighbor(start)) {
            count = Math.max(count, dfs(neighbor));
        }
        visited[start.x][start.y] = false;
        return count+1;
    }

    private static List<Point> getValidNeighbor(Point current) {
        List<Point> result = new ArrayList<>();
        for (Point move : moves) {
           Point p = new Point(current.x + move.x, current.y + move.y);
           if (isValidCell(p)) {
               result.add(p);
           }
        }
        return result;
    }

    private static boolean isValidCell(Point cell) {
        return cell.x >= 0 && cell.x < 10 && cell.y >= 0 && cell.y < 10
                && board[cell.x][cell.y] == 1 && !visited[cell.x][cell.y];
    }

    private static void buildMatrix(List<Point> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
             Point p = pairs.get(i);
             int r = p.x;
             int c = p.y;

             for (int a = 0; a < r; a++) {
                 board[i][a] = 0;
                 visited[i][a] = true;
             }

             for (int a = r+c; a < 10; a++) {
                 board[i][a] = 0;
                visited[i][a] = true;
             }

             if (pairs.size() < 10) {
                 for (int row = pairs.size(); row < 10; row++) {
                     for (int j = 0; j < 10; j ++) {
                         board[row][j] = 0;
                     }
                 }
             }
        }
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
