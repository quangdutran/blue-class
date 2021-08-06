package com.company.blue.midterm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int testCase = input.nextInt();
        for (int i = 0; i < testCase; i++) {
            int numberOfJob = input.nextInt();
            int yourJobIndex = input.nextInt();
            LinkedList<Job> queue = new LinkedList<>();
            PriorityQueue<Job> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            for (int j = 0; j < numberOfJob; j++) {
                Job job = new Job(input.nextInt(), j == yourJobIndex);
                queue.add(job);
                maxHeap.add(job);
            }

            int count  = 0;
            while (true) {
                Job firstInQueue = queue.peekFirst();
                if (maxHeap.peek().priority > firstInQueue.priority) {
                    queue.add(queue.removeFirst());
                } else if (maxHeap.peek().priority == firstInQueue.priority) {
                    maxHeap.remove();
                    Job job = queue.remove();
                    count++;
                    if (job.yourJob) {
                        break;
                    }
                } else {
                    //not supposed to happen since queue and maxHeap are synced
                }
            }

            System.out.println(count);
        }
    }

    static class Job implements Comparable<Job> {
        int priority;
        boolean yourJob;

        public Job(int priority, boolean yourJob) {
            this.priority = priority;
            this.yourJob = yourJob;
        }

        @Override
        public int compareTo(Job o) {
            return this.priority - o.priority;
        }
    }
}
