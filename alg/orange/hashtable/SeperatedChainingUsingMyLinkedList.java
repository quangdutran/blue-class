package com.company.alg.orange.hashtable;

public class SeperatedChainingUsingMyLinkedList {
    static final int TABLE_SIZE = 11;
    private static MyLinkedList<String> [] hashTable;

    private static void insertKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        hashTable[index].insertHead(key);
    }

    private static boolean searchKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        return hashTable[index].contain(key);
    }

    private static boolean deleteKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        return hashTable[index].removeKey(key);
    }

    public static void main(String[] args) {
        String [] keys = new String []{"John", "Usha", "Summer", "Bella", "Donna", "Alice"};
        hashTable = new MyLinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = new MyLinkedList<>();
        }
        for (int i = 0; i < keys.length; i++) {
            insertKey(keys[i]);
        }
        int count = 0;
        for (MyLinkedList<String> stringMyLinkedList : hashTable) {
            System.out.printf("------Index %d-------", count);
            stringMyLinkedList.printList();
            count++;
        }

        System.out.println("Usha in the hashTable? " + (searchKey("Usha") ? "YES" : "NO"));
        System.out.println("Du in the hashTable? " + (searchKey("Du") ? "YES" : "NO"));
        System.out.println("Remove Usha");
        deleteKey("Usha");
        System.out.println("Usha in the hashTable? " + (searchKey("Usha") ? "YES" : "NO"));
        System.out.println("Bella in the hashTable? " + (searchKey("Bella") ? "YES" : "NO"));
    }
}
