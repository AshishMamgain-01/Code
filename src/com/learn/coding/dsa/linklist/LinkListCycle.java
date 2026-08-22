package com.learn.coding.dsa.linklist;

public class LinkListCycle {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	private static Node findCycleStart(Node head) {
		
		Node slow = head;
		Node fast = head;
		// detect cycle
		while (slow != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				slow = head;
				while (slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}

		return null;
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

	private static void printLinkList(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + "->");
			current = current.next;
		}
		System.out.println("null");
	}

	private static boolean hasCycle(Node head) {

		Node slowPointer = head;
		Node fastPointer = head;
		while (slowPointer != null && fastPointer.next != null) {
			slowPointer = slowPointer.next;
			fastPointer = fastPointer.next.next;

			if (slowPointer == fastPointer)
				return true;
		}
		return false;

	}
	
	public static void main(String[] args) {
		
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = head.next.next;

		printLinkList(head);
		head = reverseLinkList(head);
		printLinkList(head);
		
		System.out.println(hasCycle(head));
		Node cycleStart = findCycleStart(head);
		System.out.println(cycleStart.data);
	}
}
