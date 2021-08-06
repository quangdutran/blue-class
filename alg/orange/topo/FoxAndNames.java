package com.company.alg.orange.topo;

import java.util.*;
import java.util.stream.Collectors;

public class FoxAndNames {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int words = input.nextInt();
        String [] wordList = new String [words];
        input.nextLine();
        for(int i = 0; i < words; i++) {
            wordList[i] = input.nextLine();
        }

        List<Integer> [] adj = new List[26];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < words; i++) {
            char [] prev = wordList[i-1].toCharArray();
            char [] current = wordList[i].toCharArray();

            for (int j = 0; j < prev.length; j++) {
                if (j >= current.length) {
                    System.out.println("Impossible");
                    return;
                }
                if (prev[j] != current[j]) {
                    adj[getIndexOfChar(prev[j])].add(getIndexOfChar(current[j]));
                    break;
                }
            }
        }

        System.out.println(getOrder(adj));
    }

    private static String getOrder(List<Integer> [] adj) {
        int [] degree = new int[26];

        for (int i = 0; i < 26; i++) {
            for (Integer num : adj[i]) {
                degree[num]++;
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int top = queue.poll();
            order.add(top);
            for (Integer neighbor : adj[top]) {
                degree[neighbor]--;
                if (degree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (order.size() != 26) {
            return "Impossible";
        } else {
            return order.stream().map(FoxAndNames::getCharString).collect(Collectors.joining());
        }
    }

    static private int getIndexOfChar(char a) {
        return a - 'a';
    }

    private static String getCharString(int value) {
        char c = (char) (value + 'a');
        return c + "";
    }
}
