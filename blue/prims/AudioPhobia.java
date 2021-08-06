package com.company.blue.prims;

import java.util.*;

public class AudioPhobia {
    private static int [] dist;
    private static boolean [] visited;
    private static int [] path;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (true) {
            int c = input.nextInt();
            int s = input.nextInt();
            int q = input.nextInt();


            List<Node>[] graph = new List[c + 1];

            for (int i = 1; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            if (c == 0) {
                break;
            }

            for (int i = 0; i < s; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                int third = input.nextInt();
                graph[first].add(new Node(second, third));
                graph[second].add(new Node(first, third));
            }


            System.out.println("Case #" + ++count);
            for (int i = 0; i < q; i++) {
                int first = input.nextInt();
                int second = input.nextInt();
                prim(graph, first, c);
                List<Integer> minGraph[] = new List[c + 1];
                for (int j = 0; j < minGraph.length; j++) {
                    minGraph[j] = new ArrayList<>();
                }

                for (int j = 0; j < path.length; j++) {
                    if (path[j] != -1) {
                        minGraph[j].add(path[j]);
                        minGraph[path[j]].add(j);
                    }
                }
                print(first, second, minGraph);
            }
            System.out.println();
        }
    }

    private static void print(int start, int finish, List<Integer> minGraph []) {
        if (start == finish){
            System.out.println(dist[start]);
            return;
        }

        int pathBFS [] = new int [minGraph.length];
        Arrays.fill(pathBFS, -1);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Integer top = queue.remove();
            for (Integer neighbor : minGraph[top]) {
                if (pathBFS[neighbor] == -1) {
                    queue.add(neighbor);
                    pathBFS[neighbor] = top;
                }
            }
        }

        List<Integer> b = new ArrayList<>();
        while (true) {
            if (pathBFS[finish] == -1) {
                System.out.println("no path");
                return;
            }
            b.add(finish);
            finish = pathBFS[finish];
            if (start == finish) {
                b.add(finish);
                break;
            }
        }

        int max = 0;
        for (int i = b.size() - 1 ; i >= 0; i--) {
            max = Math.max(max, dist[b.get(i)]);
        }
        System.out.println(max);
    }


    private static void prim(List<Node>[] graph, int start, int c) {

        dist = new int[c + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        path = new int[c + 1];
        Arrays.fill(path, -1);
        visited = new boolean[c + 1];


        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        minHeap.add(new Node(start, 0));
        dist[start] = 0;

        while (!minHeap.isEmpty()) {
            Node min = minHeap.poll();
            int currentId = min.id;
            int currentDist = min.dist;
            visited[currentId] = true;
            for (Node neighbor : graph[currentId]) {
                if (!visited[neighbor.id] && neighbor.dist < dist[neighbor.id]) {
                    dist[neighbor.id] = neighbor.dist;
                    minHeap.add(neighbor);
                    path[neighbor.id] = currentId;
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        private int id;
        private Integer dist;

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist.compareTo(o.dist);
        }
    }
}
