package com.company.binarysearch;

public class BinarySearch {
    public static void main(String[] args) {
        int [] array = new int[]{6,13,33,33,33,33,33,51,53,64,72,84};
        System.out.println(binarySearchFirstPosition(array, 0, array.length - 1, 25));
        System.out.println(binarySearchLastPosition(array, 0, array.length - 1, 25));
        System.out.println(lowerBound(array, array.length - 1, 0, 12));
        //System.out.println(lowerBoundRecursive(array, array.length - 1, 0, 12));
        System.out.println(upperBound(array, array.length - 1, 0, 33));
        //System.out.println(upperBoundRecursive(array, array.length - 1, 0, 13));
    }

    //Can have many same values in the array
    static int binarySearchFirstPosition(int [] array, int left, int right, int x) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if ((mid == left || x > array[mid - 1]) && array[mid] == x) {
                return mid;
            } else if (array[mid] < x) {
                return binarySearchFirstPosition(array, mid + 1, right, x);
            } else {
                return binarySearchFirstPosition(array, left, mid - 1, x);
            }
        }
        return -1;
    }

    static int binarySearchLastPosition(int [] array, int left, int right, int x) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if ((mid == right || x < array[mid + 1]) && array[mid] == x) {
                return mid;
            } else if (array[mid] > x) {
                return binarySearchFirstPosition(array, left, mid - 1, x);
            } else {
                return binarySearchFirstPosition(array, mid + 1, right, x);
            }
        }
        return -1;
    }

    //Return index of the first value greater or equal to x, if no then return right index
    static int lowerBound(int [] array, int right, int left, int x) {
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= x) {
                right = mid;
                pos = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    static int lowerBoundRecursive(int [] array, int right, int left, int x) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= x) {
                return lowerBoundRecursive(array, mid, left, x);
            } else {
                return lowerBoundRecursive(array, right, mid + 1, x);
            }
        }
        return right;
    }

    static int upperBound(int [] array, int right, int left, int x) {
        int pos = right;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > x) {
                right = mid;
                pos = mid;
            } else {
                left = mid + 1;
            }
        }
        return pos;
    }

    static int upperBoundRecursive(int [] array, int right, int left, int x) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > x) {
                return upperBoundRecursive(array, mid, left, x);
            } else {
                return upperBoundRecursive(array, right, mid + 1, x);
            }
        }
        return right;
    }
}
