package com.company.alg.orange.midterm;

import java.util.Scanner;

public class TheSultantSuccessor {
    private static int N = 8;
    private static int[][] board = new int[N][N];
    private static int[][] numberBoard = new int[N][N];
    private static int MAX = 0;

    private static int getSum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    sum += numberBoard[i][j];
                }
            }
        }
        return sum;
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
            MAX = Math.max(MAX, getSum());
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
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        for (int i = 0; i < k; i++) {
            board = new int[N][N];
            numberBoard = new int[N][N];
            MAX = 0;

            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {
                    numberBoard[a][b] = input.nextInt();
                }
            }
            NQueen(board, 0);
            System.out.println("     " + MAX);
        }

    }
}
