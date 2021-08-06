package com.company.blue.codegoda;

import java.util.*;
import java.util.stream.Collectors;

public class FindTheBestHotel {

    //Each city has a minHeap with the cheapest hotel at the root
    //Every add hotel operation, add to this meanheap
    //From the destination, go to every other nodes in graph, compute the distance, + the cost of the cheapest hotel

    private static PriorityQueue<Hotel> [] hotels;
    private static int gas;
    private static List<Long> [] distances;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfNodes = input.nextInt();
        gas = input.nextInt();
        List<Node>[] graph = new List[numberOfNodes + 1];
        hotels = new PriorityQueue[numberOfNodes + 1];
        distances = new List[numberOfNodes + 1];
        for (int i = 0; i <= numberOfNodes; i++) {
            graph[i] = new ArrayList<>();
            hotels[i] = new PriorityQueue<>();
        }
        for (int i = 1; i <= numberOfNodes - 1; i++) {
            int index = input.nextInt();
            int adjacent = input.nextInt();
            int weight = input.nextInt();
            graph[index].add(new Node(adjacent, (long) weight));
            graph[adjacent].add(new Node(index, (long) weight));
        }
        int queries = input.nextInt();
        input.nextLine();
        int hotelIDCount  = 0;
        for (int i = 0; i < queries; i++) {
            String line = input.nextLine();
            String [] split = line.split(" ");

            if (split[0].equals("H")) {
                long cost = Long.parseLong(split[3]);
                Hotel hotel = new Hotel(split[1], cost, ++hotelIDCount);
                hotels[Integer.parseInt(split[2])].add(hotel);
            } else {
                int start = Integer.parseInt(split[1]);
                processGraph(graph, start);
            }
        }
        input.close();

    }

    private static void processGraph(List<Node> [] graph, int start) {
        if (distances[start] == null) {
            PriorityQueue<Node> heap = new PriorityQueue<>();
            long [] distance = new long [graph.length + 1];
            int [] path = new int [graph.length + 1];
            for (int i = 1; i <= graph.length; i++) {
                distance[i] = Long.MAX_VALUE;
                path[i] = -1;
            }
            dijkstra(graph, heap, distance, path, start);
            distances[start] = Arrays.stream(distance).boxed()
                    .collect(Collectors.toList());
        }


        Hotel min = null;
        for (int i = 1; i <= graph.length; i++) { // go through each city/node
            if (distances[start].get(i) != Long.MAX_VALUE && hotels[i].peek() != null) {
                long goCost = gas * distances[start].get(i);
                Hotel cheapest = hotels[i].peek();
                long totalCost = goCost + cheapest.cost;
                if (min == null || (totalCost < min.cost) || (min.cost == totalCost &&  cheapest.id < min.id)) {
                    min = new Hotel(cheapest.name, totalCost, cheapest.id);
                }
            }
        }

        if (min == null) {
            System.out.println("XXX");
        } else {
            System.out.println(min.name + " " + min.cost);
        }

    }

    private static void dijkstra(List<Node>[] graph, PriorityQueue<Node> heap, long [] distance, int [] path, int start) {
        heap.add(new Node(start, 0L));//start point so weight is 0
        distance[start] = 0;

        while (!heap.isEmpty()) {
            Node top = heap.remove();
            int currentNodeId = top.id;
            long weight = top.dist;
            if (distance[currentNodeId] != weight) {
                continue;
            }
            for (Node neighbor : graph[currentNodeId]) {
                if (weight + neighbor.dist < distance[neighbor.id]) {
                    distance[neighbor.id] = weight + neighbor.dist;
                    heap.add(new Node(neighbor.id, distance[neighbor.id]));
                    path[neighbor.id] = currentNodeId;
                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        private Integer id;
        private Long dist;

        public Node(int id, Long dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist.compareTo(o.dist);
        }
    }

    static class Hotel implements Comparable<Hotel> {
        private int id;
        private String name;
        private Long cost;

        public Hotel(String name, long cost, int id) {
            this.id = id;
            this.name = name;
            this.cost = cost;
        }

        @Override
        public int compareTo(Hotel o) {
            return this.cost.compareTo(o.cost);
        }
    }
}
