package com.learn.coding.concurrent.executors;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    // Java Records
    record Customer(int id, String name) {}

    record Order(int orderId, String item, double amount) {}

    record Payment(String paymentId, String status) {}

    record User(int id, String name) {}

    record ProcessedUser(String message) {}

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFutureExample service = new CompletableFutureExample();

        // Run all APIs in parallel
        CompletableFuture<Customer> customerFuture =
                CompletableFuture.supplyAsync(service::getCustomer, executor);

        CompletableFuture<List<Order>> ordersFuture =
                CompletableFuture.supplyAsync(service::getOrders, executor);

        CompletableFuture<Payment> paymentFuture =
                CompletableFuture.supplyAsync(service::getPayment, executor);

        // Wait for all futures
        CompletableFuture<Void> all =
                CompletableFuture.allOf(customerFuture, ordersFuture, paymentFuture);

        all.thenRun(() -> {

            Customer customer = customerFuture.join();
            List<Order> orders = ordersFuture.join();
            Payment payment = paymentFuture.join();

            System.out.println("\n===== Dashboard =====");
            System.out.println(customer);
            System.out.println(orders);
            System.out.println(payment);

        }).join();

        // Chaining example
        CompletableFuture
                .supplyAsync(service::getUser, executor)
                .thenApply(service::processUser)
                .thenAccept(System.out::println)
                .join();

        executor.shutdown();
    }

    // ---------------- API Methods ----------------

    Customer getCustomer() {
        sleep(1000);
        System.out.println(Thread.currentThread().getName()
                + " -> Fetching Customer");
        return new Customer(101, "Ashish");
    }

    List<Order> getOrders() {
        sleep(2000);
        System.out.println(Thread.currentThread().getName()
                + " -> Fetching Orders");

        return List.of(
                new Order(1, "Laptop", 75000),
                new Order(2, "Mouse", 1500));
    }

    Payment getPayment() {
        sleep(1500);
        System.out.println(Thread.currentThread().getName()
                + " -> Fetching Payment");
        return new Payment("PAY123", "SUCCESS");
    }

    User getUser() {
        sleep(1000);
        System.out.println(Thread.currentThread().getName()
                + " -> Fetching User");
        return new User(10, "John");
    }

    ProcessedUser processUser(User user) {
        System.out.println(Thread.currentThread().getName()
                + " -> Processing User");
        return new ProcessedUser("Welcome " + user.name());
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}