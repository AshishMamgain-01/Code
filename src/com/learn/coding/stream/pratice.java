package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learn.coding.stream.InterviewEmployeeStream.Employee;

class practice {

	public static void main(String args[]) {
		String str = "I love my india";
		// second longest lengthy word need to get via streams;

		System.out.println(str);
		String ans = Arrays.stream(str.split(" ")).sorted(Comparator.comparingInt(String::length).reversed()).skip(1)
				.findFirst().orElse(null);
		System.out.println(ans);

		List<Employee> employees = Arrays.asList(
				new Employee(1, "David", 30000, "IT"),
				new Employee(2, "Alice", 40000, "IT"), 
				new Employee(3, "Ashish", 50000, "IT"),
				new Employee(4, "Bob", 60000, "HR"), 
				new Employee(5, "Frank", 70000, "HR"),
				new Employee(6, "Frankii", 80000, "HR"), 
				new Employee(7, "Charlie", 70000, "Finance"),
				new Employee(8, "Henry", 80000, "Finance"),
				new Employee(10, "Eviii", 80000, "Finance"));

		// Return a Map<String, List<Employee>> containing only employees whose salary
		// is greater than 40,000, grouped by department.

		Map<String, List<Employee>> salaryGreaterThan40K = employees.stream().filter(e -> e.getSalary() > 40000)
				.collect(Collectors.groupingBy(Employee::getDepartment));

		System.out.println("\n SalaryGreaterThan40K \n:" + salaryGreaterThan40K);

		// Return a Map<String, Employee> containing only employees whose salary is
		// max in department department.

		Map<String, Optional<Employee>> employeeWIthMaxSalary = employees.stream().collect(Collectors.groupingBy(
				Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

		System.out.println("\n employeeWIthMaxSalary \n:" + employeeWIthMaxSalary);

		// Suppose two employees have the same department and same highest salary.
		// Return the employee with the smaller employee ID.

	}
}