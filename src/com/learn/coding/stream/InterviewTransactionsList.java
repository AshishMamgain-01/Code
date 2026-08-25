package com.learn.coding.stream;

import java.util.*;
import java.util.stream.*;

public class InterviewTransactionsList {

    // ============================================================
    // Item
    // ============================================================

    static class Item {

        private int itemId;

        Item(int itemId) {
            this.itemId = itemId;
        }

        public int getItemId() {
            return itemId;
        }

        @Override
        public String toString() {
            return String.valueOf(itemId);
        }
    }


    // ============================================================
    // Transaction
    // ============================================================

    static class Transaction {

        private List<Item> items;

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }

        @Override
        public String toString() {
            return items.toString();
        }
    }


    public static void main(String[] args) {

        // ============================================================
        // Test Data
        // ============================================================

        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();

        transaction1.setItems(Arrays.asList(
                new Item(123),
                new Item(223),
                new Item(123),
                new Item(423),
                new Item(123)
        ));

        Transaction transaction2 = new Transaction();

        transaction2.setItems(Arrays.asList(
                new Item(123),
                new Item(523),
                new Item(623)
        ));

        Transaction transaction3 = new Transaction();

        transaction3.setItems(Arrays.asList(
                new Item(223),
                new Item(123),
                new Item(723)
        ));

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);


        // ============================================================
        // 1. Count occurrences of itemId = 123
        // ============================================================

        System.out.println("\n1. Count itemId = 123");

        long count123 = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .filter(i -> i.getItemId() == 123)
                .count();

        System.out.println(count123);


        // ============================================================
        // 2. Get all items from all transactions
        // ============================================================

        System.out.println("\n2. All items");

        List<Item> allItems = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .collect(Collectors.toList());

        System.out.println(allItems);


        // ============================================================
        // 3. Get all item IDs
        // ============================================================

        System.out.println("\n3. All item IDs");

        List<Integer> allItemIds = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .collect(Collectors.toList());

        System.out.println(allItemIds);


        // ============================================================
        // 4. Get distinct item IDs
        // ============================================================

        System.out.println("\n4. Distinct item IDs");

        List<Integer> distinctItemIds = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctItemIds);


        // ============================================================
        // 5. Count distinct item IDs
        // ============================================================

        System.out.println("\n5. Number of distinct items");

        long distinctCount = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .distinct()
                .count();

        System.out.println(distinctCount);


        // ============================================================
        // 6. Find duplicate item IDs
        // ============================================================

        System.out.println("\n6. Duplicate item IDs");

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .collect(Collectors.groupingBy(
                        id -> id,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);


        // ============================================================
        // 7. Count occurrence of every item ID
        // ============================================================

        System.out.println("\n7. Item ID frequency");

        Map<Integer, Long> itemFrequency =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .map(Item::getItemId)
                        .collect(Collectors.groupingBy(
                                id -> id,
                                Collectors.counting()
                        ));

        System.out.println(itemFrequency);


        // ============================================================
        // 8. Find most frequently occurring item
        // ============================================================

        System.out.println("\n8. Most frequent item");

        Map.Entry<Integer, Long> mostFrequentItem =
                itemFrequency.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(mostFrequentItem);


        // ============================================================
        // 9. Find least frequently occurring item
        // ============================================================

        System.out.println("\n9. Least frequent item");

        Map.Entry<Integer, Long> leastFrequentItem =
                itemFrequency.entrySet()
                        .stream()
                        .min(Map.Entry.comparingByValue())
                        .orElse(null);

        System.out.println(leastFrequentItem);


        // ============================================================
        // 10. Find all items with ID 123
        // ============================================================

        System.out.println("\n10. Items with ID 123");

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .filter(i -> i.getItemId() == 123)
                .forEach(System.out::println);


        // ============================================================
        // 11. Check whether any transaction contains itemId 123
        // ============================================================

        System.out.println("\n11. Any transaction contains 123?");

        boolean contains123 = transactions.stream()
                .anyMatch(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i -> i.getItemId() == 123)
                );

        System.out.println(contains123);


        // ============================================================
        // 12. Check whether any transaction contains itemId 999
        // ============================================================

        System.out.println("\n12. Any transaction contains 999?");

        boolean contains999 = transactions.stream()
                .anyMatch(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i -> i.getItemId() == 999)
                );

        System.out.println(contains999);


        // ============================================================
        // 13. Find transactions containing itemId 123
        // ============================================================

        System.out.println("\n13. Transactions containing 123");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i -> i.getItemId() == 123)
                )
                .forEach(System.out::println);


        // ============================================================
        // 14. Count transactions containing itemId 123
        // ============================================================

        System.out.println("\n14. Number of transactions containing 123");

        long transactionCount123 = transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i -> i.getItemId() == 123)
                )
                .count();

        System.out.println(transactionCount123);


        // ============================================================
        // 15. Find transaction containing a specific item
        // ============================================================

        System.out.println("\n15. Find first transaction containing 523");

        Transaction transactionWith523 =
                transactions.stream()
                        .filter(t ->
                                t.getItems()
                                        .stream()
                                        .anyMatch(
                                                i -> i.getItemId() == 523
                                        )
                        )
                        .findFirst()
                        .orElse(null);

        System.out.println(transactionWith523);


        // ============================================================
        // 16. Find transactions containing more than 2 items
        // ============================================================

        System.out.println("\n16. Transactions with more than 2 items");

        transactions.stream()
                .filter(t -> t.getItems().size() > 2)
                .forEach(System.out::println);


        // ============================================================
        // 17. Find transaction with maximum number of items
        // ============================================================

        System.out.println("\n17. Transaction with maximum items");

        Transaction largestTransaction =
                transactions.stream()
                        .max(
                                Comparator.comparingInt(
                                        t -> t.getItems().size()
                                )
                        )
                        .orElse(null);

        System.out.println(largestTransaction);


        // ============================================================
        // 18. Find transaction with minimum number of items
        // ============================================================

        System.out.println("\n18. Transaction with minimum items");

        Transaction smallestTransaction =
                transactions.stream()
                        .min(
                                Comparator.comparingInt(
                                        t -> t.getItems().size()
                                )
                        )
                        .orElse(null);

        System.out.println(smallestTransaction);


        // ============================================================
        // 19. Count total number of items
        // ============================================================

        System.out.println("\n19. Total number of items");

        long totalItems = transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .count();

        System.out.println(totalItems);


        // ============================================================
        // 20. Average number of items per transaction
        // ============================================================

        System.out.println("\n20. Average items per transaction");

        double averageItems =
                transactions.stream()
                        .mapToInt(t -> t.getItems().size())
                        .average()
                        .orElse(0.0);

        System.out.println(averageItems);


        // ============================================================
        // 21. Sort all item IDs
        // ============================================================

        System.out.println("\n21. Sort item IDs ascending");

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .sorted()
                .forEach(System.out::println);


        // ============================================================
        // 22. Sort all item IDs descending
        // ============================================================

        System.out.println("\n22. Sort item IDs descending");

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);


        // ============================================================
        // 23. Find maximum item ID
        // ============================================================

        System.out.println("\n23. Maximum item ID");

        int maxItemId =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .mapToInt(Item::getItemId)
                        .max()
                        .orElse(-1);

        System.out.println(maxItemId);


        // ============================================================
        // 24. Find minimum item ID
        // ============================================================

        System.out.println("\n24. Minimum item ID");

        int minItemId =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .mapToInt(Item::getItemId)
                        .min()
                        .orElse(-1);

        System.out.println(minItemId);


        // ============================================================
        // 25. Find second-highest distinct item ID
        // ============================================================

        System.out.println("\n25. Second-highest distinct item ID");

        int secondHighestItem =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .map(Item::getItemId)
                        .distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst()
                        .orElse(-1);

        System.out.println(secondHighestItem);


        // ============================================================
        // 26. Find top 3 distinct item IDs
        // ============================================================

        System.out.println("\n26. Top 3 distinct item IDs");

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .forEach(System.out::println);


        // ============================================================
        // 27. Find all transactions with item ID > 400
        // ============================================================

        System.out.println("\n27. Transactions containing item ID > 400");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i -> i.getItemId() > 400)
                )
                .forEach(System.out::println);


        // ============================================================
        // 28. Find transactions where ALL items have ID > 100
        // ============================================================

        System.out.println("\n28. Transactions where all items > 100");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .allMatch(i -> i.getItemId() > 100)
                )
                .forEach(System.out::println);


        // ============================================================
        // 29. Find transactions where NO item has ID 999
        // ============================================================

        System.out.println("\n29. Transactions with no item 999");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .noneMatch(i -> i.getItemId() == 999)
                )
                .forEach(System.out::println);


        // ============================================================
        // 30. Get first item from every transaction
        // ============================================================

        System.out.println("\n30. First item from each transaction");

        transactions.stream()
                .map(t -> t.getItems().stream().findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);


        // ============================================================
        // 31. Get first item ID from every transaction
        // ============================================================

        System.out.println("\n31. First item ID from every transaction");

        transactions.stream()
                .map(t ->
                        t.getItems()
                                .stream()
                                .map(Item::getItemId)
                                .findFirst()
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);


        // ============================================================
        // 32. Get all item IDs as comma-separated string
        // ============================================================

        System.out.println("\n32. All item IDs as string");

        String itemIds =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .map(Item::getItemId)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));

        System.out.println(itemIds);


        // ============================================================
        // 33. Find item ID frequency and sort by frequency
        // ============================================================

        System.out.println("\n33. Items sorted by frequency");

        itemFrequency.entrySet()
                .stream()
                .sorted(
                        Map.Entry
                                .<Integer, Long>comparingByValue()
                                .reversed()
                )
                .forEach(System.out::println);


        // ============================================================
        // 34. Find top 2 most frequently occurring items
        // ============================================================

        System.out.println("\n34. Top 2 frequent items");

        itemFrequency.entrySet()
                .stream()
                .sorted(
                        Map.Entry
                                .<Integer, Long>comparingByValue()
                                .reversed()
                )
                .limit(2)
                .forEach(System.out::println);


        // ============================================================
        // 35. Find duplicate item IDs using Set
        // ============================================================

        System.out.println("\n35. Duplicate IDs using Set");

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        transactions.stream()
                .flatMap(t -> t.getItems().stream())
                .map(Item::getItemId)
                .forEach(id -> {

                    if (!seen.add(id)) {
                        duplicates.add(id);
                    }
                });

        System.out.println(duplicates);


        // ============================================================
        // 36. Check if duplicate item exists
        // ============================================================

        System.out.println("\n36. Any duplicate item?");

        boolean hasDuplicate =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .map(Item::getItemId)
                        .collect(Collectors.groupingBy(
                                id -> id,
                                Collectors.counting()
                        ))
                        .values()
                        .stream()
                        .anyMatch(count -> count > 1);

        System.out.println(hasDuplicate);


        // ============================================================
        // 37. Find number of unique items
        // ============================================================

        System.out.println("\n37. Number of unique items");

        long uniqueItems =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .map(Item::getItemId)
                        .distinct()
                        .count();

        System.out.println(uniqueItems);


        // ============================================================
        // 38. Find transactions containing duplicate items internally
        // ============================================================

        System.out.println("\n38. Transactions containing duplicate item IDs");

        transactions.stream()
                .filter(t -> {

                    long total = t.getItems().size();

                    long unique = t.getItems()
                            .stream()
                            .map(Item::getItemId)
                            .distinct()
                            .count();

                    return total != unique;
                })
                .forEach(System.out::println);


        // ============================================================
        // 39. Count transactions containing duplicate items
        // ============================================================

        System.out.println("\n39. Number of transactions with duplicates");

        long transactionsWithDuplicates =
                transactions.stream()
                        .filter(t -> {

                            long total = t.getItems().size();

                            long unique = t.getItems()
                                    .stream()
                                    .map(Item::getItemId)
                                    .distinct()
                                    .count();

                            return total != unique;
                        })
                        .count();

        System.out.println(transactionsWithDuplicates);


        // ============================================================
        // 40. Flatten List<List<Item>> into List<Integer>
        // ============================================================

        System.out.println("\n40. Flatten into item IDs");

        List<Integer> flattened =
                transactions.stream()
                        .map(Transaction::getItems)
                        .flatMap(List::stream)
                        .map(Item::getItemId)
                        .collect(Collectors.toList());

        System.out.println(flattened);


        // ============================================================
        // 41. Demonstrate map() vs flatMap()
        // ============================================================

        System.out.println("\n41. map vs flatMap");

        // map gives Stream<List<Item>>
        List<List<Item>> usingMap =
                transactions.stream()
                        .map(Transaction::getItems)
                        .collect(Collectors.toList());

        System.out.println("Using map: " + usingMap);

        // flatMap gives Stream<Item>
        List<Item> usingFlatMap =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .collect(Collectors.toList());

        System.out.println("Using flatMap: " + usingFlatMap);


        // ============================================================
        // 42. Count item 123 using nested streams
        // ============================================================

        System.out.println("\n42. Count 123 using nested stream");

        long nestedCount =
                transactions.stream()
                        .mapToLong(t ->
                                t.getItems()
                                        .stream()
                                        .filter(i -> i.getItemId() == 123)
                                        .count()
                        )
                        .sum();

        System.out.println(nestedCount);


        // ============================================================
        // 43. Count item 123 using flatMap
        // ============================================================

        System.out.println("\n43. Count 123 using flatMap");

        long flatMapCount =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .filter(i -> i.getItemId() == 123)
                        .count();

        System.out.println(flatMapCount);


        // ============================================================
        // 44. Find transaction with maximum distinct item count
        // ============================================================

        System.out.println("\n44. Transaction with maximum unique items");

        Transaction maxUniqueItems =
                transactions.stream()
                        .max(
                                Comparator.comparingLong(
                                        t -> t.getItems()
                                                .stream()
                                                .map(Item::getItemId)
                                                .distinct()
                                                .count()
                                )
                        )
                        .orElse(null);

        System.out.println(maxUniqueItems);


        // ============================================================
        // 45. Find transactions containing BOTH 123 and 223
        // ============================================================

        System.out.println("\n45. Transactions containing 123 and 223");

        transactions.stream()
                .filter(t -> {

                    Set<Integer> ids = t.getItems()
                            .stream()
                            .map(Item::getItemId)
                            .collect(Collectors.toSet());

                    return ids.contains(123)
                            && ids.contains(223);
                })
                .forEach(System.out::println);


        // ============================================================
        // 46. Find transactions containing 123 OR 223
        // ============================================================

        System.out.println("\n46. Transactions containing 123 or 223");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .anyMatch(i ->
                                        i.getItemId() == 123
                                                || i.getItemId() == 223
                                )
                )
                .forEach(System.out::println);


        // ============================================================
        // 47. Find transactions containing exactly 123
        // ============================================================

        System.out.println("\n47. Transactions containing only item 123");

        transactions.stream()
                .filter(t ->
                        t.getItems()
                                .stream()
                                .allMatch(
                                        i -> i.getItemId() == 123
                                )
                )
                .forEach(System.out::println);


        // ============================================================
        // 48. Find the transaction containing the most occurrences
        //     of item 123
        // ============================================================

        System.out.println("\n48. Transaction with most 123 items");

        Transaction most123 =
                transactions.stream()
                        .max(
                                Comparator.comparingLong(
                                        t -> t.getItems()
                                                .stream()
                                                .filter(i ->
                                                        i.getItemId() == 123
                                                )
                                                .count()
                                )
                        )
                        .orElse(null);

        System.out.println(most123);


        // ============================================================
        // 49. Count total occurrences of every item
        // ============================================================

        System.out.println("\n49. Frequency of every item");

        Map<Integer, Long> frequency =
                transactions.stream()
                        .flatMap(t -> t.getItems().stream())
                        .collect(Collectors.groupingBy(
                                Item::getItemId,
                                Collectors.counting()
                        ));

        System.out.println(frequency);


        // ============================================================
        // 50. Find item IDs occurring exactly once
        // ============================================================

        System.out.println("\n50. Items occurring exactly once");

        frequency.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}