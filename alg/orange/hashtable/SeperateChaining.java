package com.company.alg.orange.hashtable;

class NodeStr {
    public String key;
    public NodeStr next;

    public NodeStr(String value) {
        this.key = value;
    }
}

class HashTable {
    public NodeStr head;
    public int count;
}


public class SeperateChaining {
    static final int TABLE_SIZE = 11;
    private static HashTable [] hashTable;

    private static void insertKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        NodeStr newNode = new NodeStr(key);
        if (hashTable[index].head == null) {
            hashTable[index].head = newNode;
            hashTable[index].count = 1;
            return;
        }

        newNode.next = hashTable[index].head;
        hashTable[index].head = newNode;
        hashTable[index].count++;
    }

    private static boolean searchKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        NodeStr myNode = hashTable[index].head;
        while (myNode != null) {
            if (myNode.key == key) {
                return true;
            }
            myNode = myNode.next;
        }
        return false;
    }

    private static boolean deleteKey(String key) {
        int index = Hash.polynominalHash(key) % TABLE_SIZE;
        NodeStr myNode = hashTable[index].head;
        if (myNode == null) {
            return false;
        }
        NodeStr temp = myNode;
        while (myNode != null) {
            if (myNode.key == key) {
                if (myNode == hashTable[index].head) {
                    hashTable[index].head = myNode.next;
                } else {
                    temp.next = myNode.next;
                }
                hashTable[index].count--;
                return true;
            }
            temp = myNode;
            myNode = myNode.next;
        }
        return false;
    }

    private static void printHasTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if(hashTable[i].count == 0) {
                continue;
            }
            NodeStr myNode = hashTable[i].head;
            if (myNode == null) {
                continue;
            }
            System.out.printf("------------Index %d------------\n", i);
            int j = 0;
            while (myNode != null) {
                j++;
                System.out.printf("%d.%d %s\n", i, j, myNode.key);
                myNode = myNode.next;
            }
        }
    }

    public static void main(String[] args) {
        String [] keys = new String []{"John", "Usha", "Summer", "Bella", "Donna", "Alice"};
        hashTable = new HashTable[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = new HashTable();
        }
        for (int i = 0; i < keys.length; i++) {
            insertKey(keys[i]);
        }
        printHasTable();
    }
}
