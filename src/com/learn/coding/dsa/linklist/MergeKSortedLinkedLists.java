package com.learn.coding.dsa.linklist;

import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {

	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Node list1 = new Node(1);
		list1.next = new Node(4);
		list1.next.next = new Node(5);

		Node list2 = new Node(2);
		list2.next = new Node(3);
		list2.next.next = new Node(9);

		Node list3 = new Node(6);
		list3.next = new Node(7);
		list3.next = new Node(8);

		Node[] lists = { list1, list2, list3 };

		Node result = mergeKLists(lists);

		printList(result);
	}

	private static Node mergeKLists(Node[] lists) {
		// TODO Auto-generated method stub

		PriorityQueue<Node> minHeap = new PriorityQueue<MergeKSortedLinkedLists.Node>(
				(a, b) -> Integer.compare(a.data, b.data));

		for (Node head : lists) {
			if (head != null)
				minHeap.offer(head);
		}

		Node dummy = new Node(0);
		Node current = dummy;

		while (!minHeap.isEmpty()) {
			// Get Smallest node
			Node smallest = minHeap.poll();
			// Add to result
			current.next = smallest;
			current = current.next;

			if (smallest.next != null) {
				minHeap.offer(smallest.next);
			}
		}

		return dummy.next;
	}

	private static void printList(Node result) {

		Node current = result;

		while (current != null) {
			System.out.print(current.data + "->");
			current = current.next;
		}

		System.out.println("null");
	}

}
