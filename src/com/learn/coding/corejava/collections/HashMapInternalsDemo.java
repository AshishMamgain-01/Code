package com.learn.coding.corejava.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HashMap interview talking points, demonstrated:
 * - hash mixing and bucket index (n is power of 2)
 * - equals collision vs hash collision
 * - load factor / resize (capacity doubles after threshold)
 * - mutating a key after insert loses the entry
 *
 * Treeify (list -> red-black tree at 8, table size >= 64) is in the JDK; we
 * don't reimplement it here. See interview-prep/core-java/HashMapInternals.md
 */
public class HashMapInternalsDemo {

	public static void main(String[] args) {
		showHashMixing();
		showEqualsVsHashCollision();
		showResizeThreshold();
		showMutatingKeyBreaksLookup();
	}

	private static void showHashMixing() {
		int capacity = 16;
		String key = "interface-eth0";
		int h = key.hashCode();
		int mixed = h ^ (h >>> 16);
		int index = (capacity - 1) & mixed;
		System.out.println("key=" + key + " hashCode=" + h + " mixed=" + mixed + " bucket=" + index);
		System.out.println("Index uses bitmask (capacity-1) because capacity is a power of two.");
	}

	private static void showEqualsVsHashCollision() {
		Map<CollidingKey, String> map = new HashMap<>();
		CollidingKey a = new CollidingKey("a", 42);
		CollidingKey b = new CollidingKey("b", 42);
		map.put(a, "first");
		map.put(b, "second");
		System.out.println("Same hash, different equals -> two entries: " + map.size());
		System.out.println("get(a)=" + map.get(a) + " get(b)=" + map.get(b));
	}

	private static void showResizeThreshold() {
		Map<Integer, Integer> map = new HashMap<>(4, 0.75f);
		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
		System.out.println("After 10 puts into initialCapacity=4, size=" + map.size()
				+ " (table grew; default load factor 0.75).");
	}

	private static void showMutatingKeyBreaksLookup() {
		Map<MutableId, String> map = new HashMap<>();
		MutableId id = new MutableId(1);
		map.put(id, "device");
		id.value = 99;
		System.out.println("After mutating key hash field, get returns: " + map.get(id)
				+ " (often null — entry is in the old bucket)");
		System.out.println("size still " + map.size() + " — object is in the map but unreachable by get.");
	}

	static final class CollidingKey {
		private final String name;
		private final int forcedHash;

		CollidingKey(String name, int forcedHash) {
			this.name = name;
			this.forcedHash = forcedHash;
		}

		@Override
		public int hashCode() {
			return forcedHash;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof CollidingKey other)) {
				return false;
			}
			return Objects.equals(name, other.name);
		}
	}

	static final class MutableId {
		int value;

		MutableId(int value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			return value;
		}

		@Override
		public boolean equals(Object o) {
			return o instanceof MutableId m && m.value == value;
		}
	}
}
