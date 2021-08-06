package com.company.blue.finaltest;

import java.util.Arrays;
import java.util.Scanner;

public class MUHImportantThings {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int size = input.nextInt();
        Item[] items = new Item[size + 1];

        for (int i = 1; i < items.length; i++) {
            items[i] = new Item(input.nextInt(), i);
        }

        Arrays.sort(items, 1, size + 1);

        int count = 0;
        for (int i = 1; i < items.length - 1; i++) {
            if (items[i].num.equals(items[i + 1].num)) {
                count++;
            }
        }

        if (count < 2) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            for (int i = 1; i < size; i++) {
                System.out.print(items[i].index + " ");
            }
            System.out.println(items[size].index);
            int t = 0;
            for (int i = 1; i < size; i++) {
                if (items[i].num.equals(items[i + 1].num)) {
                    t++;
                    if (t == 3) break;
                    Item s = items[i];
                    items[i] = items[i + 1];
                    items[i + 1] = s;
                    for (int j = 1; j < size; j++) {
                        System.out.print(items[j].index + " ");
                    }
                    System.out.println(items[size].index);
                }
            }

        }

    }

    static class Item implements Comparable<Item> {
        Integer num;
        int index;

        public Item(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Item o) {
            return num.compareTo(o.num);
        }
    }
}
