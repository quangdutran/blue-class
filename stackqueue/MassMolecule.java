package com.company.stackqueue;

import java.util.Scanner;
import java.util.Stack;

public class MassMolecule {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String formula = input.nextLine();
        input.close();

        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for(int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '(') {
                values.push(-1);
            }
            else if (formula.charAt(i) == ')') {
                while (true) {
                    int first = values.pop();
                    int second = values.peek() != -1 ? values.pop() : 0;
                    if (values.peek() == -1 ) {
                        values.pop();
                        values.push(first + second);
                        break;
                    }
                    values.push(first + second);
                }
            } else if (Character.isDigit(formula.charAt(i))) {
                if (Character.isLetter(formula.charAt(i-1))) {
                    values.push(values.pop() * Character.getNumericValue(formula.charAt(i)));
                } else {
                    values.push(Character.getNumericValue(formula.charAt(i)) * values.pop());
                }
            } else if (Character.isLetter(formula.charAt(i))) {
                values.push(getValue(formula.charAt(i)));
            }
        }

        while (values.size() > 1) {
            values.push(values.pop() + values.pop());
        }

        System.out.println(values.peek().intValue());
    }

    private static int getValue(char i) {
        if (i == 'C') {
            return 12;
        } else if (i == 'H') {
            return 1;
        } else if (i == 'O') {
            return 16;
        } else return 0;
    }
}
