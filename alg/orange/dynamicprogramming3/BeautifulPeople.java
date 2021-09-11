package com.company.alg.orange.dynamicprogramming3;

import java.util.*;
import java.util.stream.Collectors;

public class BeautifulPeople {

    private static class People implements Comparable<People> {
        int strength;
        int beauty;
        int index;

        public People(int strength, int beauty, int index) {
            this.strength = strength;
            this.beauty = beauty;
            this.index = index;
        }

        @Override
        public int compareTo(People o) {
            return strength - o.strength;
        }
    }

    private static int lowerBound(People [] a, ArrayList<Integer> sub, int n, People p) {
        int left = 0, right = n;
        int pos = n;
        while(left < right) {
            int mid = left + (right - left) / 2;
            int index = sub.get(mid);
            if (a[index].beauty >= p.beauty) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int lisOptimized(People [] people) {
        int length = 1;
        dp = new ArrayList<>();
        dp.add(0);
        path = new int [people.length];
        Arrays.fill(path, -1);
        for (int i = 1; i < people.length; i++) {
            if (people[i].beauty < people[dp.get(0)].beauty) {
                dp.set(0 ,i);
            }
            else if (people[i].strength  > people[dp.get(length - 1)].strength
                    && people[i].beauty  > people[dp.get(length - 1)].beauty) {
                path[i] = dp.get(length -1);
                dp.add(i);
                length++;
            }
            else if (people[i].strength  == people[dp.get(length - 1)].strength
                    && people[i].beauty  < people[dp.get(length - 1)].beauty)   {
                path[i] = dp.get(length-1);
                dp.set(length - 1, i);
            }
        }
        return length;
    }

    private static int LIS(People [] people) {
        dp = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            dp.add(1);
        }
        path = new int[people.length];
        Arrays.fill(path, -1);

        for (int i = 1; i < people.length; i++) {
            for (int j = 0; j < i; j++) {
                if (people[j].strength < people[i].strength && people[j].beauty < people[i].beauty
                              && dp.get(j) + 1 > dp.get(i)) {
                    dp.set(i, dp.get(j) + 1);
                    path[i] = j;
                }
            }
        }
        int length = 0;
        for (int i = 0; i < people.length; i++) {
            if (length < dp.get(i)) {
                length = dp.get(i);
                last = i;
            }
        }
        return length;
    }

    private static List<Integer> getOrder(People [] people, int length) {
        ArrayList<People> result = new ArrayList<>();
        int i = dp.get(length -1);
        while (i >= 0) {
            result.add(people[i]);
            i = path[i];
        }
        return result.stream().map(r -> r.index).collect(Collectors.toList());
    }

    private static ArrayList<Integer> dp;
    private static int path [];
    private static int last;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        People [] people = new People [n];
        for (int i =0; i < n; i++) {
            people[i] = new People(input.nextInt(), input.nextInt(), i);
        }

        Arrays.sort(people);
        int invited = lisOptimized(people);
        List<Integer> indexes = getOrder(people, invited);
        System.out.println(invited);
        indexes.forEach(index -> {
            System.out.print((index + 1) + " ");
        });
    }
}
