package com.company.blue.dsu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Forests {
    private static int[] parent;
    private static boolean[] hasOpinion;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while (--cases >= 0) {
            int person = input.nextInt();
            int tree = input.nextInt();
            input.nextLine();

            hasOpinion = new boolean[person + 1];
            parent = new int [person + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }

            Set<Integer>[] personTrees = new HashSet[person + 1];
            for (int i = 0; i < personTrees.length; i++) {
                personTrees[i] = new HashSet<>();
            }

            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                String[] pt = line.split(" ");
                hasOpinion[Integer.parseInt(pt[0])] = true;
                personTrees[Integer.parseInt(pt[0])].add(Integer.parseInt(pt[1]));
            }

            for (int i = 0; i < personTrees.length; i++) {
                for (int j = i + 1; j < personTrees.length; j++) {
                    if (personTrees[i].equals(personTrees[j])) {
                        unionSet(i, j);
                    }
                }
            }

            int ans = 0;
            for (int i = 0 ; i < parent.length; i++) {
                if (i == parent[i] && hasOpinion[i]) {
                    ans++;
                }
            }
            System.out.println(ans);
            if (cases > 0) {
                System.out.println();
            }

        }
    }

    private static int findSet(int u) {
        return u == parent[u] ? u : findSet(parent[u]);
    }

    private static void unionSet(int u, int v) {
        int up = findSet(u);
        int uv = findSet(v);
        if (up != uv) {
            parent[up] = uv;
        }
    }
}
