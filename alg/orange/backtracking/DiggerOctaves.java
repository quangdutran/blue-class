package com.company.alg.orange.backtracking;


import java.util.*;
import java.util.stream.Collectors;

public class DiggerOctaves {

    static Set<ListRowCol> setResult;
    static char[][] maze;
    static boolean [][] visited;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >= 0) {
            int size = input.nextInt();
            input.nextLine();
            setResult = new HashSet<>();
            maze = new char[size][size];
            visited = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                String line = input.nextLine();
                for (int j = 0; j < size; j++) {
                     maze[i][j] = line.charAt(j);
                }
            }
            System.out.println(count(maze));
        }
    }

    private static int count(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == 'X') {
                    visited[i][j] = true;
                    countRecursive(new ArrayList(Arrays.asList(new RowCol(i,j))));
                }
            }
        }
        return setResult.size();
    }

    private static void countRecursive(ArrayList<RowCol> listNode) {
        if (listNode.size() == 8) {
            setResult.add(new ListRowCol(listNode.stream().sorted().collect(Collectors.toList())));
            return;
        }
        RowCol current = listNode.get(listNode.size() - 1);
        for (RowCol neighbor : getValidNeighbors(current)) {
            listNode.add(neighbor);
            visited[neighbor.x][neighbor.y] = true;
            countRecursive(listNode);

            RowCol lastNode = listNode.get(listNode.size() - 1);
            visited[lastNode.x][lastNode.y] = false;
            listNode.remove(listNode.size() - 1);
        }
        visited[current.x][current.y] = false;
    }

    private static List<RowCol> getValidNeighbors(RowCol current) {
        List<RowCol> neighbors = new ArrayList<>();
        if (current.x + 1 >= 0 && current.x + 1 < maze.length
                && !visited[current.x+1][current.y] && maze[current.x+1][current.y] != '.') {
            neighbors.add(new RowCol(current.x + 1, current.y));
        }
        if (current.x - 1 >= 0 && current.x - 1 < maze.length
                && !visited[current.x-1][current.y] && maze[current.x-1][current.y] != '.') {
            neighbors.add(new RowCol(current.x - 1, current.y));
        }
        if (current.y + 1 >= 0 && current.y + 1 < maze.length
                && !visited[current.x][current.y+1] && maze[current.x][current.y+1] != '.') {
            neighbors.add(new RowCol(current.x, current.y + 1));
        }
        if (current.y - 1 >= 0 && current.y - 1 < maze.length
                && !visited[current.x][current.y-1] && maze[current.x][current.y-1] != '.') {
            neighbors.add(new RowCol(current.x, current.y - 1));
        }
        return neighbors;
    }

    static class RowCol implements Comparable<RowCol> {
        int x;
        int y;
        public RowCol(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(RowCol o) {
            if (this.x - o.x != 0) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof RowCol) {
                RowCol object = (RowCol) obj;
                return this.x == object.x && this.y == object.y;
            } else return false;
        }

        @Override
        public int hashCode() {
            return Integer.parseInt(this.x + "" + this.y);
        }
    }

    static class ListRowCol {
        private List<RowCol> list;

        public ListRowCol(List<RowCol> list) {
            this.list = list;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ListRowCol) {
                ListRowCol listRowCol = (ListRowCol) obj;
                if (listRowCol.list.size() != this.list.size()) {
                    return false;
                } else {
                    for (int i = 0; i < this.list.size(); i++) {
                        if (!Objects.equals(this.list.get(i), listRowCol.list.get(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            } else return false;
        }

        @Override
        public int hashCode() {
            int result = this.list.stream().map(RowCol::hashCode).reduce(Integer::sum).orElse(0);
            return result;
        }
    }
}
