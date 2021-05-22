package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ThrowingCardsAway {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> cards = new ArrayList<>();

        while (true) {
            int size = input.nextInt();
            if (size == 0) {
                break;
            }
            cards.add(size);
        }
        input.close();

        for (Integer card : cards) {
            LinkedList<Integer> array = new LinkedList<>();
            for (int i = 0; i < card; i++) {
                array.add(i + 1);
            }
            process(array);
        }
    }

    private static void process(LinkedList<Integer> input) {
        String discarded = "";
        while (input.size() > 1) {
            discarded += input.removeFirst() + ", ";
            input.addLast(input.removeFirst());
        }
        discarded = discarded.trim();
        if (!discarded.isEmpty()) {
            discarded = discarded.substring(0,discarded.length() - 1);
        }
        System.out.println("Discarded cards: " + discarded);
        System.out.println("Remaining card: " + input.getFirst());
    }
}
