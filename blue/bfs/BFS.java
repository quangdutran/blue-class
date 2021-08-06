package com.company.blue.bfs;

import java.util.*;
import java.util.Arrays;

public class BFS {

    public static void main(String[] args) {
        ArrayList<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1,3,8));
        graph.add(Arrays.asList(0,2,7));
        graph.add(Arrays.asList(1,3,5,7));
        graph.add(Arrays.asList(0,2,4));
        graph.add(Arrays.asList(3,8));
        graph.add(Arrays.asList(2,6));
        graph.add(Arrays.asList(5));
        graph.add(Arrays.asList(1,2));
        graph.add(Arrays.asList(0,4));

        boolean [] visited = new boolean [9];
        int [] path = new int [9];
        for (int i = 0; i < 9; i++) {
            path[i] = -1;
        }

        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(0);
        //change path of point 0
        path[0] = -2;

        while (!queue.isEmpty()) {
            Integer point = queue.removeFirst();
            for (Integer adjacentPoint : graph.get(point)) {
                if (path[adjacentPoint] == -1) {
                    queue.add(adjacentPoint);
                    path[adjacentPoint] = point;
                }
            }
        }

        for (int i : path) {
            System.out.print(i + " ");
        }
        System.out.println();
        print(0,5,path);
    }

    private static void print(int start, int finish, int [] path) {
        if (start == finish){
            System.out.println(start);
            return;
        }

        if (path[finish] == -1) {
            System.out.println("NO path");
            return;
        }

        List<Integer> b = new ArrayList<>();
        while (true) {
            b.add(finish);
            finish = path[finish];
            if (start == finish) {
                b.add(finish);
                break;
            }
        }

        for (int i = b.size() - 1 ; i >= 0; i--) {
            System.out.printf("%d ", b.get(i));
        }
    }
}
