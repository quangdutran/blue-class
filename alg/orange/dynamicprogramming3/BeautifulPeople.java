package com.company.blueclass.alg.orange.dynamicprogramming3;

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
            if (strength != o.strength) {
                return strength - o.strength;
            } else {
                return o.beauty - beauty;
            }
        }
    }

    private static ArrayList<Integer> dp;
    private static int [] path;

    private static int lowerBound(People [] people, ArrayList<Integer> dp, int length, People p) {
        int left =0, right = length;
        int pos = length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int index = dp.get(mid);
            if (people[index].beauty >= p.beauty) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int lis(People [] people) {
        int length = 1;
        dp = new ArrayList<>();
        dp.add(0);
        path = new int[people.length];

        Arrays.fill(path, -1);
        for (int i = 1; i < people.length; i++) {
            if (people[i].beauty <= people[dp.get(0)].beauty) {
                dp.set(0, i);
            } else if (people[i].beauty > people[dp.get(length - 1)].beauty) {
                path[i] = dp.get(length-1);
                dp.add(i);
                length++;
            } else {
                int pos = lowerBound(people, dp, length, people[i]);
                path[i] = dp.get(pos - 1);
                dp.set(pos, i);
            }
        }
        return length;
    }

    private static List<Integer> getIndex(People [] people, int length) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = dp.get(length - 1);
        while (i >= 0) {
            result.add(people[i].index);
            i = path[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int people = input.nextInt();
        People [] peopleArray = new People[people];
        for (int i = 0; i < people; i++) {
            peopleArray[i] = new People(input.nextInt(), input.nextInt(), i+1);
        }

        Arrays.sort(peopleArray);
        int length = lis(peopleArray);
        System.out.println(length);
        System.out.println(getIndex(peopleArray, length).stream()
                .map(Object::toString).collect(Collectors.joining(" ")));
    }
}
