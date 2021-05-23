package com.company.alg.complexity;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Vests {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfSoldiers = input.nextInt();
        int numberOfVest = input.nextInt();
        int lowerBound = input.nextInt();
        int upperBound = input.nextInt();
        int [] solderSize = new int[numberOfSoldiers];
        int [] vestSize = new int[numberOfVest];
        for (int i = 0; i < numberOfSoldiers; i++) {
            solderSize[i] = input.nextInt();
        }
        for (int i = 0; i < numberOfVest; i++) {
            vestSize[i] = input.nextInt();
        }
        input.close();

        Map<Integer, Integer> map = new HashMap<>();
        int initialIndex = 0;
//        for (int i = 0; i < numberOfSoldiers; i++) {
//            int lowerBoundVal = solderSize[i] - lowerBound;
//            int upperBoundVal = solderSize[i] + upperBound;
//            for (int j = initialIndex; j < numberOfVest; j++) {
//                if (lowerBoundVal <= vestSize[j] && vestSize[j] <= upperBoundVal) {
//                    map.put(i+ 1,j+1);
//                    initialIndex = j+1;
//                    break;
//                } else if (vestSize[j] < lowerBoundVal) {
//                    initialIndex = j+1;
//                    break;
//                } else {
//                    break;
//                }
//            }
//            if (map.size() >= numberOfVest) {
//                break;
//            }
//        }

        for (int i = 0, j = 0; i < numberOfSoldiers && j < numberOfVest;) {
            if (vestSize[j] < solderSize[i] - lowerBound) {
                ++j;
            } else if (vestSize[j] > solderSize[i] + upperBound) {
                ++i;
            } else {
                map.put(++i, ++j);
            }
        }

        System.out.println(map.size());
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }


    }
}
