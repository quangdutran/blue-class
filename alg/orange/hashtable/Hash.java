package com.company.alg.orange.hashtable;

public class Hash {
    static final int positive = 0x7FFFFFFF;
    /**
     * First bit represent positive/negative
     * A number & 0x7FFFFFFF to make sure it is positive (0x7FFFFFFF = 0111 1111 1111 1111 1111 1111 1111 1111)
     */
    public static int RSHash(String keys) {
        int a = 63689;
        int b = 378551;

        int hashValue = 0;
        for (int i = 0; i < keys.length(); i++) {
            hashValue = (hashValue * a + keys.charAt(i)) & positive;
            a = (a*b) & positive;
        }
        return hashValue & positive;
    }

    public static int polynominalHash(String string) {
        int hashValue = 0;
        int a = 33;
        for (int i = 0; i < string.length(); i++) {
            hashValue = (string.charAt(i) + a * hashValue);
        }
        return hashValue & positive;
    }

    public static int cyclicShift(String keys) {
        int hashValue = 1315423911;
        int a = 5, b = 2;
        for (int i = 0; i < keys.length(); i++) {
            int x = hashValue << a;
            int y = hashValue >> b;
            hashValue ^= (x + keys.charAt(i) + y);
        }
        return hashValue & positive;
    }

    public static void main(String[] args) {
        System.out.println(RSHash("Algo"));
    }
}
