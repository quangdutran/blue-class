package com.company.blue.floyd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeetingProfMiguel {
    static int[][] matrixY;
    static int[][] matrixM;
    static String miguel;
    static String professor;

    public static void main(String[] args) {
        mainLogic();
    }

    private static void mainLogic() {
        Scanner input = new Scanner(System.in);
        while (true) {
            matrixY = new int[26][26];
            matrixM = new int[26][26];
            int n = input.nextInt();
            if (n == 0) {
                break;
            }
            input.nextLine();
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (i == j) {
                        matrixM[i][j] = 0;
                        matrixY[i][j] = 0;
                    } else {
                        matrixM[i][j] = Integer.MAX_VALUE;
                        matrixY[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                String line = input.nextLine();
                String split [] = line.split(" ");
                if (split[0].equals("Y")) {
                    matrixY[getIndexChar(split[2])][getIndexChar(split[3])] =
                            Math.min(Integer.parseInt(split[4]), matrixY[getIndexChar(split[2])][getIndexChar(split[3])]);
                    if (split[1].equals("B")) {
                        matrixY[getIndexChar(split[3])][getIndexChar(split[2])] =
                                Math.min(Integer.parseInt(split[4]), matrixY[getIndexChar(split[3])][getIndexChar(split[2])]);
                    }
                } else {
                    matrixM[getIndexChar(split[2])][getIndexChar(split[3])] =
                            Math.min(Integer.parseInt(split[4]), matrixM[getIndexChar(split[2])][getIndexChar(split[3])]);
                    if (split[1].equals("B")) {
                        matrixM[getIndexChar(split[3])][getIndexChar(split[2])] =
                                Math.min(Integer.parseInt(split[4]), matrixM[getIndexChar(split[3])][getIndexChar(split[2])]);
                    }
                }
            }
            String position = input.nextLine();
            miguel = position.split(" ")[0];
            professor = position.split(" ")[1];

            int miguelIndex = getIndexChar(miguel);
            int professorIndex = getIndexChar(professor);

            floyd(matrixM);
            floyd(matrixY);

            List<String> meetUpPoints = new ArrayList<>();
            int minEnergy = Integer.MAX_VALUE;
            for (int i = 0; i < 26; i++) {
                if (matrixY[miguelIndex][i] == Integer.MAX_VALUE || matrixM[professorIndex][i] == Integer.MAX_VALUE) {
                    //nothing
                } else if (matrixY[miguelIndex][i] + matrixM[professorIndex][i] == minEnergy) {
                    meetUpPoints.add(getStringFromIndex(i));
                } else if (matrixY[miguelIndex][i] + matrixM[professorIndex][i] < minEnergy) {
                    meetUpPoints.clear();
                    minEnergy = matrixY[miguelIndex][i] + matrixM[professorIndex][i];
                    meetUpPoints.add(getStringFromIndex(i));
                }
            }

            if (minEnergy == Integer.MAX_VALUE) {
                System.out.println("You will never meet.");
            } else {
                System.out.print(minEnergy);
                for (String meetUpPoint : meetUpPoints) {
                    System.out.print(" " + meetUpPoint);
                }
                System.out.println();
            }

        }


    }

    private static void floyd(int [][] matrix) {
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (matrix[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < 26; j++) {
                    if (matrix[k][j] != Integer.MAX_VALUE) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }
    }

    private static int getIndexChar(String c) {
        return c.toCharArray()[0] - 'A';
        //a has index 0
        //b has index 1
        //and so on
    }

    private static String getStringFromIndex(int index) {
        return Character.toString((char) (index + 65));
    }


}
