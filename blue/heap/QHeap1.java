package com.company.blue.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class QHeap1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfQueries = input.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        input.nextLine();
        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfQueries; i++) {
            int choice = input.nextInt();
            if (choice == 1) {
                Integer num = input.nextInt();
                queue.add(num);
            } else if (choice == 2) {
                Integer num = input.nextInt();
                queue.remove(num);
            } else {
                if (!queue.isEmpty()) {
                    System.out.println(queue.peek());
                } else {
                    System.out.println(" ");
                }
            }
        }
        input.close();

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public static void main_(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        int[][] arr = new int[N][2];

        for(int i = 0; i< N; i++){
            String line = scan.nextLine();
            String[] query = line.split(" ");

            arr[i][0] = Integer.parseInt(query[0]);
            if(query.length ==2){
                arr[i][1] = Integer.parseInt(query[1]);
            }
        }
        long startTime = System.nanoTime();

        heapMethods(arr);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public static void heapMethods(int[][] arr){
        int N = arr.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for(int i = 0; i<N; i++){
            if(arr[i][0] == 1){
                minHeap.add((arr[i][1]));
            } else if(arr[i][0] == 2){
                minHeap.remove((arr[i][1]));
            } else{
                System.out.println(minHeap.peek());
            }
        }
    }


}
