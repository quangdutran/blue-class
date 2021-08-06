package com.company.alg.orange.backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class TheBoggleGame {
    static char[][] board1;
    static char[][] board2;
    static boolean[][] visited;
    static List<List<RowCol>> listNodeWord;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                continue;
            } else if (line.equals("#")) {
                return;
            }
            board1 = new char[4][4];
            board2 = new char[4][4];

            String[] lines = line.split("    ");
            for (int i = 0; i < 4; i++) {
                board1[0][i] = lines[0].replace(" ", "").charAt(i);
                board2[0][i] = lines[1].replace(" ", "").charAt(i);
            }
            for (int j = 0; j < 3; j++) {
                String nextLine = input.nextLine();
                String[] nextLines = nextLine.split("    ");
                for (int i = 0; i < 4; i++) {
                    board1[j + 1][i] = nextLines[0].replace(" ", "").charAt(i);
                    board2[j + 1][i] = nextLines[1].replace(" ", "").charAt(i);
                }
            }
            listNodeWord = new ArrayList<>();
            List<String> result = checkBothBoards(board1, board2);
            if (result.isEmpty()) {
                System.out.println("There are no common words for this pair of boggle boards.");
            } else {
                for (String s : result) {
                    System.out.println(s);
                }
            }
            System.out.println();
        }
    }

    private static List<String> checkBothBoards(char[][] board1, char[][] board2) {
        visited = new boolean[4][4];
        countBoggle(board1);
        List<List<RowCol>> board1Set = new ArrayList<>(listNodeWord);

        listNodeWord.clear();
        visited = new boolean[4][4];

        countBoggle(board2);
        List<List<RowCol>> board2Set = new ArrayList<>(listNodeWord);

        Set<String> board1Words = board1Set.stream().map(i -> listRowColToString(i, board1)).collect(Collectors.toSet());
        Set<String> board2Words = board2Set.stream().map(i -> listRowColToString(i, board2)).collect(Collectors.toSet());

        Set<String> result = new HashSet<>();
        if (board1Words.size() <= board2Words.size()) {
            for (String board1Word : board1Words) {
                if(board2Words.contains(board1Word)) {
                    result.add(board1Word);
                }
            }
        } else {
            for (String board2Word : board2Words) {
                if(board1Words.contains(board2Word)) {
                    result.add(board2Word);
                }
            }
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

    private static String listRowColToString(List<RowCol> input, char [][] board) {
        return input.stream().map(i -> board[i.x][i.y])
                .map(Object::toString).collect(Collectors.joining());
    }

    private static void countBoggle(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                visited[i][j] = true;
                countRecursive(new ArrayList(Arrays.asList(new RowCol(i, j))), maze);
            }
        }
    }

    private static void countRecursive(ArrayList<RowCol> listNode, char[][] board) {
        if (listNode.size() == 4) {
            List<RowCol> clone = listNode.stream().collect(Collectors.toList());
            if (countVowel(clone, board)) {
                listNodeWord.add(clone);
            }
            return;
        }
        RowCol current = listNode.get(listNode.size() - 1);
        for (RowCol neighbor : getValidNeighbors(current)) {
            listNode.add(neighbor);
            visited[neighbor.x][neighbor.y] = true;
            countRecursive(listNode, board);

            RowCol lastNode = listNode.get(listNode.size() - 1);
            visited[lastNode.x][lastNode.y] = false;
            listNode.remove(listNode.size() - 1);
        }
        visited[current.x][current.y] = false;
    }

    private static boolean countVowel(List<RowCol> list, char[][] board) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'Y'));
        int count = 0;
        for (RowCol rowCol : list) {
            if (vowels.contains(board[rowCol.x][rowCol.y])) {
                count++;
            }
        }
        return count == 2;
    }

    private static List<RowCol> getValidNeighbors(RowCol current) {
        List<RowCol> neighbors = new ArrayList<>();
        if (current.x + 1 >= 0 && current.x + 1 < 4
                && !visited[current.x + 1][current.y]) {
            neighbors.add(new RowCol(current.x + 1, current.y));
        }
        if (current.x - 1 >= 0 && current.x - 1 < 4
                && !visited[current.x - 1][current.y]) {
            neighbors.add(new RowCol(current.x - 1, current.y));
        }
        if (current.y + 1 >= 0 && current.y + 1 < 4
                && !visited[current.x][current.y + 1]) {
            neighbors.add(new RowCol(current.x, current.y + 1));
        }
        if (current.y - 1 >= 0 && current.y - 1 < 4
                && !visited[current.x][current.y - 1]) {
            neighbors.add(new RowCol(current.x, current.y - 1));
        }
        //lower left
        if (current.x + 1 >= 0 && current.x + 1 < 4 && current.y - 1 >= 0
                && !visited[current.x + 1][current.y - 1]) {
            neighbors.add(new RowCol(current.x + 1, current.y - 1));
        }
        //upper left
        if (current.x - 1 >= 0 && current.x - 1 < 4 && current.y - 1 >= 0
                && !visited[current.x - 1][current.y - 1]) {
            neighbors.add(new RowCol(current.x - 1, current.y - 1));
        }
        //upper right
        if (current.y + 1 >= 0 && current.y + 1 < 4 && current.x - 1 >= 0
                && !visited[current.x - 1][current.y + 1]) {
            neighbors.add(new RowCol(current.x - 1, current.y + 1));
        }
        //lower right
        if (current.y + 1 >= 0 && current.y + 1 < 4 && current.x + 1 < 4
                && !visited[current.x + 1][current.y + 1]) {
            neighbors.add(new RowCol(current.x + 1, current.y + 1));
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
    }

}
