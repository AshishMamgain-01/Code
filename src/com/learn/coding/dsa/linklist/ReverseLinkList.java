package com.learn.coding.dsa.linklist;

public class ReverseLinkList {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			super();
			this.data = data;
		}
	}

	public static void main(String[] args) {

		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);

		System.out.println("Before:");
		printList(head);

		head = reverseLinkList(head);

		printList(head);
	}

	private static Node reverseLinkList(Node head) {

		Node prev = null;
		Node current = head;

		while (current != null) {
			Node next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}

	private static void printList(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + " -> ");
			current = current.next;
		}
		System.out.println("null");
	}

}
