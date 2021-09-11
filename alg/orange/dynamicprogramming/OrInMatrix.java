package com.company.alg.orange.dynamicprogramming;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Codeforces
 */
public class OrInMatrix {
    static int row, columns;
    static int binaryMatrix[][];
    static boolean validLine[][];
    static List<RowCol> points = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        row = input.nextInt();
        columns = input.nextInt();
        binaryMatrix = new int [row][columns];
        validLine = new boolean[row][columns];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                binaryMatrix[i][j] = input.nextInt();
            }
        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                if (binaryMatrix[i][j] == 1 && !checkValid(new RowCol(i,j))) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        int [][] resultMatrix = new int[row][columns];
        for (RowCol point : points) {
            resultMatrix[point.x][point.y] = 1;
        }
        System.out.println("YES");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkValid(RowCol cell) {
        //Check row contain all 1
        boolean rowContainAll1 = true;
        for(int i = 0; i < columns; i++) {
            if (binaryMatrix[cell.x][i] != 1) {
                rowContainAll1 = false;
                break;
            }
        }
        if (rowContainAll1) {
            for (int i = 0; i < columns; i++) {
                validLine[cell.x][i] = true;
            }
        }

        //Check column contain all 1
        boolean columnContainAll1 = true;
        for (int i = 0; i < row; i++) {
            if (binaryMatrix[i][cell.y] != 1) {
                columnContainAll1 = false;
                break;
            }
        }
        if (columnContainAll1) {
            for (int i = 0; i < row; i++) {
                validLine[i][cell.y] = true;
            }
        }

        if (rowContainAll1 && columnContainAll1) {
            points.add(cell);
        }
        return rowContainAll1 | columnContainAll1;
    }

    static class RowCol {
        int x;
        int y;

        public RowCol(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
