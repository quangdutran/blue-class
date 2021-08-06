package com.company.alg.orange.bitmanipulation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatteyMultiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++) {
            String [] twoNumbers = input.nextLine().split(" ");
            BigInteger first = new BigInteger(twoNumbers[0]);
            BigInteger second = new BigInteger(twoNumbers[1]);
            System.out.println(calculate(first, second));
        }
    }

    private static String calculate(BigInteger n, BigInteger m) {
        List<Long> list = new ArrayList<>();
        long count = 0;
        BigInteger two = BigInteger.valueOf(2);
        while (m.compareTo(BigInteger.ZERO) > 0)
        {
            if (m.remainder(two).equals(BigInteger.ONE)) {
                list.add(count);
            }
            count++;
            m = m.divide(two);
        }

        Collections.reverse(list);
        return list.stream().map(num -> "("+ n + "<<" + num + ")").collect(Collectors.joining(" + "));
    }
}
