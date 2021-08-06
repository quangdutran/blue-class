package com.company.blue.array;

import java.util.HashMap;
import java.util.Scanner;

public class Museum {

    private static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
//        - array of alphabet characters in circle
//                - starting from a
//        - Jump to the next char backward or forward with min steps
//
//                - Only characters lower
//        - length of word reater than zero and no more than 100
//                - what if 2 same letters next to each other?
//
//
//        - Compare the distance between the current character and the next going forward and backward
//                - Choose the min route
//
//        Pseudo code:
//
//        if current char = next char
//                result = 2
//
//        if num of next - current num <= 13
//          result = next - current num
//        else
//          result = 26 - next num + current num
        int count = 0;
        for (int i = 97; i < 123; i++) {
            map.put((char)i, ++count);
        }

        Scanner input = new Scanner(System.in);
        String word = input.nextLine();

        int result = 0;
        if (word.charAt(0) != (char)97) {
            result = result + count((char)97, word.charAt(0));
        }

        int wordLength = word.length();
        if (wordLength > 1) {
            for (int i = 0; i < wordLength - 1; i++) {
                result += count(word.charAt(i), word.charAt(i+1));
            }
        }
        System.out.println(result);
    }

    private static int count(char current, char next) {
        if (current == next) {
            return 0;
        }
        int nextVal = map.get(next);
        int currentVal = map.get(current);
        int back = countBackward(currentVal, nextVal);
        int forward = countForward(currentVal, nextVal);
        return back > forward ? forward : back;
    }

    private static int countForward(int currentVal, int nextVal) {
        return (nextVal <= 26 && nextVal > currentVal) ? nextVal - currentVal : 26 - currentVal + nextVal;
    }

    private static int countBackward(int currentVal, int nextVal) {
        return (nextVal < currentVal && nextVal >= 1) ? currentVal- nextVal : 26 - nextVal + currentVal;
    }
}
