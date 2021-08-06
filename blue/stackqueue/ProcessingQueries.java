package com.company.blue.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProcessingQueries {
    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        int totalQueries = sc.nextInt();
        int queueSize = sc.nextInt();
        int [] timeCome = new int [totalQueries];
        int [] processTime = new int [totalQueries];
        for (int i = 0; i < totalQueries; i++) {
            timeCome[i] = sc.nextInt();
            processTime[i] = sc.nextInt();
        }
        sc.close();

        Queue<Long> processTimeQueue = new LinkedList<>();
        long processingTime = 0;

        for (int i = 0; i < totalQueries; i++) {
            while(!processTimeQueue.isEmpty() && processTimeQueue.peek() <= timeCome[i]) {
                processTimeQueue.remove();
            }

            if (processTimeQueue.size() <= queueSize) {
                processingTime = Math.max(processingTime, timeCome[i]) + processTime[i];
                System.out.println(processingTime);
                processTimeQueue.add(processingTime);
            } else {
                System.out.println(-1);
            }
        }
    }
}
