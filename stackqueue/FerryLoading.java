package com.company.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FerryLoading {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        for (int i = 0; i < numberOfCases; i++) {
            int capacity = input.nextInt();
            int timeRunning = input.nextInt();
            Queue<Car> leftSide = new LinkedList<>();
            Queue<Car> rightSide = new LinkedList<>();

            int cars = input.nextInt();
            input.nextLine();
            for (int j = 0; j < cars; j++) {
                String line = input.nextLine();
                String [] split = line.split(" ");
                if (split[1].equals("right")) {
                    rightSide.add(new Car(j, Integer.parseInt(split[0]), split[1]));
                } else {
                    leftSide.add(new Car(j, Integer.parseInt(split[0]), split[1]));
                }
            }
            processCars(capacity, timeRunning, leftSide, rightSide);
            if (i != numberOfCases -1)
                System.out.println();
        }
    }

    private static void processCars(int capacity, int timeRunning, Queue<Car> leftSide, Queue<Car> rightSide) {
        boolean curSide = true;
        int currentTime = 0;
        int [] ans = new int [rightSide.size() + leftSide.size()];

        while (!leftSide.isEmpty() || !rightSide.isEmpty()) {
            int nextTime = 0;
            if (!rightSide.isEmpty() && leftSide.isEmpty()) {
                nextTime = rightSide.peek().time;
            } else if (rightSide.isEmpty() && !leftSide.isEmpty()) {
                nextTime = leftSide.peek().time;
            } else {
                nextTime = Math.min(leftSide.peek().time, rightSide.peek().time);
            }

            currentTime = Math.max(currentTime, nextTime);
            int count = 0;

            if (curSide) {
                while (!leftSide.isEmpty()) {
                    Car front = leftSide.peek();
                    if (front.time <= currentTime && count < capacity) {
                        count++;
                        ans[front.id] = (currentTime + timeRunning);
                        leftSide.poll();

                    } else {
                        break;
                    }
                }
            } else {
                while (!rightSide.isEmpty()) {
                    Car front = rightSide.peek();
                    if (front.time <= currentTime && count < capacity) {
                        ans[front.id] = (currentTime + timeRunning);
                        count++;
                        rightSide.poll();
                    } else {
                        break;
                    }
                }
            }
            currentTime += timeRunning;
            curSide = !curSide;
        }

        for (int an : ans) {
            System.out.println(an);
        }
    }

    public static class Car {
        private int id;
        private int time;
        private String side;

        private Car(int id, int time, String side) {
            this.id = id;
            this.time = time;
            this.side = side;
        }
    }
}
