package com.learn.coding.designpattern.creational.builder;

// Prototype Interface
interface EmployeePrototype extends Cloneable {
    EmployeePrototype clone();
    void showDetails();
}

// Concrete Prototype
class Employee implements EmployeePrototype {
    private String name;
    private String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    // Clone method
    public EmployeePrototype clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void showDetails() {
        System.out.println("Employee: " + name + ", Department: " + department);
    }
}

// Test
public class Prototype {
    public static void main(String[] args) {
        // Original employee
        Employee emp1 = new Employee("Ashish", "IT");
        emp1.showDetails();

        // Clone employee
        Employee emp2 = (Employee) emp1.clone();
        emp2.showDetails();

        // Modify clone
        Employee emp3 = (Employee) emp1.clone();
        emp3 = new Employee("Rahul", "HR");
        emp3.showDetails();
    }
}
