package com.company.alg.orange.dynamicprogramming2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * UVA
 */
public class Compromise {
    private static int polyHash(String string) {
        int MOD = (int) 1e9 + 7;
        int hashValue = 0;
        int p = 29;
        for (int i = 0; i < string.length(); i++) {
            hashValue = (string.charAt(i) + p * hashValue) % MOD;
        }
        return hashValue;
    }

    private static int [][] dp;
    private static List<Integer> resultHashList;
    private static List<Integer> resultHashListClone; // To check if we could add when two letter in matrix equal.
    // Result is no since this probably greater than resultHashList
    //It counts the cases that diagonally cross each other as well


    private static int LCS(int [] array1, int [] array2) {
        dp = new int[array1.length + 1][array2.length + 1];
        for (int i = 0; i <= array1.length; i++) {
            for (int j = 0; j <= array2.length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (array1[i-1] == array2[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    resultHashListClone.add(array1[i-1]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        getResult(array1, array2);
        return dp[array1.length][array2.length];
    }

    private static void getResult(int [] array1, int [] array2) {
        int i = array1.length;
        int j = array2.length;

        while(i > 0 && j > 0) {
            if (array1[i-1] == array2[j-1]) {
                resultHashList.add(array1[i-1]);
                i--;
                j--;
            } else {
                if (dp[i-1][j] > dp[i][j-1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            StringBuilder text1 = new StringBuilder();
            StringBuilder text2 = new StringBuilder();
            while (true) {
                String line = input.nextLine();
                if (line.equals("#")) {
                    break;
                }
                text1.append(line + " ");
            }
            while (true) {
                String line = input.nextLine();
                if (line.equals("#")) {
                    break;
                }
                text2.append(line + " ");
            }

            String [] words1 = text1.toString().split(" ");
            String [] words2 = text2.toString().split(" ");
            int [] hash1 = new int [words1.length];
            int [] hash2 = new int [words2.length];
            HashMap<Integer, String> wordMap = new HashMap<>();

            for (int i = 0; i < words1.length; i++) {
                hash1[i] = polyHash(words1[i]);
                wordMap.put(hash1[i], words1[i]);
            }
            for (int i = 0; i < words2.length; i++) {
                hash2[i] = polyHash(words2[i]);
                wordMap.put(hash2[i], words2[i]);
            }

            resultHashList = new ArrayList<>();
            resultHashListClone = new ArrayList<>();
            LCS(hash1, hash2);
            for (int i = resultHashList.size() - 1; i >= 0; i--) {
                System.out.print(wordMap.get(resultHashList.get(i)) + " ");
            }
        }
    }

}
