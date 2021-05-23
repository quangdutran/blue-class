package com.company.sorting;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Devu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfSubject = input.nextInt();
        int devuAbility = input.nextInt();

        int [] chapters = new int[numberOfSubject];
        for (int i = 0; i < numberOfSubject; i++) {
            chapters[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(chapters);
        BigInteger time = BigInteger.valueOf(0);
        if (devuAbility < 1) {
            devuAbility = 1;
        }
        for (int i = 0; i < numberOfSubject; i++) {
            BigInteger multiply = BigInteger.valueOf(chapters[i]).multiply(BigInteger.valueOf(devuAbility));
            time = time.add(multiply);
            if (devuAbility > 1) {
                devuAbility--;
            }
        }
        System.out.println(time.toString());
    }
}
