package com.company.blue.floyd;

import java.util.Scanner;

public class PossibleFriendByFloyd {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfTestCases = input.nextInt();
        input.nextLine();
        while (numberOfTestCases-- > 0) {
            String line = input.nextLine();
            char[][] matrix = new char[line.length()][line.length()];
            for (int j = 0; j < line.length(); j++) {
                matrix[0][j] = line.charAt(j);
            }
            for (int j = 1; j < line.length(); j++) {
                String nextLine = input.nextLine();
                for (int k = 0; k < nextLine.length(); k++) {
                    matrix[j][k] = nextLine.charAt(k);
                }
            }
            floyd(matrix);

        }
    }

    private static void floyd(char[][] matrix) {
        int [][] distance = new int[matrix.length][matrix[0].length];

        //initialize the distance
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'Y') {
                    distance[i][j] = 1;
                } else if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                if (distance[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < matrix[0].length; j++) {
                    if (distance[k][j] != Integer.MAX_VALUE && distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        printResult(distance);
    }

    private static void printResult(int [][] distance) {
        int ans = 0;
        int index = 0;
        for (int i = 0; i < distance.length; i++) {
            int countPossibleFriend = 0;
            for (int j = 0; j < distance[0].length; j++) {
                if (distance[i][j] == 2) {
                    countPossibleFriend++;
                }
            }
            if (countPossibleFriend > ans) {
                ans = countPossibleFriend;
                index = i;
            }
        }
        System.out.println(index + " " + ans);
    }

}
