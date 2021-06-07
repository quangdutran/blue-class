package com.company.floyd;

import java.util.*;

public class PossibleFriendByBFS {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfTestCases = input.nextInt();
        input.nextLine();
        while (numberOfTestCases-- > 0) {
            String line = input.nextLine();
            Set<Integer>[] graph = new HashSet[line.length()];
            boolean visited [] = new boolean [line.length()];
            for (int i = 0; i <line.length(); i++) {
                graph[i] = new HashSet<>();
            }
            char[][] matrix = new char[line.length()][line.length()];

            for (int j = 0; j < line.length(); j++) {
                matrix[0][j] = line.charAt(j);
                if (line.charAt(j) == 'Y') {
                    graph[0].add(j);
                    graph[j].add(0);
                }
            }
            for (int j = 1; j < line.length(); j++) {
                String nextLine = input.nextLine();
                for (int k = 0; k < nextLine.length(); k++) {
                    matrix[j][k] = nextLine.charAt(k);
                    if (matrix[j][k] == 'Y') {
                        graph[j].add(k);
                        graph[k].add(j);
                    }

                }
            }

            int ans = 0;
            int index = 0;
            for (int i = 0; i < graph.length; i++) {
                int pFriend = countPossibleFriend(graph, i, visited);
                if (pFriend > ans) {
                    ans = pFriend;
                    index = i;
                }


                //clean visited array
                visited = new boolean [line.length()];
            }
            System.out.println(index + " " + ans);
        }

    }

    private static int countPossibleFriend(Set<Integer> [] graph, int start, boolean [] visited) {
        int [] distance = new int [graph.length];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int top = queue.remove();
            for (int neighbor : graph[top]) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[top] + 1;
                }
            }
        }

        int ans = 0;
        for (int d : distance) {
            if (d == 2) {
                ans++;
            }
        }
        return ans;
    }
}
