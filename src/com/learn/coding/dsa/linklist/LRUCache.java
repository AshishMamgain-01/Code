package com.learn.coding.dsa.linklist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	static class Node {
		int key;
		int value;
		Node next;
		Node previous;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private final int capacity;
	private final Map<Integer, Node> map;
	private final Node head;
	private final Node tail;

	public LRUCache(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capcity should be greater than 0");
		}
		this.capacity = capacity;

		map = new HashMap<Integer, LRUCache.Node>();
		head = new Node(0, 0);
		tail = new Node(0, 0);

		head.next = tail;
		tail.previous = head;

	}

	// head <-> 3(30) <-> 2(20) <-> 1(10) <-> tail

	public void put(int key, int value) {

		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			remove(node);
			addAfterHead(node);
			return;
		}

		Node node = new Node(key, value);
		map.put(key, node);

		addAfterHead(node);

		// capacity exceeded

		if (map.size() > capacity) {
			Node lru = tail.previous;

			remove(lru);
			map.remove(lru.key);
		}

	}

	public int get(int key) {

		Node node = map.get(key);
		if (node == null)
			return -1;
		remove(node);
		addAfterHead(node);
		return node.value;
	}

	private void addAfterHead(Node node) {
		// node at front pos

		node.next = head.next;
		node.previous = head;

		head.next.previous = node;
		head.next = node;
	}

	private void remove(Node node) {
		// TODO Auto-generated method stub
		if (node == head || node == tail) return;
		node.previous.next = node.next;
		node.next.previous = node.previous;
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.put(1, 10);
		cache.put(2, 20);
		cache.put(3, 30);

		System.out.println(cache.get(1));

		cache.put(4, 40);
		System.out.println(cache.get(2));

		cache.put(5, 50);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
		System.out.println(cache.get(5));
		
	}

}