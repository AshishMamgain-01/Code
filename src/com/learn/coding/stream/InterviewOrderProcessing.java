package com.learn.coding.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;


public class InterviewOrderProcessing {


    static class Order {

        private int orderId;
        private String customerName;
        private double amount;
        private String status;

        public Order(int orderId, String customerName,
                     double amount, String status) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.amount = amount;
            this.status = status;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public double getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return orderId + " - " +
                    customerName + " (" +
                    amount + ", " +
                    status + ")";
        }
    }


    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(

                new Order(101, "Ashish", 2500, "NEW"),
                new Order(102, "Ravi", 5000, "DELIVERED"),
                new Order(103, "Ashish", 1500, "PROCESSING"),
                new Order(104, "Meera", 7000, "DELIVERED"),
                new Order(105, "Ravi", 3000, "NEW"),
                new Order(106, "Ashish", 4500, "DELIVERED"),

                // Additional data to make interview questions interesting
                new Order(107, "Meera", 2500, "PROCESSING"),
                new Order(108, "Ravi", 5000, "DELIVERED"),
                new Order(109, "Ashish", 7000, "NEW")
        );


        // ============================================================
        // 1. Find all orders above amount 4000
        // ============================================================

        System.out.println("\n1. Orders above 4000");

        orders.stream()
                .filter(o -> o.getAmount() > 4000)
                .forEach(System.out::println);


        // ============================================================
        // 2. Find orders between 2000 and 5000
        // ============================================================

        System.out.println("\n2. Orders between 2000 and 5000");

        orders.stream()
                .filter(o -> o.getAmount() >= 2000
                        && o.getAmount() <= 5000)
                .forEach(System.out::println);


        // ============================================================
        // 3. Find all DELIVERED orders
        // ============================================================

        System.out.println("\n3. Delivered orders");

        orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .forEach(System.out::println);


        // ============================================================
        // 4. Find all NEW orders
        // ============================================================

        System.out.println("\n4. New orders");

        orders.stream()
                .filter(o -> "NEW".equals(o.getStatus()))
                .forEach(System.out::println);


        // ============================================================
        // 5. Find Ashish's orders
        // ============================================================

        System.out.println("\n5. Ashish orders");

        orders.stream()
                .filter(o -> "Ashish".equals(o.getCustomerName()))
                .forEach(System.out::println);


        // ============================================================
        // 6. Get all customer names
        // ============================================================

        System.out.println("\n6. Customer names");

        orders.stream()
                .map(Order::getCustomerName)
                .forEach(System.out::println);


        // ============================================================
        // 7. Get distinct customer names
        // ============================================================

        System.out.println("\n7. Distinct customers");

        List<String> customers = orders.stream()
                .map(Order::getCustomerName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(customers);


        // ============================================================
        // 8. Get all order IDs
        // ============================================================

        System.out.println("\n8. Order IDs");

        List<Integer> orderIds = orders.stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());

        System.out.println(orderIds);


        // ============================================================
        // 9. Total sales amount
        // ============================================================

        System.out.println("\n9. Total sales");

        double totalSales = orders.stream()
                .mapToDouble(Order::getAmount)
                .sum();

        System.out.println(totalSales);


        // ============================================================
        // 10. Average order value
        // ============================================================

        System.out.println("\n10. Average order value");

        double avgOrderValue = orders.stream()
                .mapToDouble(Order::getAmount)
                .average()
                .orElse(0.0);

        System.out.println(avgOrderValue);


        // ============================================================
        // 11. Highest-value order
        // ============================================================

        System.out.println("\n11. Highest-value order");

        Order highestOrder = orders.stream()
                .max(Comparator.comparingDouble(Order::getAmount))
                .orElse(null);

        System.out.println(highestOrder);


        // ============================================================
        // 12. Lowest-value order
        // ============================================================

        System.out.println("\n12. Lowest-value order");

        Order lowestOrder = orders.stream()
                .min(Comparator.comparingDouble(Order::getAmount))
                .orElse(null);

        System.out.println(lowestOrder);


        // ============================================================
        // 13. Top 3 highest-value orders
        // ============================================================

        System.out.println("\n13. Top 3 orders");

        orders.stream()
                .sorted(Comparator
                        .comparingDouble(Order::getAmount)
                        .reversed())
                .limit(3)
                .forEach(System.out::println);


        // ============================================================
        // 14. Top 3 orders using distinct amounts
        // ============================================================

        System.out.println("\n14. Top 3 distinct amounts");

        orders.stream()
                .map(Order::getAmount)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .forEach(System.out::println);


        // ============================================================
        // 15. Second-highest distinct order amount
        // ============================================================

        System.out.println("\n15. Second-highest distinct amount");

        double secondHighestAmount = orders.stream()
                .map(Order::getAmount)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0.0);

        System.out.println(secondHighestAmount);


        // ============================================================
        // 16. Third-highest distinct order amount
        // ============================================================

        System.out.println("\n16. Third-highest distinct amount");

        double thirdHighestAmount = orders.stream()
                .map(Order::getAmount)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElse(0.0);

        System.out.println(thirdHighestAmount);


        // ============================================================
        // 17. Second-highest order
        // ============================================================

        System.out.println("\n17. Second-highest order");

        Order secondHighestOrder = orders.stream()
                .sorted(Comparator
                        .comparingDouble(Order::getAmount)
                        .reversed())
                .skip(1)
                .findFirst()
                .orElse(null);

        System.out.println(secondHighestOrder);


        // ============================================================
        // 18. Sort orders by amount ascending
        // ============================================================

        System.out.println("\n18. Sort ascending");

        orders.stream()
                .sorted(Comparator.comparingDouble(Order::getAmount))
                .forEach(System.out::println);


        // ============================================================
        // 19. Sort orders by amount descending
        // ============================================================

        System.out.println("\n19. Sort descending");

        orders.stream()
                .sorted(Comparator
                        .comparingDouble(Order::getAmount)
                        .reversed())
                .forEach(System.out::println);


        // ============================================================
        // 20. Sort by customer name
        // ============================================================

        System.out.println("\n20. Sort by customer");

        orders.stream()
                .sorted(Comparator.comparing(Order::getCustomerName))
                .forEach(System.out::println);


        // ============================================================
        // 21. Sort by customer and then amount descending
        // ============================================================

        System.out.println("\n21. Sort by customer + amount");

        orders.stream()
                .sorted(
                        Comparator.comparing(Order::getCustomerName)
                                .thenComparing(
                                        Comparator.comparingDouble(
                                                Order::getAmount
                                        ).reversed()
                                )
                )
                .forEach(System.out::println);


        // ============================================================
        // 22. Group orders by status
        // ============================================================

        System.out.println("\n22. Group by status");

        Map<String, List<Order>> byStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus
                        ));

        System.out.println(byStatus);


        // ============================================================
        // 23. Count orders per status
        // ============================================================

        System.out.println("\n23. Count by status");

        Map<String, Long> countByStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus,
                                Collectors.counting()
                        ));

        System.out.println(countByStatus);


        // ============================================================
        // 24. Group orders by customer
        // ============================================================

        System.out.println("\n24. Group by customer");

        Map<String, List<Order>> byCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName
                        ));

        System.out.println(byCustomer);


        // ============================================================
        // 25. Count orders per customer
        // ============================================================

        System.out.println("\n25. Count orders per customer");

        Map<String, Long> countByCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.counting()
                        ));

        System.out.println(countByCustomer);


        // ============================================================
        // 26. Total amount spent per customer
        // ============================================================

        System.out.println("\n26. Total spent per customer");

        Map<String, Double> totalByCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.summingDouble(
                                        Order::getAmount
                                )
                        ));

        System.out.println(totalByCustomer);


        // ============================================================
        // 27. Average order value per customer
        // ============================================================

        System.out.println("\n27. Average order value per customer");

        Map<String, Double> averageByCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.averagingDouble(
                                        Order::getAmount
                                )
                        ));

        System.out.println(averageByCustomer);


        // ============================================================
        // 28. Customer with highest total spending
        // ============================================================

        System.out.println("\n28. Top customer");

        Map.Entry<String, Double> topCustomer =
                totalByCustomer.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(topCustomer);


        // ============================================================
        // 29. Customer with lowest total spending
        // ============================================================

        System.out.println("\n29. Lowest spending customer");

        Map.Entry<String, Double> lowestCustomer =
                totalByCustomer.entrySet()
                        .stream()
                        .min(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(lowestCustomer);


        // ============================================================
        // 30. Top 2 customers by total spending
        // ============================================================

        System.out.println("\n30. Top 2 customers");

        totalByCustomer.entrySet()
                .stream()
                .sorted(Map.Entry
                        .<String, Double>comparingByValue()
                        .reversed())
                .limit(2)
                .forEach(System.out::println);


        // ============================================================
        // 31. Total sales by status
        // ============================================================

        System.out.println("\n31. Total sales by status");

        Map<String, Double> totalByStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus,
                                Collectors.summingDouble(
                                        Order::getAmount
                                )
                        ));

        System.out.println(totalByStatus);


        // ============================================================
        // 32. Average order amount by status
        // ============================================================

        System.out.println("\n32. Average amount by status");

        Map<String, Double> avgByStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus,
                                Collectors.averagingDouble(
                                        Order::getAmount
                                )
                        ));

        System.out.println(avgByStatus);


        // ============================================================
        // 33. Highest-value order by status
        // ============================================================

        System.out.println("\n33. Highest order by status");

        Map<String, Optional<Order>> highestByStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus,
                                Collectors.maxBy(
                                        Comparator.comparingDouble(
                                                Order::getAmount
                                        )
                                )
                        ));

        System.out.println(highestByStatus);


        // ============================================================
        // 34. Lowest-value order by status
        // ============================================================

        System.out.println("\n34. Lowest order by status");

        Map<String, Optional<Order>> lowestByStatus =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getStatus,
                                Collectors.minBy(
                                        Comparator.comparingDouble(
                                                Order::getAmount
                                        )
                                )
                        ));

        System.out.println(lowestByStatus);


        // ============================================================
        // 35. Partition delivered vs not delivered
        // ============================================================

        System.out.println("\n35. Delivered vs Not Delivered");

        Map<Boolean, List<Order>> partitioned =
                orders.stream()
                        .collect(Collectors.partitioningBy(
                                o -> "DELIVERED".equals(o.getStatus())
                        ));

        System.out.println(partitioned);


        // ============================================================
        // 36. Count delivered vs not delivered
        // ============================================================

        System.out.println("\n36. Count delivered vs not delivered");

        Map<Boolean, Long> deliveredCount =
                orders.stream()
                        .collect(Collectors.partitioningBy(
                                o -> "DELIVERED".equals(o.getStatus()),
                                Collectors.counting()
                        ));

        System.out.println(deliveredCount);


        // ============================================================
        // 37. Total delivered sales
        // ============================================================

        System.out.println("\n37. Total delivered sales");

        double deliveredSales = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .mapToDouble(Order::getAmount)
                .sum();

        System.out.println(deliveredSales);


        // ============================================================
        // 38. Find duplicate order amounts
        // ============================================================

        System.out.println("\n38. Duplicate amounts");

        orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getAmount,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);


        // ============================================================
        // 39. Find orders having duplicate amounts
        // ============================================================

        System.out.println("\n39. Orders having duplicate amounts");

        Map<Double, List<Order>> amountGroups =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getAmount
                        ));

        amountGroups.entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(System.out::println);


        // ============================================================
        // 40. Check if any order is above 10000
        // ============================================================

        System.out.println("\n40. Any order > 10000?");

        boolean anyAbove10000 = orders.stream()
                .anyMatch(o -> o.getAmount() > 10000);

        System.out.println(anyAbove10000);


        // ============================================================
        // 41. Check if all orders are above 1000
        // ============================================================

        System.out.println("\n41. All orders > 1000?");

        boolean allAbove1000 = orders.stream()
                .allMatch(o -> o.getAmount() > 1000);

        System.out.println(allAbove1000);


        // ============================================================
        // 42. Check if no order is below 1000
        // ============================================================

        System.out.println("\n42. No order < 1000?");

        boolean noneBelow1000 = orders.stream()
                .noneMatch(o -> o.getAmount() < 1000);

        System.out.println(noneBelow1000);


        // ============================================================
        // 43. Find first delivered order
        // ============================================================

        System.out.println("\n43. First delivered order");

        Order firstDelivered = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .findFirst()
                .orElse(null);

        System.out.println(firstDelivered);


        // ============================================================
        // 44. Find any delivered order
        // ============================================================

        System.out.println("\n44. Any delivered order");

        Order anyDelivered = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .findAny()
                .orElse(null);

        System.out.println(anyDelivered);


        // ============================================================
        // 45. Get order IDs as comma-separated string
        // ============================================================

        System.out.println("\n45. Order IDs as string");

        String orderIdString = orders.stream()
                .map(Order::getOrderId)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.println(orderIdString);


        // ============================================================
        // 46. Get customer names as comma-separated string
        // ============================================================

        System.out.println("\n46. Customers as string");

        String customerString = orders.stream()
                .map(Order::getCustomerName)
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println(customerString);


        // ============================================================
        // 47. Convert order list to Map<OrderId, Order>
        // ============================================================

        System.out.println("\n47. Order ID -> Order");

        Map<Integer, Order> orderMap =
                orders.stream()
                        .collect(Collectors.toMap(
                                Order::getOrderId,
                                Function.identity()
                        ));

        System.out.println(orderMap);


        // ============================================================
        // 48. Convert order list to Map<OrderId, Amount>
        // ============================================================

        System.out.println("\n48. Order ID -> Amount");

        Map<Integer, Double> orderAmountMap =
                orders.stream()
                        .collect(Collectors.toMap(
                                Order::getOrderId,
                                Order::getAmount
                        ));

        System.out.println(orderAmountMap);


        // ============================================================
        // 49. Get distinct order amounts
        // ============================================================

        System.out.println("\n49. Distinct amounts");

        orders.stream()
                .map(Order::getAmount)
                .distinct()
                .forEach(System.out::println);


        // ============================================================
        // 50. Count distinct customers
        // ============================================================

        System.out.println("\n50. Number of distinct customers");

        long distinctCustomerCount = orders.stream()
                .map(Order::getCustomerName)
                .distinct()
                .count();

        System.out.println(distinctCustomerCount);


        // ============================================================
        // 51. Find customer with maximum number of orders
        // ============================================================

        System.out.println("\n51. Customer with most orders");

        Map.Entry<String, Long> customerWithMostOrders =
                countByCustomer.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(customerWithMostOrders);


        // ============================================================
        // 52. Find customer with minimum number of orders
        // ============================================================

        System.out.println("\n52. Customer with least orders");

        Map.Entry<String, Long> customerWithLeastOrders =
                countByCustomer.entrySet()
                        .stream()
                        .min(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(customerWithLeastOrders);


        // ============================================================
        // 53. Find customers whose total spending > 5000
        // ============================================================

        System.out.println("\n53. Customers spending > 5000");

        totalByCustomer.entrySet()
                .stream()
                .filter(e -> e.getValue() > 5000)
                .forEach(System.out::println);


        // ============================================================
        // 54. Find customers with more than 2 orders
        // ============================================================

        System.out.println("\n54. Customers with > 2 orders");

        countByCustomer.entrySet()
                .stream()
                .filter(e -> e.getValue() > 2)
                .forEach(System.out::println);


        // ============================================================
        // 55. Salary-style statistics for order amounts
        // ============================================================

        System.out.println("\n55. Order amount statistics");

        DoubleSummaryStatistics statistics =
                orders.stream()
                        .collect(Collectors.summarizingDouble(
                                Order::getAmount
                        ));

        System.out.println("Count   : " + statistics.getCount());
        System.out.println("Sum     : " + statistics.getSum());
        System.out.println("Min     : " + statistics.getMin());
        System.out.println("Max     : " + statistics.getMax());
        System.out.println("Average : " + statistics.getAverage());


        // ============================================================
        // 56. Statistics per customer
        // ============================================================

        System.out.println("\n56. Statistics by customer");

        Map<String, DoubleSummaryStatistics> statsByCustomer =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.summarizingDouble(
                                        Order::getAmount
                                )
                        ));

        statsByCustomer.forEach((customer, stats) -> {

            System.out.println(
                    customer +
                            " -> count=" + stats.getCount() +
                            ", sum=" + stats.getSum() +
                            ", min=" + stats.getMin() +
                            ", max=" + stats.getMax() +
                            ", avg=" + stats.getAverage()
            );
        });


        // ============================================================
        // 57. Maximum order using reduce()
        // ============================================================

        System.out.println("\n57. Maximum order using reduce");

        Optional<Order> maxUsingReduce =
                orders.stream()
                        .reduce((o1, o2) ->
                                o1.getAmount() > o2.getAmount()
                                        ? o1
                                        : o2
                        );

        System.out.println(maxUsingReduce.orElse(null));


        // ============================================================
        // 58. Total sales using reduce()
        // ============================================================

        System.out.println("\n58. Total sales using reduce");

        double totalUsingReduce =
                orders.stream()
                        .map(Order::getAmount)
                        .reduce(0.0, Double::sum);

        System.out.println(totalUsingReduce);


        // ============================================================
        // 59. Find largest delivered order
        // ============================================================

        System.out.println("\n59. Largest delivered order");

        Order largestDelivered = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .max(Comparator.comparingDouble(Order::getAmount))
                .orElse(null);

        System.out.println(largestDelivered);


        // ============================================================
        // 60. Find average delivered order value
        // ============================================================

        System.out.println("\n60. Average delivered order");

        double avgDelivered = orders.stream()
                .filter(o -> "DELIVERED".equals(o.getStatus()))
                .mapToDouble(Order::getAmount)
                .average()
                .orElse(0.0);

        System.out.println(avgDelivered);


        // ============================================================
        // 61. Find top customer based only on delivered orders
        // ============================================================

        System.out.println("\n61. Top delivered customer");

        Map<String, Double> deliveredByCustomer =
                orders.stream()
                        .filter(o -> "DELIVERED".equals(o.getStatus()))
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.summingDouble(
                                        Order::getAmount
                                )
                        ));

        Map.Entry<String, Double> topDeliveredCustomer =
                deliveredByCustomer.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(topDeliveredCustomer);


        // ============================================================
        // 62. Find customer with highest average order value
        // ============================================================

        System.out.println("\n62. Customer with highest average order value");

        Map<String, Double> customerAverage =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomerName,
                                Collectors.averagingDouble(
                                        Order::getAmount
                                )
                        ));

        Map.Entry<String, Double> highestAverageCustomer =
                customerAverage.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(highestAverageCustomer);


        // ============================================================
        // 63. Find orders above the overall average
        // ============================================================

        System.out.println("\n63. Orders above average");

        orders.stream()
                .filter(o -> o.getAmount() > avgOrderValue)
                .forEach(System.out::println);


        // ============================================================
        // 64. Find status with highest total sales
        // ============================================================

        System.out.println("\n64. Status with highest sales");

        Map.Entry<String, Double> highestSalesStatus =
                totalByStatus.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(highestSalesStatus);


        // ============================================================
        // 65. Find status having the most orders
        // ============================================================

        System.out.println("\n65. Status with most orders");

        Map.Entry<String, Long> mostOrdersStatus =
                countByStatus.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(mostOrdersStatus);
    }
}