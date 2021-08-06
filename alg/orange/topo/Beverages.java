package com.company.alg.orange.topo;

import java.util.*;
import java.util.stream.Collectors;

public class Beverages {

    static Map<String, Integer> beverageIndex = new HashMap<>();
    static Map<Integer, String> indexBeverage = new HashMap<>();


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (input.hasNextLine()) {
            String firstLine = input.nextLine();
            if (firstLine.isEmpty()) {
                continue;
            }
            count++;
            int beverages = Integer.parseInt(firstLine);
            List<Integer> [] adjacent = new List[beverages];
            for (int i = 0; i < beverages; i++) {
                String name = input.nextLine();
                beverageIndex.put(name, i);
                indexBeverage.put(i, name);
                adjacent[i] = new ArrayList<>();
            }

            String dependency = input.nextLine();
            int dependencyNum = Integer.parseInt(dependency);
            for (int i = 0; i < dependencyNum; i++) {
                String twoBeverages = input.nextLine();
                String first = twoBeverages.split(" ")[0];
                String second = twoBeverages.split(" ")[1];
                adjacent[getBeverageIndex(first)].add(getBeverageIndex(second));
            }

            System.out.println("Case #" + count + ": Dilbert should drink beverages in this order: " + kahn(adjacent) + ".");
            System.out.println();
            beverageIndex.clear();
        }
    }

    private static String kahn(List<Integer> [] adjacent) {
        int [] degree = new int [adjacent.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < adjacent.length; i++) {
            for (Integer neighbor : adjacent[i]) {
                degree[neighbor]++;
            }
        }

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> order = new ArrayList<>();
        while(!queue.isEmpty()) {
            int top = queue.poll();
            order.add(top);
            for (Integer neighbor : adjacent[top]) {
                degree[neighbor]--;
                if (degree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return order.stream().map(i -> indexBeverage.get(i)).collect(Collectors.joining(" "));
    }

    private static int getBeverageIndex(String name) {
        return beverageIndex.get(name);
    }
}
