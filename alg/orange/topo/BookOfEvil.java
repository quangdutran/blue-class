package com.company.alg.orange.topo;

import java.util.*;

/**
 * Codeforces
 *
 * thay vì phải loang từ m đỉnh, ta có thể loang từ 2 đỉnh có khoảng cách xa nhất trong đồ thị.
 * Ta ký hiệu khoảng cách giữa 2 đỉnh u,v là dist(u,v). Giả sử 2 đỉnh có khoảng cách xa nhất trong đồ thị là s,r.
 * Một đỉnh t thỏa yêu cầu bài toán khi dist(s,t) <= d và dist(r,t) ≤ d
 */
public class BookOfEvil {
    static int nodes, affectedNodes, affectedRadius;
    static List<Integer> [] graph;
    static boolean [] affected;

    private static void BFS(int start, int [] distance) {
        for (int i = 1; i <= nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;

        boolean [] visited = new boolean[nodes + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int top = queue.pop();
            for (Integer neighbor : graph[top]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    distance[neighbor] = distance[top] + 1;
                }
            }
        }
    }

    private static int findFurthestNode(int start) {
        int []  distance = new int [nodes + 1];
        BFS(start, distance);

        int furthestNode = start;
        int maxDist = 0;
        for (int i = 1; i <= nodes; i++) {
            if (distance[i] > maxDist && affected[i]) {
                maxDist = distance[i];
                furthestNode = i;
            }
        }
        return furthestNode;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        nodes = input.nextInt();
        affectedNodes = input.nextInt();
        affectedRadius = input.nextInt();
        affected = new boolean [nodes + 1];
        graph = new List[nodes + 1];
        for (int i = 0; i < affectedNodes; i++) {
            affected[input.nextInt()] = true;
        }

        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= nodes - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        int s = findFurthestNode(1); //Could start at any node
        int t = findFurthestNode(s);

        int [] distanceS = new int [nodes + 1];
        BFS(s, distanceS);

        int [] distanceT = new int [nodes + 1];
        BFS(t, distanceT);

        int nodeCount = 0;
        for (int i = 1; i <= nodes; i++) {
            if (distanceS[i] <= affectedRadius && distanceT[i] <= affectedRadius) {
                nodeCount++;
            }
        }
        System.out.println(nodeCount);
        input.close();
    }
}

