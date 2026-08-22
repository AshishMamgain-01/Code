package com.learn.coding.dsa.linklist;

public class ReverseKGroup {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	private static ListNode getKthNode(ListNode startNode, int k) {

		ListNode currentNode = startNode;
		for (int count = 0; count < k; count++) {
			if (currentNode == null) {
				return null;
			}
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	private static void reverse(ListNode current, ListNode stopNode) {
		ListNode prev = stopNode;
		while (current != stopNode) {
			ListNode next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
	}

	public ListNode reverseKGroup(ListNode head, int k) {

		ListNode dummmyHead = new ListNode(0);
		dummmyHead.next = head;
		ListNode previousGroupEnd = dummmyHead;

		while (true) {

			ListNode kthNode = getKthNode(previousGroupEnd, k);
			if (kthNode == null)
				break;

			ListNode currentGroupHead = previousGroupEnd.next;
			ListNode nextGroupHead = kthNode.next;

			reverse(currentGroupHead, nextGroupHead);

			previousGroupEnd.next = kthNode;
			previousGroupEnd = currentGroupHead;
		}

		return dummmyHead.next;
	}

	private static void printList(ListNode newHead) {
		ListNode printer = newHead;
		while (printer != null) {
			System.out.print(printer.val + " -> ");
			printer = printer.next;
		}
	}

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);

		ReverseKGroup obj = new ReverseKGroup();
		ListNode newHead = obj.reverseKGroup(head, 3);
		printList(newHead); // Output: 3 2 1 6 5 4 7 8

	}

}
