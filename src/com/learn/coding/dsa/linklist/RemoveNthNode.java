package com.learn.coding.dsa.linklist;

public class RemoveNthNode {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        printNodes(head);
        Node currentNode = removeKthFromBeginning(head, 1);
        printNodes(currentNode);
    }

    private static void printNodes(Node head) {
        Node printer = head;
        while (printer != null) {
            System.out.print(printer.data + " -> ");
            printer = printer.next;
        }
        System.out.println("null");
    }

    private static Node removeKthFromBeginning(Node head, int k) {
        Node previousNode = head;
        if (head == null) {
            return null;
        }

        // Remove first node
        if (k == 1) {
            return head.next;
        }
        for (int i = 1; i < k - 1; i++) {
            previousNode = previousNode.next;
        }

        previousNode.next = previousNode.next.next;

        return head;
    }

    public static Node removeKthFromEnd(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node slow = dummy;
        Node fast = dummy;

        // Move fast k steps ahead
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the node
        slow.next = slow.next.next;

        return dummy.next;
    }

}
