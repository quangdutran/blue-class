package com.company.alg.orange.backtracking;

public class NQueen {
    private static int N = 4;
    private static int[][] board = new int[N][N];

    private static void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf(" %d ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean check(int[][] board, int row, int col) {
        for(int i = 0; i < row; i++) {
            if (board[i][col] != 0) {
                return false;
            }
        }
        for (int i = row, j = col; i>=0 && j>= 0; i--, j--) {
            if (board[i][j] != 0) {
                return false;
            }
        }
        for (int i = row, j = col; j < N && i>=0; i--,j++) {
            if(board[i][j] != 0){
                return false;
            }
        }
        return true;
    }

    private static boolean NQueen(int[][] board, int row) {
        if (row == N) {
            printSolution();
            return true;
        }
        for (int j = 0; j < N; j++) {
            if (check(board, row, j)) {
                board[row][j] = 1;
                NQueen(board, row+1);
                board[row][j] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NQueen(board, 0);
    }
}
