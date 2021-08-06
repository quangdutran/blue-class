package com.company.blue.trie;

public class Trie {
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