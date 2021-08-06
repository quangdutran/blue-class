package com.company.blue.array;

import java.util.Scanner;

public class Vitaly {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        String firstWord = input.nextLine();
//        String secondWord = input.nextLine();
//        input.close();
//
//        for (int i = 0; i < firstWord.length(); i++) {
//            if ((int)secondWord.charAt(i) - (int)firstWord.charAt(i) > 1) {
//                char middleChar = (char) ((int)firstWord.charAt(i) + 1);
//                System.out.println(firstWord.substring(0, i) + middleChar + firstWord.substring(i + 1));
//                return;
//            }
//        }
//        System.out.println("No such string");
//    }

    static String lexNext(String str, int n) {
        char[] s = str.toCharArray();
        // Iterate from last character
        for (int i = n - 1; i >= 0; i--) {
            // If not 'z', increase by one
            if (s[i] != 'z') {
                s[i]++;
                return String.valueOf(s);
            }

            // if 'z', change it to 'a'
            s[i] = 'a';
        }
        return null;
    }

    // Driver Code
    static public void main(String[] args) {
        String S = "klz", T = "knb";
        int n = S.length();
        String res = lexNext(S, n);

        // If not equal, print the
        // resultant string
        if (res != T) {
            System.out.println(res);
        } else {
            System.out.println("-1");
        }
    }
}
