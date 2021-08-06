package com.company.blue.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class RoyAndTrendingTopic {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfTopics = input.nextInt();
        PriorityQueue<Topic> maxHeap = new PriorityQueue<>();
        for (int i = 0; i < numberOfTopics; i++) {
            int topicId = input.nextInt();
            int currentScore = input.nextInt();
            int post = input.nextInt() * 50;
            int like = input.nextInt() * 5;
            int comment = input.nextInt() * 10;
            int share = input.nextInt() * 20;
            int newScore = post + like + comment + share;
            int changeScore = newScore - currentScore;
            maxHeap.add(new Topic(topicId, changeScore, newScore));
        }
        input.close();

        int count = 0;
        while (!maxHeap.isEmpty() && count < 5) {
            System.out.println(maxHeap.remove());
            count++;
        }
    }

    static class Topic implements Comparable<Topic>{
        int id;
        int changeScore;
        int currentScore;

        public Topic(int id, int changeScore, int currentSore) {
            this.id = id;
            this.changeScore = changeScore;
            this.currentScore = currentSore;
        }
        @Override
        public int compareTo(Topic o) {
            if (this.changeScore == o.changeScore) {
                return o.id - id;
            } else {
                return o.changeScore - changeScore;
            }
        }

        @Override
        public String toString() {
            return id + " " + currentScore;
        }
    }
}
