package com.company.alg.orange.hashtable;

public class MyDoubleLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public MyDoubleLinkedList() {

    }

    public void insertHead(T value) {
        Node newNode = new Node<T>(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    public void insertTail(T value) {
        Node newNode = new Node<T>(value);
        if (size == 0) {
            head = tail = newNode;
        } else {
            Node temp = tail;
            tail = newNode;
            temp.setNext(newNode);
            newNode.setPrev(temp);
        }
        size++;
    }

    public T getHead() {
        return head == null ? null : head.getValue();
    }

    public T getTail() {
        return tail == null ? null : tail.getValue();
    }

    public void removeHead() {
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
    }

    public void removeTail() {
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
    }

    public void removeNode(Node node) {
        if (size > 0) {
            Node prev = node.getPrev();
            Node next = node.getNext();
            if (prev != null )
                prev.setNext(next);
            if (next != null)
                next.setPrev(prev);
            node = null;
            size--;
        }
    }

    /**
     * Get the node after head and before tail if size >= 3
     * @return
     */
    public Node getRandomNodeInTheMiddle() {
        if (size >= 3) {
            int indexMid = size / 2;
            int count = 0;
            Node n = head;
            while (count < indexMid) {
                n = head.getNext();
                count++;
            }
            return n;
        } else return null;
    }

    public void printList() {
        if (head == null) {
            System.out.println("empty");
        } else {
            Node n = head;
            System.out.print(n.getValue().toString() + " ");
            while (n.getNext() != null) {
                n = n.getNext();
                System.out.print(n.getValue().toString() + " ");
            }
            System.out.println();
        }
    }
}
