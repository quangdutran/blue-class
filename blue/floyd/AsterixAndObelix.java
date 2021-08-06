package com.company.blue.floyd;

import java.util.Scanner;

public class AsterixAndObelix {
    static int c;
    static int r;
    static int q;
    static int[] feast;
    static int[][] feastCost;
    static long[][] matrix;
    static final int INF = (int)1e9 + 7;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (true) {
            c = input.nextInt();
            r = input.nextInt();
            q = input.nextInt();
            if (c == 0 && r == 0 && q == 0) {
                break;
            }
            feast = new int[c + 1];

            feastCost = new int[c + 1][c + 1];
            for (int i = 1; i < c + 1; i++) {
                feast[i] = input.nextInt();
            }

            matrix = new long[c + 1][c + 1];
            for (int i = 1; i < c + 1; i++) {
                for (int j = 1; j < c + 1; j++) {
                    matrix[i][j] = INF;
                    feastCost[i][j] = Math.max(feast[i], feast[j]);
                }
            }

            for (int i = 0; i < r; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                matrix[first][second] = third;
                matrix[second][first] = third;
            }
            FloydWarshall();
            FloydWarshall();

            System.out.println("Case #" + (++count) + "");
            for (int i = 0; i < q; i++) {
                int start = input.nextInt();
                int stop = input.nextInt();
                System.out.println(matrix[start][stop] == INF ? -1 : matrix[start][stop] + feastCost[start][stop]);
            }


        }
    }

    public static void FloydWarshall() {
        int times = 1;

        while (times-- > 0) {
            for (int k = 1; k <= c; k++) {
                for (int i = 1; i <= c; i++) {
                    for (int j = 1; j <= c; j++) {
                        int maxFeast = Math.max(feastCost[i][k], feastCost[k][j]);
                        if (matrix[i][j] + feastCost[i][j] > matrix[i][k] + matrix[k][j] + maxFeast) {
                            matrix[i][j] = matrix[i][k] + matrix[k][j];
                            feastCost[i][j] = maxFeast;
                        }
                    }
                }
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
                    int maxCost = Math.max(feastCost[i][k], feastCost[k][j]);
                    if (matrix[k][j] != Integer.MAX_VALUE &&
                            matrix[i][j] + feastCost[i][j] > matrix[i][k] + matrix[k][j] + maxCost) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        feastCost[i][j] = maxCost;
                    }
                }
            }
        }
    }

}
