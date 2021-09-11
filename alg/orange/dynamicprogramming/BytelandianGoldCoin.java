package com.company.alg.orange.dynamicprogramming;


import java.util.HashMap;
import java.util.Scanner;

/**
 * SPOJ: A coin could be exchanged into 3 other coins (n/2, n/3, n/4), exchange could happen like a tree, until 0 coins.
 */
public class BytelandianGoldCoin {
    static int [] dp;
    static HashMap<Integer, Long> resultMap;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                return;
            }
            int total = Integer.parseInt(line);
            resultMap = new HashMap<>();
            resultMap.put(0,0l);
            resultMap.put(1,1l);
            resultMap.put(2,2l);
            System.out.println(getMaxChange(total));
        }
        input.close();
    }

    private static long getMaxChange(int n) {
        if (n <= 2) {
            return resultMap.get(n);
        }
        if (resultMap.containsKey(n)) {
            return resultMap.get(n);
        } else {
            long max = Math.max(n, (getMaxChange(n/2) + getMaxChange(n/3) + getMaxChange(n/4)));
            resultMap.put(n, max);
            return max;
        }
    }
}
