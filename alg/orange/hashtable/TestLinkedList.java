package com.company.alg.orange.hashtable;

public class TestLinkedList {
    public static void main(String[] args) {
        testDoublyLinkedList();
    }

    private static void testDoublyLinkedList() {
        MyDoubleLinkedList<Integer> myDoubleLinkedList = new MyDoubleLinkedList<>();
        myDoubleLinkedList.insertHead(1);
        myDoubleLinkedList.printList();
        myDoubleLinkedList.insertHead(2);
        myDoubleLinkedList.printList();
        myDoubleLinkedList.insertTail(3);
        myDoubleLinkedList.printList();
        myDoubleLinkedList.insertTail(4);
        myDoubleLinkedList.insertHead(5);
        myDoubleLinkedList.printList();
        System.out.println(myDoubleLinkedList.getHead());
        System.out.println(myDoubleLinkedList.getTail());
        System.out.println(myDoubleLinkedList.getRandomNodeInTheMiddle().getValue());

        myDoubleLinkedList.removeHead();
        myDoubleLinkedList.printList();
        myDoubleLinkedList.removeTail();
        myDoubleLinkedList.printList();
        myDoubleLinkedList.insertTail(4);
        myDoubleLinkedList.insertHead(5);
        myDoubleLinkedList.printList();
        Node mid = myDoubleLinkedList.getRandomNodeInTheMiddle();
        myDoubleLinkedList.removeNode(mid);
        myDoubleLinkedList.printList();

        myDoubleLinkedList.removeTail();
        myDoubleLinkedList.printList();
        Node midNode = myDoubleLinkedList.getRandomNodeInTheMiddle();
        myDoubleLinkedList.removeNode(midNode);
        myDoubleLinkedList.printList();
        myDoubleLinkedList.removeHead();
        myDoubleLinkedList.removeTail();
        myDoubleLinkedList.printList();
    }

    private static void testSinglyLinkedList() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.printList();
        myLinkedList.insertHead(1);
        myLinkedList.insertHead(2);
        myLinkedList.printList();
        myLinkedList.insertTail(3);
        myLinkedList.insertTail(4);
        myLinkedList.printList();
        myLinkedList.removeHead();
        myLinkedList.printList();
        myLinkedList.removeTail();
        myLinkedList.printList();
        myLinkedList.removeTail();
        myLinkedList.removeTail();
        myLinkedList.printList();
    }
}
