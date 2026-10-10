package com.learn.coding.customImplementation;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

	static class Node {
		int key;
		int value;
		Node next;
		Node prev;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	public final int capacity;
	public final Node head;
	public final Node tail;
	public final Map<Integer, Node> map;

	public LRUCache(int capacity) {

		if (capacity <= 0) {
			throw new IllegalArgumentException("Size can't be less than zero.");
		}
		this.capacity = capacity;
		this.head = new Node(0, 0);
		this.tail = new Node(0, 0);
		map = new HashMap<Integer, LRUCache.Node>();

		head.next = tail;
		tail.prev = head;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			removeNode(node);
			addAfterHead(node);
			return;
		}
		Node newNode = new Node(key, value);
		map.put(key, newNode);
		addAfterHead(newNode);
		if (map.size() > capacity) {
			Node lru = tail.prev;
			removeNode(lru);
			map.remove(lru.key);
		}
	}

	public int get(int key) {
		Node node = map.get(key);
		if (node == null) {
			return -1;
		}
		removeNode(node);
		addAfterHead(node);
		return node.value;

	}

	public void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	public void addAfterHead(Node node) {
		node.next = head.next;
		node.prev = head;

		head.next.prev = node;
		head.next = node;
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.put(1, 10);
		cache.put(2, 20);
		cache.put(3, 30);
		cache.put(4, 40);

		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

}