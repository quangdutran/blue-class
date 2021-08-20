package com.company.alg.orange.divideconquer;

import java.util.Scanner;


/**
 * Idea: There is always a one horizontal stroke at the bottom. Taking that as one stroke, then divide the fences into two parts
 * based on the shortest fence, and repeat the same steps (divide and conquer)
 */
public class PaintingFences {

    static int [] array;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        array = new int [5007];
        for (int i = 1; i <= length; i++) {
            array[i] = input.nextInt();
        }
        System.out.println(minStroke(1, length, 0));

    }

    private static int minStroke(int left, int right, int base) {
        if (left == right) {  //When there is only one fence left, there is one stroke needed
            return 1;
        }

        int high = (int) 2e9;
        for (int i = left; i <= right; i++) {
            high = Math.min(high, array[i]);  //Find the shortest fence of all
        }
        int ans = high - base;                //Minus the previous stroke height (start at 0)
        for (int i = left; i <= right; i++) { // Go through all fences
            if (array[i] != high) {           // If fence is higher than the shortest one, this fence is the start
                int j = 0;
                for (j = i; j <= right; j++) {      // go through all fences
                    if ( j == right ) break;        //break out the loop when all fences are looped (all fences are higher than the shortest one
                    if ( array[j+1] == high ) break; // or find the one that higher while loop has not finished
                }
                ans += minStroke(i, j, high);       //Now recursive call with the start fence and the end fence above
                i = j + 1;                          //this is the next index of the next set of fences higher than the shortest one
            }
        }
        return  Math.min(ans, right - left + 1);  //Take the min between strokes computed and number of fences (equal length) in this case
    }
}
