package com.company.blue.bst;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class HardwoodSpecies {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        input.nextLine();
        while(--cases >= 0) {
            TreeMap<String, Integer> treeMap = new TreeMap<>();
            String line = input.nextLine();
            int countTree = 0;
            while (!line.isEmpty()) {
                if (treeMap.containsKey(line)) {
                    treeMap.put(line, treeMap.get(line) + 1);
                } else {
                    treeMap.put(line, 1);
                }
                countTree++;
                if (!input.hasNextLine()) {
                    break;
                }
                line = input.nextLine();
            }
            for (Map.Entry<String, Integer> e : treeMap.entrySet()) {
                System.out.println(e.getKey() + " " + String.format("%,.4f", (double) e.getValue()  / countTree * 100));
            }
        }
    }
}
