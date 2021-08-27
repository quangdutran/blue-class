package com.company.alg.orange.hashtable;

import java.util.Objects;

public class MyLinkedList<T> {
    private Node<T> head;
    int size;

    public MyLinkedList() {

    }

    public void insertHead(T value) {
        Node newNode = new Node();
        newNode.setValue(value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void insertTail(T value) {
        if (head == null) {
            insertHead(value);
        } else {
            Node n = head;
            while (n.getNext() != null) {
                n = n.getNext();
            }

            Node newNode = new Node<T>();
            newNode.setValue(value);
            newNode.setNext(null);
            n.setNext(newNode);
        }
        size++;
    }

    public boolean contain(T object) {
        if (head == null) {
            return false;
        } else {
            Node<T> n = head;
            while (n != null) {
                if (Objects.equals(object, n.getValue())) {
                    return true;
                }
                n = n.getNext();
            }
            return false;
        }
    }

    /**
     * O(n) to remove
     * @param key
     * @return
     */
    public boolean removeKey(T key) {
        if (head == null) {
            return false;
        } else {
            Node<T> n = head;
            Node<T> temp = n;
            while (n != null) {
                if (Objects.equals(key, n.getValue())) {
                    //delete
                    if (Objects.equals(n, head)) {
                        head = n.getNext();
                    } else {
                        temp.setNext(n.getNext());
                    }
                    size--;
                    return true;
                }
                temp = n;
                n = n.getNext();
            }
            return false;
        }
    }

    public T getHead() {
        return head != null ? head.getValue() : null;
    }

    public T getTail() {
        if (head == null) {
            return null;
        } else {
            Node<T> n = head;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            return n.getValue();
        }
    }

    public void removeHead() {
        if (head != null) {
            head = head.getNext();
            size--;
        }
    }

    public void removeTail() {
        if (head != null) {
            if (size == 1) {
                head = null;
            } else {
                Node<T> n = head;
                int count = 1;
                while (n.getNext() != null && count < size - 1) {
                    n = n.getNext();
                    count++;
                }
                n.setNext(null);
            }
            size--;

        }
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
