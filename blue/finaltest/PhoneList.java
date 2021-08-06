package com.company.blue.finaltest;

import java.util.Scanner;

public class PhoneList {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();

        while(--cases >= 0) {
            int numbers = input.nextInt();
            input.nextLine();
            Trie trie = new Trie();
            boolean flag = true;
            for (int i = 0; i < numbers; i++) {
                String line = input.nextLine().trim();
                line = line.replace(" ", "");
                line = line.replace(".", "");
                if (trie.insertWord(line)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
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
            return c - '0';
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
