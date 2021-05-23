package com.company.stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class TransformExpressioin {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String [] expressions = new String[n];
        for (int i = 0; i < n; i++) {
            expressions[i] = input.nextLine();
        }
        input.close();

        for (String expression : expressions) {
            System.out.println(convert(expression));
        }
    }

    private static boolean isOperator(char in) {
        return in == 42 || in == 43 || in == 45 || in == 47 || in == 94;
    }

    private static String convert(String expression) {
        String result = "";
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetter(expression.charAt(i))) {
                result += expression.charAt(i);
            } else if (isOperator(expression.charAt(i))) {
                stack.push(expression.charAt(i));
            } else if (expression.charAt(i) == 41) {
                result += stack.pop();
            }
        }
        return result;
    }
}
