package com.learn.coding.corejava.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * Immutable type: final class, private final fields, defensive copies.
 */
public class ImmutableEmployeeDemo {

	public static void main(String[] args) {
		List<String> skills = new ArrayList<>();
		skills.add("Java");
		Employee emp = new Employee("Ashish", skills);
		skills.add("hacker");
		System.out.println("external list mutated, employee skills still " + emp.skills());

		try {
			emp.skills().add("nope");
		} catch (UnsupportedOperationException e) {
			System.out.println("exposed list is unmodifiable: " + e.getClass().getSimpleName());
		}
	}

	public static final class Employee {
		private final String name;
		private final List<String> skills;

		public Employee(String name, List<String> skills) {
			this.name = name;
			this.skills = List.copyOf(skills);
		}

		public String name() {
			return name;
		}

		public List<String> skills() {
			return skills;
		}
	}
}
