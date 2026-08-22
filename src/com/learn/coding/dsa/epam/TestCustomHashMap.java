package com.learn.coding.dsa.epam;

import java.util.Objects;

class CustomHashMap<K, V> {
	private static class Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Entry<K, V>[] table;
	private int capacity = 16;
	private int size = 0;
	private final float loadFactor = 0.75f;

	@SuppressWarnings("unchecked")
	public CustomHashMap() {
		table = new Entry[capacity];
	}

	private int hash(K key) {
		return Objects.hashCode(key) & (capacity - 1);
	}

	public void put(K key, V value) {
		int index = hash(key);
		Entry<K, V> head = table[index];

		// check if key exists
		for (Entry<K, V> e = head; e != null; e = e.next) {
			if (Objects.equals(e.key, key)) {
				e.value = value; // update
				return;
			}
		}

		// insert new entry at head
		Entry<K, V> newEntry = new Entry<>(key, value);
		newEntry.next = head;
		table[index] = newEntry;
		size++;

		if (size >= capacity * loadFactor) {
			resize();
		}
	}

	public V get(K key) {
		int index = hash(key);
		for (Entry<K, V> e = table[index]; e != null; e = e.next) {
			if (Objects.equals(e.key, key)) {
				return e.value;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		capacity *= 2;
		Entry<K, V>[] oldTable = table;
		table = new Entry[capacity];
		size = 0;

		for (Entry<K, V> head : oldTable) {
			for (Entry<K, V> e = head; e != null; e = e.next) {
				put(e.key, e.value);
			}
		}
	}
}

public class TestCustomHashMap {
    public static void main(String[] args) {
    	CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("Ashish", 1);
        map.put("Interview", 2);
        map.put("Java", 3);

        System.out.println(map.get("Ashish"));     // 1
        System.out.println(map.get("Interview"));  // 2
        System.out.println(map.get("Java"));       // 3
        System.out.println(map.get("Unknown"));    // null
    }
}
