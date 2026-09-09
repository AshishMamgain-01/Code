package com.learn.coding.corejava.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Broken equals/hashCode vs correct record-style contract.
 */
public class EqualsHashCodeDemo {

	public static void main(String[] args) {
		brokenContract();
		correctContract();
	}

	private static void brokenContract() {
		Map<BrokenEmployee, String> map = new HashMap<>();
		BrokenEmployee e1 = new BrokenEmployee("A1", "Ashish");
		BrokenEmployee e2 = new BrokenEmployee("A1", "Ashish");
		map.put(e1, "lead");
		System.out.println("Broken: e1.equals(e2)=" + e1.equals(e2) + " same hash? "
				+ (e1.hashCode() == e2.hashCode()));
		System.out.println("Broken: get(e2)=" + map.get(e2) + " (null if hashes differ)");
	}

	private static void correctContract() {
		Map<EmployeeKey, String> map = new HashMap<>();
		EmployeeKey e1 = new EmployeeKey("A1", "Ashish");
		EmployeeKey e2 = new EmployeeKey("A1", "Ashish");
		map.put(e1, "lead");
		System.out.println("Correct: e1.equals(e2)=" + e1.equals(e2));
		System.out.println("Correct: get(e2)=" + map.get(e2));
	}

	static final class BrokenEmployee {
		private final String id;
		private final String name;

		BrokenEmployee(String id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof BrokenEmployee other)) {
				return false;
			}
			return Objects.equals(id, other.id) && Objects.equals(name, other.name);
		}

		@Override
		public int hashCode() {
			return System.identityHashCode(this);
		}
	}

	static final class EmployeeKey {
		private final String id;
		private final String name;

		EmployeeKey(String id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof EmployeeKey other)) {
				return false;
			}
			return Objects.equals(id, other.id) && Objects.equals(name, other.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name);
		}
	}
}
