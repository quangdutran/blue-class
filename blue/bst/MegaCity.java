package com.company.blue.bst;

import java.util.*;

public class MegaCity {
    public static void main(String[] args) {
        final double MAX = Math.pow(10, 6);
        Scanner input = new Scanner(System.in);
        int nodes = input.nextInt();
        int population = input.nextInt();
        TreeMap<Double, Integer> radiusTree = new TreeMap<>();
        TreeMap<Long, Double> populationTree = new TreeMap<>();
        for (int i = 0; i < nodes; i++) {
            double radius = getRadius(input.nextInt(), input.nextInt());
            int populationOfRadius = input.nextInt();
            if (radiusTree.containsKey(radius)) {
                radiusTree.put(radius, radiusTree.get(radius) + populationOfRadius);
            } else {
                radiusTree.put(radius, populationOfRadius);
            }
        }

        long totalPopulation = 0L;
        for (Map.Entry<Double, Integer> e : radiusTree.entrySet()) {
            totalPopulation += e.getValue();
            populationTree.put(totalPopulation, e.getKey());
        }



        int gap = (int) MAX - population;
        Map.Entry<Long, Double> entry = populationTree.ceilingEntry((long) gap);
        if (Objects.isNull(entry)) {
            System.out.println(-1);
        } else {
            System.out.println(entry.getValue());
        }
    }

    private static double getRadius(int x, int y) {
        return Math.sqrt(x*x + y*y);
    }
}
