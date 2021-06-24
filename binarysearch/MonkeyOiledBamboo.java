package com.company.binarysearch;

import java.util.Scanner;

public class MonkeyOiledBamboo {
    static int [] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfCases = input.nextInt();
        int count = 0;
        while (--numberOfCases >= 0) {
            int size = input.nextInt();
            array = new int [size];
            for (int i = 0; i < size; i++) {
                array[i] = input.nextInt();
            }
            System.out.println("Case " + ++count + ": " + binarySearch());
        }
    }

    private static int binarySearch() {
        int left = 1;
        int right = array[array.length - 1];
        int ans = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean flag = false;
            int tempMid = mid;
            for (int i = 1; i < array.length; i++) {
                if (array[i] - array[i-1] > tempMid) {
                    flag = true;
                    break;
                } else if (array[i] - array[i-1] == tempMid) {
                    --tempMid;
                }
            }

            if (flag) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}
