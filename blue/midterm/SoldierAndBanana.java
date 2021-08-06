package com.company.blue.midterm;

import java.math.BigInteger;
import java.util.Scanner;

public class SoldierAndBanana {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        int totalMoney = input.nextInt();
        int w = input.nextInt();
        input.close();
        BigInteger total = getSum(BigInteger.valueOf(w)).multiply(BigInteger.valueOf(k));
        BigInteger ans = total.subtract(BigInteger.valueOf(totalMoney));
        System.out.println(BigInteger.ZERO.compareTo(ans) > 0 ? 0 : ans);
    }

    private static BigInteger getSum(BigInteger i) {
        if (BigInteger.ZERO.equals(i)) {
            return BigInteger.ZERO;
        }
        return i.add(getSum(i.subtract(BigInteger.ONE)));
    }
}
