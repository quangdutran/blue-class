package com.company.blue.dsu;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LostAndSurvived {
    private static int [] parent;
    private static int [] rank;
    private static int [] frequency;
    private static PriorityQueue<Count> minHeap;
    private static int maxi = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();

        parent = new int[n + 1];
        rank = new int[n + 1];
        frequency = new int [n+1];
        Arrays.fill(frequency, 1);
        Arrays.fill(rank,1);
        minHeap = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            frequency[i] = 1;
            parent[i] = i;
            minHeap.add(new Count(1, i));
        }

        maxi = 1;

        for (int i = 0; i < q; i++) {
            int first = input.nextInt();
            int second = input.nextInt();
            unionSet(first, second);

            while (true) {
                Count top = minHeap.peek();
                int parent = findSet(top.r);
                if (parent != top.r) {
                    minHeap.poll();
                    continue;
                } else if (frequency[parent] != top.count) {
                    minHeap.poll();
                    continue;
                }
                break;
            }

            System.out.println(maxi - minHeap.peek().count);
        }
    }

    private static int findSet(int u) {
        return u == parent[u] ? u : findSet(parent[u]);
    }

    private static void unionSet(int u, int v) {
        int up = findSet(u);
        int vp = findSet(v);
        if (up == vp) {
            return;
        }

        if (rank[up] > rank[vp]) {
            parent[vp] = up;
            frequency[up] += frequency[vp];
            minHeap.add(new Count(frequency[up],up));
            maxi = Math.max(maxi, frequency[up]);
        } else if (rank[vp] > rank[up]) {
            parent[up] = vp;
            frequency[vp] += frequency[up];
            minHeap.add(new Count(frequency[vp],vp));
            maxi = Math.max(maxi, frequency[vp]);
        } else {
            parent[up] = vp;
            rank[vp]++;
            frequency[vp] += frequency[up];
            minHeap.add(new Count(frequency[vp],vp));
            maxi = Math.max(maxi, frequency[vp]);
        }
    }

    static class Count implements Comparable<Count> {
        Integer count;
        Integer r;

        public Count(int count, int r) {
            this.count = count;
            this.r = r;
        }

        @Override
        public int compareTo(Count o) {
            if (count.equals(o.count)) {
                return r.compareTo(o.r);
            } else {
                return count.compareTo(o.count);
            }
        }
    }

}
