package com.learn.coding.dsa.linklist;

public class MergeSortSamelist {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;

		}
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.next = new Node(2);
		head.next.next = new Node(1);
		head.next.next.next = new Node(3);

		System.out.println("Original list:");
		printList(head);

		head = mergeSort(head);

		System.out.println("Sorted list:");
		printList(head);
	}

	private static Node mergeSort(Node head) {
		// TODO Auto-generated method stub
		if (head == null || head.next == null) {
			return head; // base case
		}
		Node dummNode = head;

		// find middle element
		Node middleNode = getMiddleNode(head);
		Node nextOfMiddle = middleNode.next;
		middleNode.next = null;
		Node left = mergeSort(head);
		Node right = mergeSort(nextOfMiddle);

		return mergeTwoLists(left, right);

	}

	private static Node mergeTwoLists(Node left, Node right) {
		// TODO Auto-generated method stub

		Node dummy = new Node(0);
		Node temp = dummy;
		while (left != null && right != null) {
			if (left.data < right.data) {
				temp.next = left;
				left = left.next;
			} else {
				temp.next =right;
				right=right.next;
			}
			temp=temp.next;
		}
		
		if(left!=null) {
			temp.next=left;
		}
		else {
			temp.next=right;
		}

		return dummy.next;
	}

	private static Node getMiddleNode(Node head) {
		// TODO Auto-generated method stub
		Node slow = head;
		Node fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	private static void printList(Node head) {
		Node printer = head;
		while (printer != null) {
			System.out.print(printer.data + " -> ");
			printer = printer.next;
		}
		System.out.println("null");
	}

}
