package com.company.alg.orange.bitmanipulation;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HackerEarth
 */
public class SamuAndHerBirthdayParty {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        while(--cases >=0 ) {
            int n = input.nextInt();
            int k = input.nextInt();
            input.nextLine();
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String line = input.nextLine();
                numbers.add(Integer.parseInt(line, 2));  //Complexity?
            }

        }
    }
}
