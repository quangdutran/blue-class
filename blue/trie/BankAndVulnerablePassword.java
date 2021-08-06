package com.company.blue.trie;

import java.util.Scanner;

public class BankAndVulnerablePassword {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int words = input.nextInt();
        input.nextLine();
        Trie trie = new Trie();
        for (int i = 0; i < words; i++) {
            if (trie.insertWord(input.nextLine())) {
                System.out.println("vulnerable");
                return;
            }
        }
        System.out.println("non vulnerable");


    }

    static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public boolean insertWord(String word) {
            char [] charArray = word.toCharArray();
            Node temp = root;
            for (char c : charArray) {
                if (temp.children[getCharIndex(c)] == null) {
                    temp.children[getCharIndex(c)] = new Node();
                }
                temp = temp.children[getCharIndex(c)];
                if (temp.endWord > 0) {
                    return true;
                }
            }
            if (nodeHasNextChar(temp) || temp.endWord > 0) {
                return true;
            }
            temp.endWord++;
            return false;
        }

        private boolean nodeHasNextChar(Node node) {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasWord(String word) {
            char [] charArray = word.toCharArray();
            Node temp = root;
            for (char c : charArray) {
                if (temp.children[getCharIndex(c)] == null) {
                    return false;
                }
                temp = temp.children[getCharIndex(c)];
            }
            return temp.endWord > 0;
        }

        private int getCharIndex(char c) {
            return c - 'a';
        }
    }

    static class Node {
        private int endWord;
        private Node [] children;

        public Node() {
            children = new Node[26];
        }
    }
}
