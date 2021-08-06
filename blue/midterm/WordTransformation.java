package com.company.blue.midterm;

import java.util.*;

public class WordTransformation {
    static List<Integer>[] neighborList = new List[201];
    static List<String>[] dictionary = new List[11];
    static Map<String, Integer> wordMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        input.nextLine();
        while (input.hasNextLine()) {
            refresh();
            List<String> pairs = new ArrayList<>();
            
            int countIndex = 0;
            while (true) {
                String word = input.nextLine();
                if (word.equals("*")) {
                    break;
                }
                dictionary[word.length()].add(word);
                countIndex++;
                wordMap.put(word, countIndex);
            }

            while (input.hasNextLine()) {
                String pair = input.nextLine();
                if (pair.isEmpty()) {
                    break;
                } else {
                    pairs.add(pair);
                }
            }

            //Find neighbor of all words
            for (List<String> strings : dictionary) {
                findNeighbor(strings);
            }

            for (String pair : pairs) {
                String[] p = pair.split(" ");
                String firstWord = p[0];
                String secondWord = p[1];
                System.out.println(firstWord + " " +  secondWord + " " + bfs(firstWord, secondWord));
            }
            refresh();
        }

    }

    private static int bfs(String first, String second) {
        int firstIndex = getIndexForWord(first);
        int secondIndex = getIndexForWord(second);

        LinkedList<Integer> queue = new LinkedList<>();
        boolean [] visited = new boolean[201];
        int [] distance = new int[201];

        queue.add(firstIndex);
        visited[firstIndex] = true;

        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (Integer neighbor : neighborList[current]) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    distance[neighbor] = distance[current] + 1;
                    visited[neighbor] = true;

                    if (neighbor.intValue() == secondIndex) {
                        return distance[secondIndex];
                    }
                }
            }
        }
        return distance[secondIndex];
    }

    private static void refresh() {
        for (int i = 0; i < 201; i++) {
            neighborList[i] = new ArrayList<>();
        }
        wordMap = new HashMap<>();

        for (int i = 0; i < 11; i++) {
            dictionary[i] = new ArrayList<>();
        }
    }

    private static void findNeighbor(List<String> listSameLength) {
        for (String s : listSameLength) {
            for (String s1 : listSameLength) {
                if (isNeighbor(s, s1)) {
                    neighborList[wordMap.get(s)].add(wordMap.get(s1));
                }
            }
        }
    }

    private static int getIndexForWord(String word) {
        return wordMap.get(word);
    }

    private static boolean isNeighbor(String first, String second) {
        if (first.equals(second)) {
            return false;
        }
        boolean ans = false;

        //O(n) with n = 10 max
        for (int i = 0; i < first.length(); i++) {
            boolean firstHalf = true;
            boolean secondHalf = true;
            for (int j = 0; j < i; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    firstHalf = false;
                    break;
                }
            }
            for (int j = i + 1; j < first.length(); j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    secondHalf = false;
                    break;
                }
            }
            if (firstHalf && secondHalf) {
                ans = true;
            }
        }
        return ans;
    }
}
