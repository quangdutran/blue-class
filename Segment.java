package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Segment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOFSegments = input.nextInt();
        List<BigSegment> list = new ArrayList<>();
        for (int i = 0; i < numberOFSegments; i++) {
            list.add(new BigSegment(input.nextInt(), input.nextInt()));
        }
        input.close();



        int result = -1;
        if (numberOFSegments == 1) {
            result = 1;
        } else {
            int min = 100000;
            int max = 1;
            int pos = -1;
            for (int i = 0; i < numberOFSegments; i++) {
                BigSegment b = list.get(i);
                if (b.firstNum <= min && b.secondNum >= max) {
                    pos = i;
                }
                if (b.firstNum < min) {
                    min = b.firstNum;
                }
                if (b.secondNum > max) {
                    max = b.secondNum;
                }
            }
            if (pos != -1 && list.get(pos).firstNum <= min && list.get(pos).secondNum >= max) {
                result = pos;
            }
        }
        System.out.println(result);
    }

    public static class BigSegment {
        int firstNum;
        int secondNum;

        public BigSegment(int firstNum, int secondNum) {
            this.firstNum = firstNum;
            this.secondNum = secondNum;
        }
    }
}
