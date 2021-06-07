package com.company.floyd;

import java.util.HashMap;
import java.util.Scanner;

public class Arbitrage {
    static HashMap<String, Integer> currencyMap;
    static int vertices;
    static int edges;
    static double [][] matrix;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (input.hasNextLine()) {
            currencyMap = new HashMap<>();
            vertices = input.nextInt();
            if (vertices == 0) {
                break;
            }
            input.nextLine();
            for (int i = 0; i < vertices; i++) {
                currencyMap.put(input.nextLine(), i);
            }
            edges = input.nextInt();
            matrix = new double [vertices][vertices];
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (i == j) {
                        matrix[i][j] = 1;
                    } else {
                        matrix[i][j] = 0;
                    }
                }
            }

            input.nextLine();
            for (int i = 0; i < edges; i++) {
                String line = input.nextLine();
                String [] split = line.split(" ");
                int indexFrom = currencyMap.get(split[0]);
                int indexTo = currencyMap.get(split[2]);
                matrix[indexFrom][indexTo] = Double.parseDouble(split[1]);
            }
            input.nextLine();

            floyd();
            System.out.println("Case " + ++count + ": " + checkHasArbitrage());
        }
    }

    private static void floyd() {
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                if (matrix[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < vertices; j++) {
                    if (matrix[k][j] != 0) {
                        matrix[i][j] = Math.max(matrix[i][k] * matrix[k][j], matrix[i][j]);
                    }
                }
            }
        }
    }

    private static String checkHasArbitrage() {
        for (int i = 0; i < vertices; i++) {
            if (matrix[i][i] > 1) {
                return "Yes";
            }
        }
        return "No";
    }
}
