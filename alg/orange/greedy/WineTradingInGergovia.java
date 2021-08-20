package com.company.alg.orange.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WineTradingInGergovia {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            int total = input.nextInt();
            if (total == 0) {
                break;
            }
            List<Pair> buy = new ArrayList<>();
            List<Pair> sell = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                int wine = input.nextInt();
                if (wine >= 0) {
                    buy.add(new Pair(i + 1, wine));
                } else {
                    sell.add(new Pair(i + 1, wine));
                }
            }

            long workUnit = 0;

            for (int i = 0,j = 0; i < buy.size() && j < sell.size();) {
                if (buy.get(i).wine == 0) {
                    i++;
                    continue;
                }
                int amount = buy.get(i).wine + sell.get(j).wine;
                int distance = Math.abs(buy.get(i).position - sell.get(j).position);
                if (amount > 0) {
                    workUnit += Math.abs(sell.get(j).wine) * distance;
                    buy.get(i).wine = amount;
                    sell.get(j).wine = 0;
                    ++j;
                } else if (amount == 0) {
                    workUnit += buy.get(i).wine * distance;
                    i++;
                    j++;
                } else {
                    workUnit += buy.get(i).wine * distance;
                    sell.get(j).wine = amount;
                    buy.get(i).wine = 0;
                    ++i;
                }
            }
            System.out.println(workUnit);
        }
        input.close();
    }
}

class Pair {
    int position;
    int wine;

    public Pair(int position, int wine) {
        this.position= position;
        this.wine = wine;
    }
}
