package com.company.alg.orange.backtracking;

import java.util.Scanner;
import java.util.stream.LongStream;

public class DreamonAndWifi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line1 = input.nextLine();
        String line2 = input.nextLine();

        int expectPlus = 0;
        int expectMinus = 0;
        int wifiPlus = 0;
        int wifiMinus = 0;
        int question = 0;

        for (int i = 0; i < line1.length(); i++) {
            if (line1.charAt(i) == '+') {
                expectPlus++;
            } else {
                expectMinus++;
            }

            if (line2.charAt(i) == '+') {
                wifiPlus++;
            } else if (line2.charAt(i) == '-') {
                wifiMinus++;
            } else {
                question++;
            }
        }


        int gap = expectPlus - wifiPlus;

        if (question == 0) {
            System.out.printf("%.10f", (gap == 0) ? 1.0 : 0.0);
        } else if (gap > question || gap < 0) {
            System.out.printf("%.10f", 0.0);
        } else {
            double division = factorialUsingStreams(question) / factorialUsingStreams(gap) / factorialUsingStreams(question - gap);
            double probability = Math.pow(2, question);
            division /= probability;
            System.out.printf("%.10f", division);
        }
    }

    public static long factorialUsingStreams(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }
}
