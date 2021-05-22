package com.company;

import java.util.*;

public class StreetParade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<List<Integer>> cases = new ArrayList<>();

        while (true) {
            int size = input.nextInt();
            if (size == 0) {
                break;
            }
            List<Integer> testCase = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                testCase.add(input.nextInt());
            }
            cases.add(testCase);
        }
        input.close();

        for (List<Integer> aCase : cases) {
            checkCase((LinkedList<Integer>) aCase);
        }
    }

    private static void checkCase(LinkedList<Integer> testCase) {
        if (testCase.size() < 3) {
            System.out.println("yes");
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int need = 1;
        List<Integer> result = new ArrayList<>();
        int originalSize = testCase.size();


        while (!testCase.isEmpty()) {
            if (testCase.getFirst() == need) {
                result.add(testCase.removeFirst());
                need++;
            } else if (!stack.isEmpty() && stack.peek() == need) {
                result.add(stack.pop());
                need++;
            } else {
                stack.push(testCase.removeFirst());
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        if (result.size() == originalSize && isSortedIncreasing(result)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    private static boolean isSortedIncreasing(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) + 1 != list.get(i+1)) {
                return false;
            }
        }
        return true;
    }

}
