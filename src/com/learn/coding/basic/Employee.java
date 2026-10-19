package com.learn.coding.basic;

import java.util.Objects;

public class Employee {
    private int id;
    private String department;
    private String name;
    
    public Employee(int id, String department, String name) {
		super();
		this.id = id;
		this.department = department;
		this.name = name;
	}
 
	public int getId() {
		return id;
	}

	public String getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}

	@Override
    public String toString() {
        return id + " - " + name + " (" + department + ")";
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // same reference
        if (!(obj instanceof Employee)) return false; // type check

        Employee other = (Employee) obj;
        return this.id == other.id &&  // compare primitive
               Objects.equals(this.department, other.department) &&
               Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, name);
    }
}
