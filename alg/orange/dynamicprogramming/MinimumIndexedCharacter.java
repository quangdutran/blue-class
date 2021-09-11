package com.company.alg.orange.dynamicprogramming;

import com.company.alg.orange.hashtable.Hash;

import java.util.HashMap;
import java.util.Scanner;

/**
 * GeeksForGeeks
 */
public class MinimumIndexedCharacter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();

        while (--cases >= 0) {
            String word1 = input.nextLine();
            String word2 = input.nextLine();

            HashMap<Character, Integer> indexMap = new HashMap<>();

            for (int i = 0; i < word1.length(); i++) {
                if (!indexMap.containsKey(word1.charAt(i))) {
                    indexMap.put(word1.charAt(i), i);
                }
            }

            int min = Integer.MAX_VALUE;
            Character minChar = null;
            for (int i = 0; i < word2.length(); i++) {
                if (indexMap.containsKey(word2.charAt(i))) {
                    if (indexMap.get(word2.charAt(i)) < min) {
                        min = Math.min(min, indexMap.get(word2.charAt(i)));
                        minChar = word2.charAt(i);
                    }
                }
            }

            if (minChar == null) {
                System.out.println("No character present");
            } else {
                System.out.println(minChar);
            }
        }
    }
}
