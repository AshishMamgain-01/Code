package com.learn.coding.basic;
import java.util.*;

public class EmployeeMapDemo {
    public static void main(String[] args) {
        Map<Employee, String> empMap = new HashMap<>();

        empMap.put(new Employee(90, "IT", "Alice"), "IT");
        empMap.put(new Employee(120, "Finance", "Bob"), "Finance");
        empMap.put(new Employee(130, "HR", "Charlie"), "HR");
        empMap.put(new Employee(160, "Sales", "David"), "Sales");

        System.out.println("Before removal:");
        empMap.forEach((k,v) -> System.out.println(k + " -> " + v));

        // Remove entries where id > 100 and id < 150
        empMap.entrySet().removeIf(entry -> {
            int id = entry.getKey().getId();
            return id > 100 && id < 150;
        });

        System.out.println("\nAfter removal:");
        empMap.forEach((k,v) -> System.out.println(k + " -> " + v));
    }
}
