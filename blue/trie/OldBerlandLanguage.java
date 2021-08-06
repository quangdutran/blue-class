package com.company.blue.trie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class OldBerlandLanguage {
    private static int nn, wordNumber;
    private static boolean flag;
    private static Node [] nodeArray = new Node [1010];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        wordNumber = input.nextInt();
        for (int i = 1; i <= wordNumber; i++) {
            nodeArray[i] = new Node(input.nextInt(), i);
        }
        Arrays.sort(nodeArray, 1, wordNumber +1, lengthCompare);
        nn = 1;
        DFS(0, "");
        if (!flag) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            Arrays.sort(nodeArray, 1, wordNumber +1, idCompare);
            for (int i = 1; i <= wordNumber; i++) {
                System.out.println(nodeArray[i].str);
            }
        }
    }

    private static void DFS(int length, String str1) {
        if(length == nodeArray[nn].l) {
            nodeArray[nn].str = str1;
            nn++;
            if(nn == wordNumber + 1) {
                flag = true;
            }
            return;
        }
        DFS(length + 1, str1+"0");
        if (flag) return;
        DFS(length + 1, str1+"1");
    }

    private static Comparator<Node> idCompare = Comparator.comparing((Node o) -> o.id);
    private static Comparator<Node> lengthCompare = Comparator.comparing((Node o) -> o.l);
    static class Node {
        Integer l, id;
        String str;

        Node(int l, int id) {
            this.l = l;
            this.id = id;
            str = "";
        }
    }
}
