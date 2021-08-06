package com.company.blue.midterm;

import java.util.Scanner;

public class Panagram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        input.nextLine();
        String word = input.nextLine();
        input.close();

        int [] count = new int [26];
        // A 0, B 1, C 2, D 3, E 4, F 5, G 6, H 7, I 8, J 9, K 10, L 11, M 12, N 13, O 14
        // , P15, Q16, R17, S 18, T 19, U 20, V 21, W 22, X 23, Y24, Z25

        char [] charArray = new char [length];
        for (int i = 0; i < length; i++) {
            charArray[i] = word.charAt(i);
            if (charArray[i] == 'a' || charArray[i] == 'A') {
                count[0]++;
            } else if (charArray[i] == 'b' || charArray[i] == 'B') {
                count[1]++;
            } else if (charArray[i] == 'c' || charArray[i] == 'C') {
                count[2]++;
            } else if (charArray[i] == 'd' || charArray[i] == 'D') {
                count[3]++;
            } else if (charArray[i] == 'e' || charArray[i] == 'E') {
                count[4]++;
            } else if (charArray[i] == 'f' || charArray[i] == 'F') {
                count[5]++;
            } else if (charArray[i] == 'g' || charArray[i] == 'G') {
                count[6]++;
            } else if (charArray[i] == 'h' || charArray[i] == 'H') {
                count[7]++;
            } else if (charArray[i] == 'i' || charArray[i] == 'I') {
                count[8]++;
            } else if (charArray[i] == 'j' || charArray[i] == 'J') {
                count[9]++;
            } else if (charArray[i] == 'k' || charArray[i] == 'K') {
                count[10]++;
            } else if (charArray[i] == 'l' || charArray[i] == 'L') {
                count[11]++;
            }
            else if (charArray[i] == 'm' || charArray[i] == 'M') {
                count[12]++;
            }
            else if (charArray[i] == 'n' || charArray[i] == 'N') {
                count[13]++;
            }
            else if (charArray[i] == 'o' || charArray[i] == 'O') {
                count[14]++;
            }
            else if (charArray[i] == 'p' || charArray[i] == 'P') {
                count[15]++;
            }
            else if (charArray[i] == 'q' || charArray[i] == 'Q') {
                count[16]++;
            }
            else if (charArray[i] == 'r' || charArray[i] == 'R') {
                count[17]++;
            }
            else if (charArray[i] == 's' || charArray[i] == 'S') {
                count[18]++;
            }
            else if (charArray[i] == 't' || charArray[i] == 'T') {
                count[19]++;
            }
            else if (charArray[i] == 'u' || charArray[i] == 'U') {
                count[20]++;
            }
            else if (charArray[i] == 'v' || charArray[i] == 'V') {
                count[21]++;
            }
            else if (charArray[i] == 'w' || charArray[i] == 'W') {
                count[22]++;
            }
            else if (charArray[i] == 'x' || charArray[i] == 'X') {
                count[23]++;
            }
            else if (charArray[i] == 'y' || charArray[i] == 'Y') {
                count[24]++;
            }
            else if (charArray[i] == 'z' || charArray[i] == 'Z') {
                count[25]++;
            }
        }

        for (int i : count) {
            if (i == 0) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");


    }
}
