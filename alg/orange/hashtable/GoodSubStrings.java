package com.company.alg.orange.hashtable;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Codeforces: Use Trie for better performance
 */
public class GoodSubStrings {
    static String mainWord;
    static boolean goodCharacter [] = new boolean[26];

    static class Node {
        int endWord;
        Node [] children;

        public Node() {
            children = new Node [26];
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        mainWord = input.nextLine();
        String good = input.nextLine();
        setGoodChar(good);
        int k = input.nextInt();

        Node head = new Node();
        int result = 0;
        for (int i = 0; i < mainWord.length(); i++) {
            int countBadChar = 0;
            Node temp = head;
            for (int j = i; j < mainWord.length(); j++) {
                if (!goodCharacter[mainWord.charAt(j) - 'a'] ) {
                    countBadChar++;
                }
                if (countBadChar > k) {
                    break;
                } else {
                    if (temp.children[mainWord.charAt(j) - 'a'] == null) {
                        temp.children[mainWord.charAt(j) - 'a'] = new Node();
                        result++; // Increase only when there is new character branch
                    }
                    temp = temp.children[mainWord.charAt(j) - 'a'];
                }
            }
        }
        System.out.println(result);
    }



    //Naive algorithm, TLE
    static HashSet<String> validString;
    public static void mainNaive(String[] args) {
        Scanner input = new Scanner(System.in);
        mainWord = input.nextLine();
        String good = input.nextLine();
        setGoodChar(good);
        int k = input.nextInt();

        validString = new HashSet<>();

        for (int i = 0; i < mainWord.length(); i++) {
            int countBadChar = goodCharacter[mainWord.charAt(i) - 'a'] ? 0 : 1;
            for (int j = i; j < mainWord.length(); j++) {
                if (!goodCharacter[mainWord.charAt(j) - 'a'] && i != j) {
                    countBadChar++;
                }
                if (countBadChar > k) {
                    break;
                } else {
                    validString.add(getWord(i,j));
                }
            }
}

        System.out.println(validString.size());
                }

    private static String getWord(int i, int j) {
        return mainWord.substring(i,j+1);
    }

    private static void setGoodChar(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '1') {
                goodCharacter[i] = true;
            }
        }
    }
}
