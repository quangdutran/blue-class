package com.company.blueclass.alg.orange.dynamicprogramming3;

import java.util.*;

public class WavioSequence {
    private static ArrayList<Integer> dp;

    private static int lowerBound_LTE(int [] array, List<Integer> dp, int length, int compareNumber) {
        int right = length;
        int left = 0;
        int pos = length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[dp.get(mid)] >= compareNumber) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int lowerBound(List<Integer> array, int val) {
        int left = 0;
        int right = array.size();
        int pos = array.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (val <= array.get(mid)) {
                pos = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    private static int lisOptimized_LTE(int [] array) {
        dp = new ArrayList<>();
        dp.add(0);
        int dpLength = 1;
        int lengthArray = array.length;
        for (int i = 1; i < lengthArray; i++) {
            if (array[i] <= array[dp.get(0)]) {
                dp.set(0,i);
            } else if (array[i] > array[dp.get(dpLength - 1)]) {
                dpLength++;
                dp.add(i);
            } else {
                int lowerBoundIndex = lowerBound_LTE(array, dp, dpLength, array[i]);
                dp.set(lowerBoundIndex, i);
            }
        }
        return dpLength;
    }

    private static void lisInOneLoop(int [] array, int [] increaseDecreaseArray) {
        int arrayLength = array.length;
        List<Integer> dp_prime = new ArrayList<>();
        dp_prime.add(array[0]);
        for (int i = 1; i < arrayLength; i++) {
            int pos = lowerBound(dp_prime, array[i]);
            if (pos == dp_prime.size()) {
                dp_prime.add(array[i]);
            } else {
                dp_prime.set(pos, array[i]);
            }
            increaseDecreaseArray[i] = pos + 1;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true)
            try {
                n = sc.nextInt();
                int[] array = new int[n];
                int[] ascendingLength = new int[n];
                int[] descendingLength = new int[n];
                for (int i = 0; i < n; i++)
                    array[i] = sc.nextInt();
                lisInOneLoop(array, ascendingLength);

                int reverse[] = new int[n];
                int count = 0;
                for (int i = n - 1; i >= 0; i--) {
                    reverse[count] = array[i];
                    count++;
                }

                lisInOneLoop(reverse, descendingLength);
                int maxLength = 1;
                for (int i = 0; i < n; i++) {
                    int minLen = Math.min(ascendingLength[i], descendingLength[n - i - 1]);
                    maxLength = Math.max(maxLength, minLen * 2 - 1);
                }
                System.out.println(maxLength);
            } catch (Exception e) {
                sc.close();
                System.exit(0);
            }
    }

    public static void main_LTE(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                int length = input.nextInt();
                int array[] = new int[length];
                for (int i = 0; i < length; i++) {
                    array[i] = input.nextInt();
                }

                int [] increasing = new int [length];
                int [] decreasing = new int [length];

                increasing[0] = 1;
                decreasing[0] = 1;
                for (int i = 1; i < length; i++) {
                    increasing[i] = lisOptimized_LTE(Arrays.copyOfRange(array, 0, i + 1));
                }


                int reverse[] = new int[length];
                int count = 0;
                for (int i = length - 1; i >= 0; i--) {
                    reverse[count] = array[i];
                    count++;
                }

                for (int i = 1; i < length; i++) {
                    decreasing[i] = lisOptimized_LTE(Arrays.copyOfRange(reverse, 0, i + 1));
                }

                int max = 1;
                for (int i = 0; i < length; i++) {
                    int minLength = Math.min(increasing[i], decreasing[length-i-1]);
                    max = Math.max(max, minLength * 2 -1);
                }
                System.out.println(max);
            } catch (Exception e) {
                input.close();
                System.exit(0);
            }
        }
    }
}
