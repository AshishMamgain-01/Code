package com.learn.coding.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.learn.coding.stream.InterviewEmployeeStream.Employee;

public class EmployeeTestStream {

	public static void main(String args[]) {

		List<Employee> employees = Arrays.asList(
				new Employee(1, "David", 30000, "IT"),
				new Employee(2, "Alice", 40000, "IT"), 
				new Employee(3, "Ashish", 50000, "IT"),
				new Employee(4, "Bob", 60000, "HR"), 
				new Employee(5, "Frank", 70000, "HR"),
				new Employee(6, "Frankii", 80000, "HR"), 
				new Employee(7, "Charlie", 70000, "Finance"),
				new Employee(8, "Henry", 80000, "Finance"),
				new Employee(9, "Rishabh", 50000, "IT"),
				new Employee(10, "Eviii", 80000, "Finance"));

		/* Return a Map<String, List<Employee>> containing only employees whose salary is greater than 40,000 and 
		 grouped by department. */
		Map<String, List<Employee>> salaryGreaterThan40K = employees.stream()
				.filter(e -> e.getSalary() > 40000)
				.collect(Collectors.groupingBy(Employee::getDepartment));

		System.out.println("\n SalaryGreaterThan40K \n:" + salaryGreaterThan40K);

		// Return a Map<String, Employee> containing only employees whose salary is
		// max in department department.

		Map<String, Employee> employeeWithMaxSalary = employees.stream()
			    .collect(Collectors.groupingBy(
			        Employee::getDepartment,
			        Collectors.collectingAndThen(
			            Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
			            Optional::get   // unwrap Optional<Employee> to Employee
			        )
			    ));


		System.out.println("\n employeeWithMaxSalary \n:" + employeeWithMaxSalary);

		// Suppose two employees have the same department and same highest salary.
		// Return the employee with the smaller employee ID.
		
		Map<String, Employee> empMaxSalResTieSmallerId =
	            employees.stream()
	                     .collect(Collectors.groupingBy(Employee::getDepartment,
	                         Collectors.collectingAndThen(
	                             Collectors.maxBy(
	                                 Comparator.comparingDouble(Employee::getSalary)
	                                           .thenComparing(Employee::getId, Comparator.naturalOrder())
	                             ),
	                             Optional::get
	                         )
	                     ));
		
		System.out.println("\n empMaxSalResTieSmallerId \n:" + empMaxSalResTieSmallerId);
		
		//find all employees per department having max salary
		Map<String, List<Employee>> topSalByDeptIncTie =
	            employees.stream()
	                     .collect(Collectors.groupingBy(Employee::getDepartment,
	                         Collectors.collectingAndThen(Collectors.toList(), list -> {
	                             double maxSalary = list.stream()
	                                                    .mapToDouble(Employee::getSalary)
	                                                    .max()
	                                                    .orElse(0);
	                             return list.stream()
	                                        .filter(e -> e.getSalary() == maxSalary)
	                                        .collect(Collectors.toList());
	                         })
	                     ));
		System.out.println("\ntopSalByDeptIncTie : \n" +topSalByDeptIncTie);
		
		//top 3 salaries per department including ties
		Map<String, List<Employee>> top3ByDeptIncTies =
	            employees.stream()
	                .collect(Collectors.groupingBy(Employee::getDepartment,
	                    Collectors.collectingAndThen(Collectors.toList(), list -> {
	                        // Find top 3 distinct salaries
	                        Set<Double> topSalaries = list.stream()
	                            .map(Employee::getSalary)
	                            .distinct()
	                            .sorted(Comparator.reverseOrder())
	                            .limit(3)
	                            .collect(Collectors.toSet());

	                        // Keep all employees whose salary is in those top salaries
	                        return list.stream()
	                            .filter(e -> topSalaries.contains(e.getSalary()))
	                            .collect(Collectors.toList());
	                    })
	                ));
		System.out.println("\ntop3ByDeptIncTie : \n" +top3ByDeptIncTies);

	}
}