package com.company.blue.binarysearch;

import java.util.Scanner;

public class PlayBoyChimp {
    static long [] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        array = new long [size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextLong();
        }
        int queries = input.nextInt();
        for (int i = 0; i < queries; i++) {
            long x = input.nextLong();
            int indexLow = getLowerBound(x);
            int indexUp = getUpperBound(x);

            String first;
            if (array[indexLow] >= x) {
                --indexLow;
                first = indexLow < 0 ? "X" : array[indexLow] + "";
            } else {
                first = array[indexLow] + "";
            }
            String second = indexUp == (array.length - 1) && array[indexUp] <= x ? "X" : array[indexUp] + "";
            System.out.print(first + " " + second);
            System.out.println();
        }
    }


    private static int getLowerBound(long x) {
        int pos = array.length - 1;
        int right = array.length - 1;
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= x) {
                right = mid;
                pos = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int getUpperBound(long x) {
        int pos = array.length - 1;
        int right = array.length - 1;
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > x) {
                right = mid;
                pos = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }
}
