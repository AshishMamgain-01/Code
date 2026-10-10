package com.learn.coding.basic;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class EmployeeSetDemo {
    public static void main(String[] args) {
        // Comparator: sort by id ascending, then name descending
        Comparator<Employee> empComparator = Comparator
                .comparingInt(Employee::getId)  // sort by id ascending
                .thenComparing(Employee::getName, Comparator.reverseOrder()); // name descending

        Set<Employee> employees = new TreeSet<>(empComparator);

        employees.add(new Employee(3, "HR", "Alice"));
        employees.add(new Employee(1, "IT", "Charlie"));
        employees.add(new Employee(2, "Finance", "Bob"));
        employees.add(new Employee(2, "Finance", "David")); // same id, different name

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
