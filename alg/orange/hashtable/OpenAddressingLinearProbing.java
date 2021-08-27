package com.company.alg.orange.hashtable;


public class OpenAddressingLinearProbing {
    static class Node {
        public String key;
        public int marker;
    }

    static final int TABLE_SIZE = 11;
    private static Node [] hashTable;
    private static int current_size;

    private static boolean insertKey(String key) {
        if (current_size >= TABLE_SIZE) {
            return false;
        }
        int index = Hash.cyclicShift(key) % TABLE_SIZE;
        while (hashTable[index].marker == 1) {
            index = (index + 1) % TABLE_SIZE;
        }
        hashTable[index].key = key;
        hashTable[index].marker = 1;
        current_size++;
        return true;
    }

    private static int searchKey(String key) {
        int index = Hash.cyclicShift(key) % TABLE_SIZE;
        int count = 0;
        if (current_size == 0) {
            return -1;
        }
        while (hashTable[index].marker != 0 && count <= TABLE_SIZE) {
            if (hashTable[index].key == key) {
                if (hashTable[index].marker == 1) {
                    return index;
                } else return -1;
            }
            index = (index + 1) % TABLE_SIZE;
            count++;
        }
        return -1;
    }

    private static boolean deleteKey(String key) {
        int index = searchKey(key);
        if (index == -1) {
            //not found or deleted
            return false;
        }
        current_size--;
        hashTable[index].marker = -1;
        return true;
    }

    private static void printHashTable() {
        if (current_size == 0) {
            return;
        }
        for (int i = 0; i < TABLE_SIZE; i++) {
            if(hashTable[i].marker == 1) {
                System.out.printf("------Index %d------\n",i);
                System.out.printf("%s\n", hashTable[i].key);
            }
        }
    }

    public static void main(String[] args) {
        String [] keys = new String []{"John", "Usha", "Summer", "Bella", "Donna", "Alice"};
        hashTable = new Node[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = new Node();
        }
        for (int i = 0; i < keys.length; i++) {
            insertKey(keys[i]);
        }
        printHashTable();
    }
}
