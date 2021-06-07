package com.company.floyd;

import java.util.Scanner;

public class AsterixAndObelix {
    static int c;
    static int r;
    static int q;
    static int[] feastCost;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while(true) {
            c = input.nextInt();
            r = input.nextInt();
            q = input.nextInt();
            feastCost = new int [c + 1];

            for (int i = 1; i < c + 1; i++) {
                feastCost[i] = input.nextInt();
            }

            matrix = new int [c + 1][c + 1];
            for (int i = 1; i < c + 1; i++) {
                for (int j = 1; j < c + 1; j++) {
                    if (i == j) {
                        matrix[i][j] = 0;
                    } else {
                        matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            for (int i = 0; i < r; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                matrix[first][second] = third;
                matrix[second][first] = third;
            }
            floyd();

            System.out.println("Case #" + (++count) + "");
            for (int i = 0; i < q; i++) {
                int start = input.nextInt();
                int stop = input.nextInt();
                System.out.println(matrix[start][stop]);
            }


        }
    }

    private static void floyd() {
        for (int k = 1; k < c + 1; k++) {
            for (int i = 1; i < c + 1; i++) {
                if (matrix[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 1; j < c + 1; j++) {
                    if (matrix[k][j] != Integer.MAX_VALUE &&
                            matrix[i][j] > matrix[i][k] + matrix[k][j] + max(feastCost[i], feastCost[k], feastCost[j])) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j] + max(feastCost[i], feastCost[k], feastCost[j]);
                    }
                }
            }
        }
    }

    private static int max(int a, int b, int c) {
        return Math.max(Math.max(a,b), c);
    }
}
