package com.learn.coding.dsa.linklist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicate {
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	private static Node removeDuplicates(Node head) {

		if(head==null) return null;
		Node current = head;
		Node prev = null;

		Set<Integer> seen = new HashSet<>();

		while (current != null) {

			if (seen.contains(current.data)) {
				prev.next =current.next;
			} else {
				seen.add(current.data);
				prev=current;
			}
			current =current.next;
		}
		return head;
	}

	private static void printList(Node head) {
		Node printer = head;
		while (printer != null) {
			System.out.print(printer.data + " -> ");
			printer = printer.next;
		}
		System.out.println("null");
	}
	
	public static void main(String[] args) {
		// Example: 3 -> 2 -> 3 -> 4 -> 2 -> 3 -> NULL
		Node head = new Node(3);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(2);
		head.next.next.next.next.next = new Node(3);

		System.out.println("Original list:");
		printList(head);

		head = removeDuplicates(head);

		System.out.println("After removing duplicates:");
		printList(head);
	}

}
