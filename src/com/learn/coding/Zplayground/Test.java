package com.learn.coding.Zplayground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Employee {
	
	String name;
	String department;
	double salary;
	
	public Employee(String name, String department, double salary) {
		super();
		this.name = name;
		this.department = department;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public String getDepartment() {
		return department;
	}
	public double getSalary() {
		return salary;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", salary=" + salary + "]";
	}
}

public class Test {

	public static void main(String[] args) {
		List<Employee> employee = new ArrayList<>();
		employee.add(new Employee("Ashish","IT",1000.0));
		employee.add(new Employee("Amit","IT",900.0));
		employee.add(new Employee("Arun","IT",8000.0));
		employee.add(new Employee("Alok","Finance",1000.0));
		employee.add(new Employee("Ashok","Finance",900.0));
		employee.add(new Employee("Arpit","Finance",7000.0));
		employee.add(new Employee("Anurag","Finance",7000.0));
		employee.add(new Employee("David","HR",1000.0));
		employee.add(new Employee("Amita","HR",900.0));
		employee.add(new Employee("Aruna","HR",5000.0));
		
		//Highest salary overall.
		
		double highestSalary = employee.stream().mapToDouble(Employee::getSalary).max().orElse(0);
		
		System.out.println("highestSalary : " + highestSalary);
		
		
		//Second-highest salary.
		double secondHighestSalary = employee.stream()
				.filter(e->e.getSalary()!=highestSalary)
				.mapToDouble(Employee::getSalary).max().orElse(0);
		System.out.println("secondHighestSalary : " + secondHighestSalary);
		
		List<Double> asd = employee.stream()
				.map(e->e.getSalary())
				.sorted(Comparator.reverseOrder())
				.distinct()
				.limit(3)
				.collect(Collectors.toList());
		System.out.println("asd : \n"+asd+"\n");
		//Highest salary by department.
				
		Map<String, Optional<Employee>> highestSalByDept = employee.stream()
		.collect(Collectors.groupingBy(
				Employee::getDepartment, 
				Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
		System.out.println(highestSalByDept);
		
		Map<String , Employee> highestSalByDept1 =employee.stream()
		.collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));		
		System.out.println(highestSalByDept1);
		
		//All employees having the highest salary by department.
		Map<String, List<Employee>> allHighestByDept = employee.stream()
				.collect(Collectors.groupingBy(
						Employee::getDepartment,
						Collectors.collectingAndThen(
								Collectors.toList(), list45 -> {
									Double maxSalary = list45.stream()
											.mapToDouble(Employee::getSalary)
											.max()
											.orElse(0.0);
					
					return list45.stream()
							.filter(e-> e.getSalary()==maxSalary)
							.collect(Collectors.toList());
				})));
		
		System.out.println("allHighestByDept : "+allHighestByDept);
		
		//Average salary by department.
		Map<String , Double> employeLissa =employee.stream().collect(Collectors.groupingBy(
				Employee::getDepartment,
				Collectors.averagingDouble(Employee::getSalary)));		
		System.out.println(employeLissa);
		
		//Department with highest average salary.
		String name = employeLissa.entrySet().stream().max(Map.Entry.comparingByValue()).map(e ->e.getKey()).orElse(null);
		System.out.println(name);
		
		//Top 3 salaries per department.
		
		//top3HighestSalary = 
		employee.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.collectingAndThen(Collectors.toList(), list100 -> {
							
							List<Double> maxSalary = list100.stream()
									.map(e ->e.getSalary())
									.sorted(Comparator.reverseOrder())
									.distinct().limit(3).collect(Collectors.toList());
							
							return list100.stream()
									.filter(e -> maxSalary.contains(e.getSalary())).collect(Collectors.toList());
						}))); 
		System.out.println();
		
		//Group employees by department.
		Map<String , List<Employee>> groupByDept = employee.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));
		System.out.println(groupByDept);
		
		Map<String, List<String>> groupByDepte = employee.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, 
						Collectors.mapping(Employee::getName, Collectors.toList())));
		System.out.println(groupByDepte);
		
		//Sort employees by salary descending.
		List<Employee> emL = employee.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
		System.out.println(emL);
		
		
		List<String> words = Arrays.asList("listen", "silent", "enlist", "google", "elbow", "below");
		Map<String,List<String>> ana= words.stream().collect(Collectors.groupingBy(word -> {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			return new String(chars);
		}));
		
		System.out.println(ana);
	}

}
