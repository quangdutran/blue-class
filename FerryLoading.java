package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class FerryLoading {
    public static void main(String[] args) {

        Queue<Car> leftSide = new LinkedList<>();
        Queue<Car> rightSide = new LinkedList<>();

        leftSide.add(new Car(25, "left"));
        leftSide.add(new Car(40, "left"));
        rightSide.add(new Car(10, "right"));

        int time = 0;
        boolean ferryAtLeft = true;


    }

    public static class Car {
        private int time;
        private String side;

        private Car(int time, String side) {
            this.time = time;
            this.side = side;
        }
    }
}
