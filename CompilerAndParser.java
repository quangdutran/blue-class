package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CompilerAndParser {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfQueries = input.nextInt();
        input.nextLine();
        List<String> expressions = new ArrayList<>();
        for (int i = 0; i < numberOfQueries; i++) {
            expressions.add(input.nextLine());
        }
        input.close();

        for (String expression : expressions) {
            check(expression);
        }

    }

    private static void check(String expression) {
        Stack<Character> stack = new Stack<>();
        if (expression.length() == 1) {
            System.out.println(0);
            return;
        }

        int ans = 0;
        for(int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '<') {
                stack.push('<');
            } else if (stack.isEmpty()) {
                break;
            } else {
                stack.pop();
                ans = stack.isEmpty() ? i + 1 : ans;
            }
        }
        System.out.println(ans);
    }
}
