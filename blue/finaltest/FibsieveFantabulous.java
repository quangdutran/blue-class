package com.company.blue.finaltest;

import java.util.Scanner;

/**
 * The column 1 9 25 where x=1 is the square of an odd number and the square root is the column number of this number.
 * In the row where y=1, 1 4 16 is the square of an even number, and the square root is the number of rows of this number.
 * Observe the number of diagonals, 1 3 7 13 21... Let the number of columns be a. The number of diagonals in each column is equal to a*(a-1)+1.
 *
 * Now we will give the square root of n and define it as x,
 *
 * 1.x*x=n: If x is an odd number, its coordinates must be (1,x), otherwise (x,1).
 *
 * 2.x*x!=n: Because we get integers when extracting squares, the calculated row or column is the previous row or column,
 * so the row or column of the actual number is x+1, as for Whether x+1 is the number of rows or columns, we are going to discuss the parity of x.
 * We define the value of the diagonal as y, y=(x+1)*x+1.
 *
 * When x+1 is an odd number: (1) When y>n, x+1 is the number of columns, and the number of rows is x+1 minus the distance from y to n: (x+1-(n-y), x+1);
 *
 * (2) When y<n, x+1 is the number of rows, and the number of columns is the same as when y>n: (x+1, x+1-(y-n));
 *
 * When x+1 is even: The discussion method is the same as when x+1 is odd.
 */
public class FibsieveFantabulous {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        int count = 0;
        while (--cases >= 0) {
            long col = -1;
            long row = -1;
            long value = input.nextLong();
            long x = (long) Math.sqrt((double) value);
            if (x * x == value) {
                if (x % 2 == 0) {
                    col = x;
                    row = 1;
                } else {
                    col = 1;
                    row = x;
                }
            } else {
                long y = (x +1) * x + 1;
                if ((x+1) % 2 != 0) {
                    if (y > value) {
                        col = x + 1;
                        row = x + 1 - (y -value);
                    } else {
                        col = x + 1 - (value - y);
                        row = x + 1;
                    }
                } else {
                    if (y > value) {
                        col = x + 1 - (y - value);
                        row = x + 1 ;
                    } else {
                        col = x + 1 ;
                        row = x + 1 - (value - y);
                    }
                }
            }
            System.out.println("Case " + ++count + ": " + col + " " + row);
        }
    }
}
