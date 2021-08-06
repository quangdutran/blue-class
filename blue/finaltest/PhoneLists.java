package com.company.blue.finaltest;

import java.util.Scanner;

public class PhoneLists {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();
            Trie trie = new Trie();
            boolean pd = true;
            String str1[] = new String[n];
            for (int i = 0; i < n; i++) {
                str1[i] = input.next();
                trie.insert(str1[i]);
            }
            for (int i = 0; i < n; i++) {
                if (trie.countPrefix(str1[i]) > 1) {
                    pd = false;
                }
            }
            if (pd) System.out.println("YES");
            else System.out.println("NO");
        }
        input.close();
    }
}

class Trie {
    private int size = 26;
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    private class TrieNode {
        private int num;
        private TrieNode[] son;
        private boolean isEnd;
        private char val;

        public TrieNode() {
            num = 1;
            son = new TrieNode[size];
            isEnd = false;
        }
    }

    public void insert(String str) {
        if (str == null || str.length() == 0) return;
        TrieNode node = root;
        char letters[] = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - '0';
            if (node.son[pos] == null) {
                node.son[pos] = new TrieNode();
                node.son[pos].val = letters[i];
            } else {
                node.son[pos].num++;
            }
            node = node.son[pos];
        }
        node.isEnd = true;
    }

    public int countPrefix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return -1;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - '0';
            if (node.son[pos] == null)
                return 0;
            else
                node = node.son[pos];
        }
        return node.num;
    }

    public String hasPredix(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return null;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int pos = letters[i] - '0';
            if (node.son[pos] == null)
                return null;
            else
                node = node.son[pos];
        }
        preTraverse(node, prefix);
        return prefix;
    }

    private void preTraverse(TrieNode node, String prefix) {
        if (!node.isEnd) {
            for (TrieNode child : node.son) {
                if (child != null) {
                    preTraverse(child, prefix + child.val);
                }
            }
            return;
        }
        System.out.println(prefix);
    }
}
