package com.learn.coding.dsa.linklist;

public class MergeSortedLinkList {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	private static Node sortTwoLinkedLists(Node list1, Node list2) {
		// TODO Auto-generated method stub
		Node dummy = new Node(0);
		Node temp = dummy;

		while (list1 != null && list2 != null) {
			if (list1.data < list2.data) {
				temp.next = list1;
				list1 = list1.next;
			} else {
				temp.next = list2;
				list2 = list2.next;
			}
			temp = temp.next;
		}

		if (list1 != null) {
			temp.next = list1;
		} else {
			temp.next = list2;
		}
		return dummy.next;
	}

	private static void printLinkedList(Node list1) {
		// TODO Auto-generated method stub
		Node printer = list1;
		while (printer != null) {
			System.out.print(printer.data + " -> ");
			printer = printer.next;
		}

		System.out.println("null");

	}

	public static void main(String[] args) {
		// Example Linked Lists
		Node list1 = new Node(1);
		list1.next = new Node(3);
		list1.next.next = new Node(5);

		Node list2 = new Node(2);
		list2.next = new Node(4);
		list2.next.next = new Node(6);

		System.out.print("First sorted linked list: ");
		printLinkedList(list1);

		System.out.print("Second sorted linked list: ");
		printLinkedList(list2);

		Node mergedList = sortTwoLinkedLists(list1, list2);

		System.out.print("Merged sorted linked list: ");
		printLinkedList(mergedList);
	}

}
