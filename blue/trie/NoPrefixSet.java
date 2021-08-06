package com.company.blue.trie;

import java.util.Scanner;

public class NoPrefixSet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        Trie trie = new Trie();

        for (int i = 0; i < cases; i++) {
            String line = input.nextLine();
            if (trie.insertWord(line)) {
                System.out.println("BAD SET");
                System.out.println(line);
                return;
            }
        }
        System.out.println("GOOD SET");
    }

    public static class Trie {
        private static Node root;

        public Trie() {
            root = new Node();
        }

        public boolean insertWord(String word) {
            char [] wordArray = word.toCharArray();
            boolean newNode = false;
            Node temp = root;
            for(char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    temp.children[getIndexChar(c)] = new Node();
                    newNode = true;
                }
                temp = temp.children[getIndexChar(c)];
                if (temp.endWord > 0) {
                    return true;
                }
            }
            temp.endWord++;
            return !newNode;
        }

        public boolean hasWord(String word) {
            char [] wordArray = word.toCharArray();
            Node temp = root;
            for (char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    return false;
                }
                temp = temp.children[getIndexChar(c)];
            }
            return temp.endWord > 0;
        }

        public static int getIndexChar(char c) {
            return c - 'a';
        }

        public static class Node {
            private int endWord;
            private Node [] children;

            public Node() {
                this.children = new Node [26];
            }
        }
    }
}
