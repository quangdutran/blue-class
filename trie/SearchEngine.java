package com.company.trie;

import java.util.Scanner;

public class SearchEngine {

    public static void main(String[] args) {
        Trie tree = new Trie();
        Scanner input = new Scanner(System.in);
        int words = input.nextInt();
        int queries = input.nextInt();
        input.nextLine();

        for (int i = 0; i < words; i++) {
            String line = input.nextLine();
            String [] w = line.split(" ");
            tree.insertWordWithPriority(w[0], Integer.parseInt(w[1]));
        }

        for (int i = 0; i < queries; i++) {
            System.out.println(tree.hasWord(input.nextLine()));
        }
    }
    public static class Trie {
        private static Node root;

        public Trie() {
            root = new Node();
        }

        public void insertWord(String word) {
            char [] wordArray = word.toCharArray();
            Node temp = root;
            for(char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    temp.children[getIndexChar(c)] = new Node();
                }
                temp = temp.children[getIndexChar(c)];
            }
            temp.endWord++;
        }

        public void insertWordWithPriority(String word, int priority) {
            char [] wordArray = word.toCharArray();
            Node temp = root;
            for(char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    temp.children[getIndexChar(c)] = new Node(priority);
                }
                temp = temp.children[getIndexChar(c)];
                if (temp.endWord < priority) {
                    temp.endWord = priority;
                }
            }
        }

        public int hasWord(String word) {
            char [] wordArray = word.toCharArray();
            Node temp = root;
            for (char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    return -1;
                }
                temp = temp.children[getIndexChar(c)];
            }
            return temp.endWord;
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

            public Node(int priority) {
                this.children = new Node [26];
                this.endWord = priority;
            }
        }
    }




}
