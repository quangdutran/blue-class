package com.company.bst;

public class BinarySearchTree {
    public class BST<T extends Comparable<T>> {
        private Node root;

        public BST() {
            root = null;
        }

        public Node createNewNode(T value) {
            return new Node(value);
        }

        public void insertNode(T value) {
            root = insertNode(root, value);
        }

        public Node search(T value) {
            return search(root, value);
        }

        private void remove(T value) {
            root = deleteNode(root, value);
        }

        private Node search(Node root, T value) {
            if (root == null) {
                return null;
            }
            if (value.compareTo(root.value) == 0) {
                return root;
            } else if (value.compareTo(root.value) < 0) {
                return search(root.left, value);
            } else {
                return search(root.right, value);
            }
        }

        private Node insertNode(Node root, T value) {
            if (root == null) {
                return createNewNode(value);
            }
            if (value.compareTo(root.value) < 0) {
                root.left = insertNode(root.left, value);
            }
            if (value.compareTo(root.value) > 0) {
                root.right = insertNode(root.right, value);
            }
            return root;
        }

        private Node deleteNode(Node root, T value) {
            if (root == null) {
                return null;
            }

            if (value.compareTo(root.value) < 0) {
                root.left = deleteNode(root.left, value);
            }
            else if (value.compareTo(root.value) > 0) {
                root.right = deleteNode(root.right,value);
            } else {
                if (root.left == null) {
                    Node temp = root.right;
                    root = null;
                    return temp;
                } else if (root.right == null) {
                    Node temp = root.left;
                    root = null;
                    return temp;
                } else {
                    Node minValueNode = minValueNode(root.right);
                    root.value = minValueNode.value;
                    root.right = deleteNode(root.right, minValueNode.value);
                }
            }
            return root;
        }

        private Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        class Node {
            T value;
            Node left;
            Node right;

            public Node(T value) {
                this.value = value;
                this.left = null;
                this.right = null;
            }
        }
    }
}
