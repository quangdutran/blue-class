package com.company.alg.orange.topo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Hierarchy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalStudents = input.nextInt();
        int goodStudents = input.nextInt();
        List<Integer> [] graph = new List[totalStudents + 1];
        for (int i = 0; i <= totalStudents; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= goodStudents; i++) {
            int children = input.nextInt();
            for (int j =0; j < children; j++) {
                graph[i].add(input.nextInt());
            }
        }

        int degree [] = new int [totalStudents + 1];
        for (int i = 1; i <= goodStudents; i++) {
            for (Integer child : graph[i]) {
                degree[child]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i  < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> orderTree = new ArrayList<>();
        while (!queue.isEmpty()) {
            int top = queue.pop();
            orderTree.add(top);
            for (Integer child : graph[top]) {
                degree[child]--;
                if (degree[child] == 0) {
                    queue.add(child);
                }
            }
        }

        int [] result = new int [orderTree.size()];
        result[orderTree.get(0) -1] = 0;
        for (int i = 1; i < result.length; i++) {
            result[orderTree.get(i) - 1] = orderTree.get(i - 1);
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
