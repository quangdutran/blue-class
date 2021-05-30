package com.company.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheLastShot {

    static List<Integer> [] graph;
    static boolean [] visited;
    static int [] path;
    static int count = 0;
    static int vertices;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        vertices = input.nextInt();
        int edges = input.nextInt();
        graph = new List[vertices + 1];
        visited = new boolean [vertices + 1];

        for (int i = 0; i < vertices + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges; i++) {
            graph[input.nextInt()].add(input.nextInt());
        }

        int ans = 0;

        for (int i = 1; i < vertices + 1; i++) {
                refresh();
                dfsRecursive(i);
                ans = Math.max(count, ans);

        }

        System.out.println(ans);

        input.close();
    }

    private static void refresh() {
        count = 0;
        visited = new boolean[vertices + 1];
    }

    public static void dfsRecursive(int start) {
        visited[start] = true;
        count++;

        for (int neighbor : graph[start]) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor);
            }
        }
    }
}
