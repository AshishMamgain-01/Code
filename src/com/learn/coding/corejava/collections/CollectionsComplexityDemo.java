package com.learn.coding.corejava.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Interview: pick the collection by access pattern, not habit.
 *
 * Run main and read the comments next to each block.
 */
public class CollectionsComplexityDemo {

	public static void main(String[] args) {
		listRandomAccess();
		setUniqueness();
		mapOrdering();
	}

	private static void listRandomAccess() {
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();
		for (int i = 0; i < 10_000; i++) {
			arrayList.add(i);
			linkedList.add(i);
		}

		long t1 = System.nanoTime();
		int a = arrayList.get(5_000);
		long arrayGet = System.nanoTime() - t1;

		long t2 = System.nanoTime();
		int b = linkedList.get(5_000);
		long linkedGet = System.nanoTime() - t2;

		System.out.println("get(5000) ArrayList ns=" + arrayGet + " LinkedList ns=" + linkedGet + " values=" + a + ","
				+ b);
		System.out.println("Takeaway: LinkedList get is O(n). Prefer ArrayList unless you only touch ends.");
	}

	private static void setUniqueness() {
		Set<String> names = new HashSet<>();
		names.add("eth0");
		names.add("eth0");
		System.out.println("HashSet size after duplicate insert: " + names.size() + " (must be 1)");
	}

	private static void mapOrdering() {
		Map<Integer, String> hash = new HashMap<>();
		Map<Integer, String> linked = new LinkedHashMap<>();
		Map<Integer, String> tree = new TreeMap<>();
		int[] keys = { 3, 1, 2 };
		for (int k : keys) {
			hash.put(k, "v" + k);
			linked.put(k, "v" + k);
			tree.put(k, "v" + k);
		}
		System.out.println("HashMap order (not guaranteed): " + hash.keySet());
		System.out.println("LinkedHashMap insertion order:  " + linked.keySet());
		System.out.println("TreeMap sorted order:           " + tree.keySet());
	}
}
