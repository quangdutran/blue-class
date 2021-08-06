package com.company.blue.trie;

import java.util.Scanner;

public class DNAPrefix {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        int count = 0;
        while (--cases >= 0) {
            Trie trie = new Trie();
            int words = input.nextInt();
            input.nextLine();
            for (int i = 0; i < words; i++) {
                String line = input.nextLine();
                trie.insertWord(line);
            }
            System.out.println("Case " + ++count + ": " + trie.max);
        }
    }


    public static class Trie {
        private static Node root;
        private static int max;
        public Trie() {
            root = new Node();
            max = 0;
        }

        public void insertWord(String word) {
            char [] wordArray = word.toCharArray();
            Node temp = root;
            int count = 0;
            for(char c : wordArray) {
                if (temp.children[getIndexChar(c)] == null) {
                    temp.children[getIndexChar(c)] = new Node(1);
                } else {
                    temp.children[getIndexChar(c)].weight++;
                }
                temp = temp.children[getIndexChar(c)];
                temp.index = ++count;
                max = Math.max(max, temp.weight * temp.index);
            }
        }

        public static int getIndexChar(char c) {
            if (c == 'A') {
                return 0;
            } else if (c == 'C') {
                return 1;
            } else if (c == 'G') {
                return 2;
            } else return 3;
        }

        public static class Node {
            private int weight;
            private Node [] children;
            private int index;

            public Node() {
                this.children = new Node [4];
            }

            public Node(int weight) {
                this.children = new Node [4];
                this.weight = weight;
            }
        }
    }

}
