package com.company;

import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfPasswords = input.nextInt();
        int numOfFailures = input.nextInt();

        input.nextLine();
        String [] passwords = new String[numOfPasswords];
        for (int i = 0; i < numOfPasswords; i++) {
            passwords[i] = input.nextLine().trim();
        }
        String realPassword = input.nextLine();

        int passwordsLessThanReal = 0;
        int passwordsEqualReal = 0;

        for (int i = 0; i < numOfPasswords; i++) {
            if (passwords[i].length() < realPassword.length()) {
                passwordsLessThanReal++;
            } else if (passwords[i].length() == realPassword.length()) {
                passwordsEqualReal++;
            }
        }

        int bestCaseOrder = passwordsLessThanReal + 1;
        int worstCaseOrder = passwordsLessThanReal + passwordsEqualReal;
        System.out.println();
        System.out.println(calculateSeconds(bestCaseOrder, numOfFailures) + " " + calculateSeconds(worstCaseOrder, numOfFailures));
    }

    private static int calculateSeconds(int order, int k) {
        if (order % k == 0) {
            return order + ((order/k)  - 1) * 5;
        } else {
            return order + (order/k) * 5;
        }
    }
}
