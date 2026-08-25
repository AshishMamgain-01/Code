package com.learn.coding.stream;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewEmployeeStream {

    static class Employee {
        private int id;
        private String name;
        private double salary;
        private String department;

        public Employee(int id, String name, double salary, String department) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }

        @Override
        public String toString() {
            return id + " - " + name +
                    " (" + salary + ", " + department + ")";
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 6000, "IT"),
                new Employee(2, "Bob", 4500, "HR"),
                new Employee(3, "Charlie", 7000, "Finance"),
                new Employee(4, "David", 3000, "IT"),
                new Employee(5, "Eve", 8000, "Finance"),
                new Employee(6, "Frank", 5000, "HR"),
                new Employee(7, "Grace", 9000, "IT"),
                new Employee(8, "Henry", 7000, "Finance")
        );

        // ============================================================
        // 1. Find employees earning more than 5000
        // ============================================================

        System.out.println("\n1. Employees earning > 5000");

        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .forEach(System.out::println);


        // ============================================================
        // 2. Get names of employees earning more than 5000
        // ============================================================

        System.out.println("\n2. Names earning > 5000");

        List<String> names = employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .map(Employee::getName)
                .collect(Collectors.toList());

        System.out.println(names);


        // ============================================================
        // 3. Create a list with 10% salary increase
        // ============================================================

        System.out.println("\n3. Employees with 10% salary increase");

        List<Employee> increasedSalary = employees.stream()
                .map(e -> new Employee(
                        e.getId(),
                        e.getName(),
                        e.getSalary() * 1.10,
                        e.getDepartment()
                ))
                .collect(Collectors.toList());

        increasedSalary.forEach(System.out::println);


        // ============================================================
        // 4. Find employee with highest salary
        // ============================================================

        System.out.println("\n4. Highest-paid employee");

        Employee highest = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        System.out.println(highest);


        // ============================================================
        // 5. Find employee with lowest salary
        // ============================================================

        System.out.println("\n5. Lowest-paid employee");

        Employee lowest = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        System.out.println(lowest);


        // ============================================================
        // 6. Calculate average salary
        // ============================================================

        System.out.println("\n6. Average salary");

        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println(averageSalary);


        // ============================================================
        // 7. Find employees below average salary
        // ============================================================

        System.out.println("\n7. Employees below average salary");

        employees.stream()
                .filter(e -> e.getSalary() < averageSalary)
                .forEach(System.out::println);


        // ============================================================
        // 8. Sort employees by salary descending
        // ============================================================

        System.out.println("\n8. Employees sorted by salary descending");

        employees.stream()
                .sorted(Comparator
                        .comparingDouble(Employee::getSalary)
                        .reversed())
                .forEach(System.out::println);


        // ============================================================
        // 9. Find top 3 highest-paid employees
        // ============================================================

        System.out.println("\n9. Top 3 highest-paid employees");

        employees.stream()
                .sorted(Comparator
                        .comparingDouble(Employee::getSalary)
                        .reversed())
                .limit(3)
                .forEach(System.out::println);


        // ============================================================
        // 10. Calculate total salary expense
        // ============================================================

        System.out.println("\n10. Total salary");

        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println(totalSalary);


        // ============================================================
        // 11. Partition employees based on salary >= average
        // ============================================================

        System.out.println("\n11. Partition based on average salary");

        Map<Boolean, List<Employee>> partitioned =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getSalary() >= averageSalary
                        ));

        System.out.println(partitioned);


        // ============================================================
        // 12. Find duplicate salaries
        // ============================================================

        System.out.println("\n12. Duplicate salaries");

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getSalary,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);


        // ============================================================
        // 13. Group employees by department
        // ============================================================

        System.out.println("\n13. Group by department");

        Map<String, List<Employee>> byDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment
                        ));

        byDepartment.forEach((dept, list) ->
                System.out.println(dept + " -> " + list)
        );


        // ============================================================
        // 14. Count employees per department
        // ============================================================

        System.out.println("\n14. Employee count by department");

        Map<String, Long> countByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        ));

        System.out.println(countByDepartment);


        // ============================================================
        // 15. Average salary per department
        // ============================================================

        System.out.println("\n15. Average salary by department");

        Map<String, Double> averageByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(
                                        Employee::getSalary
                                )
                        ));

        System.out.println(averageByDepartment);


        // ============================================================
        // 16. Total salary per department
        // ============================================================

        System.out.println("\n16. Total salary by department");

        Map<String, Double> totalByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(
                                        Employee::getSalary
                                )
                        ));

        System.out.println(totalByDepartment);


        // ============================================================
        // 17. Highest-paid employee in each department
        // ============================================================

        System.out.println("\n17. Highest-paid employee by department");

        Map<String, Optional<Employee>> highestByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.maxBy(
                                        Comparator.comparingDouble(
                                                Employee::getSalary
                                        )
                                )
                        ));

        System.out.println(highestByDepartment);


        // ============================================================
        // 18. Lowest-paid employee in each department
        // ============================================================

        System.out.println("\n18. Lowest-paid employee by department");

        Map<String, Optional<Employee>> lowestByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.minBy(
                                        Comparator.comparingDouble(
                                                Employee::getSalary
                                        )
                                )
                        ));

        System.out.println(lowestByDepartment);


        // ============================================================
        // 19. Find second-highest DISTINCT salary
        // ============================================================

        System.out.println("\n19. Second-highest distinct salary");

        double secondHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0.0);

        System.out.println(secondHighestSalary);


        // ============================================================
        // 20. Find third-highest DISTINCT salary
        // ============================================================

        System.out.println("\n20. Third-highest distinct salary");

        double thirdHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(0.0);

        System.out.println(thirdHighestSalary);


        // ============================================================
        // 21. Find second-highest-paid employee
        // ============================================================

        System.out.println("\n21. Second-highest-paid employee");

        Employee secondHighestEmployee = employees.stream()
                .sorted(Comparator
                        .comparingDouble(Employee::getSalary)
                        .reversed())
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println(secondHighestEmployee);


        // ============================================================
        // 22. Find top 2 employees from each department
        // ============================================================

        System.out.println("\n22. Top 2 employees by department");

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment
                ))
                .forEach((dept, empList) -> {

                    List<Employee> top2 = empList.stream()
                            .sorted(Comparator
                                    .comparingDouble(Employee::getSalary)
                                    .reversed())
                            .limit(2)
                            .collect(Collectors.toList());

                    System.out.println(dept + " -> " + top2);
                });


        // ============================================================
        // 23. Second-highest salary in each department
        // ============================================================

        System.out.println("\n23. Second-highest salary by department");

        Map<String, Optional<Double>> secondHighestByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,

                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                Employee::getSalary,
                                                Collectors.toList()
                                        ),

                                        salaries -> salaries.stream()
                                                .distinct()
                                                .sorted(Comparator.reverseOrder())
                                                .skip(1)
                                                .findFirst()
                                )
                        ));

        System.out.println(secondHighestByDepartment);


        // ============================================================
        // 24. Department with highest average salary
        // ============================================================

        System.out.println("\n24. Department with highest average salary");

        Map.Entry<String, Double> highestAverageDepartment =
                averageByDepartment.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(highestAverageDepartment);


        // ============================================================
        // 25. Department with highest total salary
        // ============================================================

        System.out.println("\n25. Department with highest total salary");

        Map.Entry<String, Double> highestTotalDepartment =
                totalByDepartment.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(highestTotalDepartment);


        // ============================================================
        // 26. Get all employee names as comma-separated string
        // ============================================================

        System.out.println("\n26. Names as comma-separated string");

        String allNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        System.out.println(allNames);


        // ============================================================
        // 27. Convert Employee list to Map<Id, Name>
        // ============================================================

        System.out.println("\n27. Employee ID -> Name");

        Map<Integer, String> idNameMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getId,
                                Employee::getName
                        ));

        System.out.println(idNameMap);


        // ============================================================
        // 28. Convert Employee list to Map<Name, Salary>
        // ============================================================

        System.out.println("\n28. Employee Name -> Salary");

        Map<String, Double> nameSalaryMap =
                employees.stream()
                        .collect(Collectors.toMap(
                                Employee::getName,
                                Employee::getSalary
                        ));

        System.out.println(nameSalaryMap);


        // ============================================================
        // 29. Employees with salary >= 5000
        // ============================================================

        System.out.println("\n29. Partition salary >= 5000");

        Map<Boolean, List<Employee>> salaryPartition =
                employees.stream()
                        .collect(Collectors.partitioningBy(
                                e -> e.getSalary() >= 5000
                        ));

        System.out.println(salaryPartition);


        // ============================================================
        // 30. Group employees into HIGH and LOW salary
        // ============================================================

        System.out.println("\n30. Group into HIGH and LOW salary");

        Map<String, List<Employee>> salaryGroup =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                e -> e.getSalary() >= 5000
                                        ? "HIGH"
                                        : "LOW"
                        ));

        System.out.println(salaryGroup);


        // ============================================================
        // 31. Does any employee earn more than 10000?
        // ============================================================

        System.out.println("\n31. Any salary > 10000?");

        boolean anyHighSalary = employees.stream()
                .anyMatch(e -> e.getSalary() > 10000);

        System.out.println(anyHighSalary);


        // ============================================================
        // 32. Are all employees earning more than 2000?
        // ============================================================

        System.out.println("\n32. All salaries > 2000?");

        boolean allAbove2000 = employees.stream()
                .allMatch(e -> e.getSalary() > 2000);

        System.out.println(allAbove2000);


        // ============================================================
        // 33. Are there no employees earning below 2000?
        // ============================================================

        System.out.println("\n33. No salary < 2000?");

        boolean noneBelow2000 = employees.stream()
                .noneMatch(e -> e.getSalary() < 2000);

        System.out.println(noneBelow2000);


        // ============================================================
        // 34. Find first employee from IT
        // ============================================================

        System.out.println("\n34. First IT employee");

        Employee firstIT = employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .findFirst()
                .orElse(null);

        System.out.println(firstIT);


        // ============================================================
        // 35. Find any employee from IT
        // ============================================================

        System.out.println("\n35. Any IT employee");

        Employee anyIT = employees.stream()
                .filter(e -> e.getDepartment().equals("IT"))
                .findAny()
                .orElse(null);

        System.out.println(anyIT);


        // ============================================================
        // 36. Get employee names grouped by department
        // ============================================================

        System.out.println("\n36. Names grouped by department");

        Map<String, List<String>> namesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(
                                        Employee::getName,
                                        Collectors.toList()
                                )
                        ));

        System.out.println(namesByDepartment);


        // ============================================================
        // 37. Salary statistics
        // ============================================================

        System.out.println("\n37. Salary statistics");

        DoubleSummaryStatistics statistics =
                employees.stream()
                        .collect(Collectors.summarizingDouble(
                                Employee::getSalary
                        ));

        System.out.println("Count   : " + statistics.getCount());
        System.out.println("Sum     : " + statistics.getSum());
        System.out.println("Min     : " + statistics.getMin());
        System.out.println("Max     : " + statistics.getMax());
        System.out.println("Average : " + statistics.getAverage());


        // ============================================================
        // 38. Find duplicate employee names
        // ============================================================

        System.out.println("\n38. Duplicate employee names");

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getName,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);


        // ============================================================
        // 39. Find duplicate salaries and employees having them
        // ============================================================

        System.out.println("\n39. Employees having duplicate salaries");

        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getSalary
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e ->
                        System.out.println(
                                "Salary " + e.getKey()
                                        + " -> " + e.getValue()
                        )
                );


        // ============================================================
        // 40. Count employees whose salary is above average
        // ============================================================

        System.out.println("\n40. Count above-average employees");

        long aboveAverageCount = employees.stream()
                .filter(e -> e.getSalary() > averageSalary)
                .count();

        System.out.println(aboveAverageCount);


        // ============================================================
        // 41. Find employee whose salary is closest to average
        // ============================================================

        System.out.println("\n41. Employee closest to average salary");

        Employee closestToAverage = employees.stream()
                .min(Comparator.comparingDouble(
                        e -> Math.abs(e.getSalary() - averageSalary)
                ))
                .orElse(null);

        System.out.println(closestToAverage);


        // ============================================================
        // 42. Sort by department, then salary descending
        // ============================================================

        System.out.println("\n42. Sort by department and salary");

        employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getDepartment)
                                .thenComparing(
                                        Comparator.comparingDouble(
                                                Employee::getSalary
                                        ).reversed()
                                )
                )
                .forEach(System.out::println);


        // ============================================================
        // 43. Find employee with maximum salary using reduce
        // ============================================================

        System.out.println("\n43. Maximum salary using reduce");

        Optional<Employee> maxUsingReduce = employees.stream()
                .reduce((e1, e2) ->
                        e1.getSalary() > e2.getSalary()
                                ? e1
                                : e2
                );

        System.out.println(maxUsingReduce.orElse(null));


        // ============================================================
        // 44. Calculate total salary using reduce
        // ============================================================

        System.out.println("\n44. Total salary using reduce");

        double totalUsingReduce = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        System.out.println(totalUsingReduce);


        // ============================================================
        // 45. Get distinct departments
        // ============================================================

        System.out.println("\n45. Distinct departments");

        List<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(departments);


        // ============================================================
        // 46. Find number of distinct departments
        // ============================================================

        System.out.println("\n46. Number of departments");

        long departmentCount = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .count();

        System.out.println(departmentCount);


        // ============================================================
        // 47. Find employees whose name starts with 'A'
        // ============================================================

        System.out.println("\n47. Names starting with A");

        employees.stream()
                .filter(e -> e.getName().startsWith("A"))
                .forEach(System.out::println);


        // ============================================================
        // 48. Find employees whose name contains 'a'
        // ============================================================

        System.out.println("\n48. Names containing 'a'");

        employees.stream()
                .filter(e -> e.getName()
                        .toLowerCase()
                        .contains("a"))
                .forEach(System.out::println);


        // ============================================================
        // 49. Find highest salary from each department and return names
        // ============================================================

        System.out.println("\n49. Highest-paid employee name by department");

        Map<String, String> highestNameByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,

                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(
                                                        Employee::getSalary
                                                )
                                        ),
                                        optional -> optional
                                                .map(Employee::getName)
                                                .orElse(null)
                                )
                        ));

        System.out.println(highestNameByDepartment);


        // ============================================================
        // 50. Get complete salary statistics per department
        // ============================================================

        System.out.println("\n50. Salary statistics per department");

        Map<String, DoubleSummaryStatistics> statsByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summarizingDouble(
                                        Employee::getSalary
                                )
                        ));

        statsByDepartment.forEach((dept, stats) -> {

            System.out.println(
                    dept +
                            " -> count=" + stats.getCount() +
                            ", sum=" + stats.getSum() +
                            ", min=" + stats.getMin() +
                            ", max=" + stats.getMax() +
                            ", avg=" + stats.getAverage()
            );
        });
    }
}