package com.company.blue.bfs;

import java.util.*;
import java.util.Arrays;

public class KefaAndPark {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfVertices = input.nextInt();
        int numberOfConsecutiveCats = input.nextInt();
        int [] hasCat = new int[numberOfVertices + 1];
        boolean [] visited = new boolean[numberOfVertices + 1];
        List<Set<Integer>> tree = new ArrayList<>();
        for (int i = 1; i < numberOfVertices + 1; i++) {
            hasCat[i] = input.nextInt();
            tree.add(new HashSet<>());
        }
        tree.add(new HashSet<>());
        for (int i = 0; i < numberOfVertices - 1; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            tree.get(first).add(second);
            tree.get(second).add(first);
        }
        input.close();

        int [] path = new int [numberOfVertices + 1];
        for (int i = 0; i < numberOfVertices + 1; i++) {
            path[i] = -1;
        }

        List<Integer> leaves = new ArrayList<>();
        //find the restaurant
        for (int i = 1; i < numberOfVertices + 1; i++) {
            if (tree.get(i).size() == 1 && i != 1) {
                leaves.add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            Integer current = queue.remove();
            for (Integer neighbor : tree.get(current)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    path[neighbor] = current;
                }
            }
        }

        int count = 0;

        outerloop:
        for (Integer leaf : leaves) {
            int countCats = 0;
            List<Integer> pathDone = getPath(1, leaf, path);
            for (int i = pathDone.size() - 1; i >= 0; i--) {
                if (hasCat[pathDone.get(i)] == 1) {
                    countCats++;
                } else {
                    if (countCats > 0) {
                        countCats--;
                    }
                }
                if (countCats > numberOfConsecutiveCats) {
                  continue outerloop;
                }
            }
            count++;
        }

        System.out.println(count);
    }

    private static List<Integer> getPath(int start, int finish, int [] path) {
        if (start == finish){
            //System.out.println(start);
            return Arrays.asList(start);
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

        return b;
    }
}
