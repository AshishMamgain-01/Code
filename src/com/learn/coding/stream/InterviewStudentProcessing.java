package com.learn.coding.stream;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewStudentProcessing {

    // ============================================================
    // Student - Static Inner Class
    // ============================================================

    static class Student {

        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return name + " (" + score + ")";
        }
    }


    public static void main(String[] args) {

        List<Student> students = Arrays.asList(new Student("Alice", 85), new Student("Bob", 92), new Student("Charlie", 70), new Student("David", 60), new Student("Eve", 88), new Student("Frank", 92), new Student("Grace", 75), new Student("Henry", 85));


        // ============================================================
        // 1. Find student with highest score
        // ============================================================

        System.out.println("\n1. Highest scorer");

        Student highest = students.stream().max(Comparator.comparingInt(Student::getScore)).orElse(null);

        System.out.println(highest);


        // ============================================================
        // 2. Find student with lowest score
        // ============================================================

        System.out.println("\n2. Lowest scorer");

        Student lowest = students.stream().min(Comparator.comparingInt(Student::getScore)).orElse(null);

        System.out.println(lowest);


        // ============================================================
        // 3. Calculate average score
        // ============================================================

        System.out.println("\n3. Average score");

        double averageScore = students.stream().mapToInt(Student::getScore).average().orElse(0.0);

        System.out.println(averageScore);


        // ============================================================
        // 4. Find students below average
        // ============================================================

        System.out.println("\n4. Students below average");

        students.stream().filter(s -> s.getScore() < averageScore).forEach(System.out::println);


        // ============================================================
        // 5. Find students above or equal to average
        // ============================================================

        System.out.println("\n5. Students above/equal average");

        students.stream().filter(s -> s.getScore() >= averageScore).forEach(System.out::println);


        // ============================================================
        // 6. Find top 3 students
        // ============================================================

        System.out.println("\n6. Top 3 students");

        students.stream().sorted(Comparator.comparingInt(Student::getScore).reversed()).limit(3).forEach(System.out::println);


        // ============================================================
        // 7. Find top 3 DISTINCT scores
        // ============================================================

        System.out.println("\n7. Top 3 distinct scores");

        students.stream().map(Student::getScore).distinct().sorted(Comparator.reverseOrder()).limit(3).forEach(System.out::println);


        // ============================================================
        // 8. Find second-highest DISTINCT score
        // ============================================================

        System.out.println("\n8. Second-highest distinct score");

        int secondHighest = students.stream().map(Student::getScore).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);

        System.out.println(secondHighest);


        // ============================================================
        // 9. Find student(s) having second-highest score
        // ============================================================

        System.out.println("\n9. Students with second-highest score");

        students.stream().filter(s -> s.getScore() == secondHighest).forEach(System.out::println);


        // ============================================================
        // 10. Find third-highest DISTINCT score
        // ============================================================

        System.out.println("\n10. Third-highest distinct score");

        int thirdHighest = students.stream().map(Student::getScore).distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst().orElse(-1);

        System.out.println(thirdHighest);


        // ============================================================
        // 11. Find student(s) having third-highest score
        // ============================================================

        System.out.println("\n11. Students with third-highest score");

        students.stream().filter(s -> s.getScore() == thirdHighest).forEach(System.out::println);


        // ============================================================
        // 12. Calculate total score
        // ============================================================

        System.out.println("\n12. Total score");

        int totalScore = students.stream().mapToInt(Student::getScore).sum();

        System.out.println(totalScore);


        // ============================================================
        // 13. Count students
        // ============================================================

        System.out.println("\n13. Number of students");

        long studentCount = students.stream().count();

        System.out.println(studentCount);


        // ============================================================
        // 14. Count students above average
        // ============================================================

        System.out.println("\n14. Count above average");

        long aboveAverageCount = students.stream().filter(s -> s.getScore() > averageScore).count();

        System.out.println(aboveAverageCount);


        // ============================================================
        // 15. Count students below average
        // ============================================================

        System.out.println("\n15. Count below average");

        long belowAverageCount = students.stream().filter(s -> s.getScore() < averageScore).count();

        System.out.println(belowAverageCount);


        // ============================================================
        // 16. Partition students into above/below average
        // ============================================================

        System.out.println("\n16. Partition by average");

        Map<Boolean, List<Student>> partitioned = students.stream().collect(Collectors.partitioningBy(s -> s.getScore() >= averageScore));

        System.out.println("Above/equal average:");
        System.out.println(partitioned.get(true));

        System.out.println("Below average:");
        System.out.println(partitioned.get(false));


        // ============================================================
        // 17. Partition students who passed/failed
        // Assume >= 40 is pass
        // ============================================================

        System.out.println("\n17. Pass / Fail");

        Map<Boolean, List<Student>> passFail = students.stream().collect(Collectors.partitioningBy(s -> s.getScore() >= 40));

        System.out.println("Passed: " + passFail.get(true));
        System.out.println("Failed: " + passFail.get(false));


        // ============================================================
        // 18. Group students by score
        // ============================================================

        System.out.println("\n18. Group students by score");

        Map<Integer, List<Student>> scoreGroups = students.stream().collect(Collectors.groupingBy(Student::getScore));

        System.out.println(scoreGroups);


        // ============================================================
        // 19. Find duplicate scores
        // ============================================================

        System.out.println("\n19. Duplicate scores");

        scoreGroups.entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(System.out::println);


        // ============================================================
        // 20. Count students for each score
        // ============================================================

        System.out.println("\n20. Count students by score");

        Map<Integer, Long> countByScore = students.stream().collect(Collectors.groupingBy(Student::getScore, Collectors.counting()));

        System.out.println(countByScore);


        // ============================================================
        // 21. Find score occurring most frequently
        // ============================================================

        System.out.println("\n21. Most frequent score");

        Map.Entry<Integer, Long> mostFrequentScore = countByScore.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);

        System.out.println(mostFrequentScore);


        // ============================================================
        // 22. Sort students by score ascending
        // ============================================================

        System.out.println("\n22. Sort ascending");

        students.stream().sorted(Comparator.comparingInt(Student::getScore)).forEach(System.out::println);


        // ============================================================
        // 23. Sort students by score descending
        // ============================================================

        System.out.println("\n23. Sort descending");

        students.stream().sorted(Comparator.comparingInt(Student::getScore).reversed()).forEach(System.out::println);


        // ============================================================
        // 24. Sort by score descending and name ascending
        // ============================================================

        System.out.println("\n24. Sort score descending + name ascending");

        students.stream().sorted(Comparator.comparingInt(Student::getScore).reversed().thenComparing(Student::getName)).forEach(System.out::println);


        // ============================================================
        // 25. Find student by name
        // ============================================================

        System.out.println("\n25. Find Alice");

        Student alice = students.stream().filter(s -> "Alice".equals(s.getName())).findFirst().orElse(null);

        System.out.println(alice);


        // ============================================================
        // 26. Find students whose name starts with A
        // ============================================================

        System.out.println("\n26. Names starting with A");

        students.stream().filter(s -> s.getName().startsWith("A")).forEach(System.out::println);


        // ============================================================
        // 27. Find students whose name contains 'a'
        // ============================================================

        System.out.println("\n27. Names containing 'a'");

        students.stream().filter(s -> s.getName().toLowerCase().contains("a")).forEach(System.out::println);


        // ============================================================
        // 28. Get only student names
        // ============================================================

        System.out.println("\n28. Student names");

        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());

        System.out.println(names);


        // ============================================================
        // 29. Get only scores
        // ============================================================

        System.out.println("\n29. Student scores");

        List<Integer> scores = students.stream().map(Student::getScore).collect(Collectors.toList());

        System.out.println(scores);


        // ============================================================
        // 30. Get distinct scores
        // ============================================================

        System.out.println("\n30. Distinct scores");

        students.stream().map(Student::getScore).distinct().forEach(System.out::println);


        // ============================================================
        // 31. Find number of distinct scores
        // ============================================================

        System.out.println("\n31. Number of distinct scores");

        long distinctScoreCount = students.stream().map(Student::getScore).distinct().count();

        System.out.println(distinctScoreCount);


        // ============================================================
        // 32. Find student with score exactly 92
        // ============================================================

        System.out.println("\n32. Students with score 92");

        students.stream().filter(s -> s.getScore() == 92).forEach(System.out::println);


        // ============================================================
        // 33. Check if any student scored 100
        // ============================================================

        System.out.println("\n33. Any student scored 100?");

        boolean any100 = students.stream().anyMatch(s -> s.getScore() == 100);

        System.out.println(any100);


        // ============================================================
        // 34. Check if all students scored >= 40
        // ============================================================

        System.out.println("\n34. All students passed?");

        boolean allPassed = students.stream().allMatch(s -> s.getScore() >= 40);

        System.out.println(allPassed);


        // ============================================================
        // 35. Check if no student scored below 40
        // ============================================================

        System.out.println("\n35. No student failed?");

        boolean noneFailed = students.stream().noneMatch(s -> s.getScore() < 40);

        System.out.println(noneFailed);


        // ============================================================
        // 36. Find first student scoring above 80
        // ============================================================

        System.out.println("\n36. First student above 80");

        Student firstAbove80 = students.stream().filter(s -> s.getScore() > 80).findFirst().orElse(null);

        System.out.println(firstAbove80);


        // ============================================================
        // 37. Find any student scoring above 80
        // ============================================================

        System.out.println("\n37. Any student above 80");

        Student anyAbove80 = students.stream().filter(s -> s.getScore() > 80).findAny().orElse(null);

        System.out.println(anyAbove80);


        // ============================================================
        // 38. Get top scorer names
        // ============================================================

        System.out.println("\n38. Top scorer names");

        int maxScore = students.stream().mapToInt(Student::getScore).max().orElse(-1);

        students.stream().filter(s -> s.getScore() == maxScore).map(Student::getName).forEach(System.out::println);


        // ============================================================
        // 39. Find all students having maximum score
        // ============================================================

        System.out.println("\n39. All highest scorers");

        students.stream().filter(s -> s.getScore() == maxScore).forEach(System.out::println);


        // ============================================================
        // 40. Find all students having minimum score
        // ============================================================

        System.out.println("\n40. All lowest scorers");

        int minScore = students.stream().mapToInt(Student::getScore).min().orElse(-1);

        students.stream().filter(s -> s.getScore() == minScore).forEach(System.out::println);


        // ============================================================
        // 41. Get names of top 3 students
        // ============================================================

        System.out.println("\n41. Names of top 3 students");

        students.stream().sorted(Comparator.comparingInt(Student::getScore).reversed()).limit(3).map(Student::getName).forEach(System.out::println);


        // ============================================================
        // 42. Get names of students above average
        // ============================================================

        System.out.println("\n42. Names above average");

        students.stream().filter(s -> s.getScore() >= averageScore).map(Student::getName).forEach(System.out::println);


        // ============================================================
        // 43. Get names as comma-separated string
        // ============================================================

        System.out.println("\n43. Names as string");

        String nameString = students.stream().map(Student::getName).collect(Collectors.joining(", "));

        System.out.println(nameString);


        // ============================================================
        // 44. Convert Student list to Map<Name, Score>
        // ============================================================

        System.out.println("\n44. Name -> Score");

        Map<String, Integer> nameScoreMap = students.stream().collect(Collectors.toMap(Student::getName, Student::getScore));

        System.out.println(nameScoreMap);


        // ============================================================
        // 45. Find student with score closest to average
        // ============================================================

        System.out.println("\n45. Closest to average");

        Student closestToAverage = students.stream().min(Comparator.comparingInt(s -> (int) Math.abs(s.getScore() - averageScore))).orElse(null);

        System.out.println(closestToAverage);


        // ============================================================
        // 46. Find students scoring above 80 and below 95
        // ============================================================

        System.out.println("\n46. Score between 80 and 95");

        students.stream().filter(s -> s.getScore() > 80 && s.getScore() < 95).forEach(System.out::println);


        // ============================================================
        // 47. Calculate average score of students above 80
        // ============================================================

        System.out.println("\n47. Average score above 80");

        double averageAbove80 = students.stream().filter(s -> s.getScore() > 80).mapToInt(Student::getScore).average().orElse(0.0);

        System.out.println(averageAbove80);


        // ============================================================
        // 48. Calculate sum of scores above average
        // ============================================================

        System.out.println("\n48. Sum of scores above average");

        int sumAboveAverage = students.stream().filter(s -> s.getScore() > averageScore).mapToInt(Student::getScore).sum();

        System.out.println(sumAboveAverage);


        // ============================================================
        // 49. Find top 2 DISTINCT scores
        // ============================================================

        System.out.println("\n49. Top 2 distinct scores");

        students.stream().map(Student::getScore).distinct().sorted(Comparator.reverseOrder()).limit(2).forEach(System.out::println);


        // ============================================================
        // 50. Find students with top 2 DISTINCT scores
        // ============================================================

        System.out.println("\n50. Students with top 2 distinct scores");

        List<Integer> top2Scores = students.stream().map(Student::getScore).distinct().sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());

        students.stream().filter(s -> top2Scores.contains(s.getScore())).forEach(System.out::println);


        // ============================================================
        // 51. Find median score
        // ============================================================

        System.out.println("\n51. Median score");

        List<Integer> sortedScores = students.stream().map(Student::getScore).sorted().collect(Collectors.toList());

        double median;

        int size = sortedScores.size();

        if (size % 2 == 0) {
            median = (sortedScores.get(size / 2 - 1) + sortedScores.get(size / 2)) / 2.0;
        } else {
            median = sortedScores.get(size / 2);
        }

        System.out.println(median);


        // ============================================================
        // 52. Score statistics
        // ============================================================

        System.out.println("\n52. Score statistics");

        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getScore));

        System.out.println("Count   : " + statistics.getCount());
        System.out.println("Sum     : " + statistics.getSum());
        System.out.println("Min     : " + statistics.getMin());
        System.out.println("Max     : " + statistics.getMax());
        System.out.println("Average : " + statistics.getAverage());


        // ============================================================
        // 53. Maximum score using reduce()
        // ============================================================

        System.out.println("\n53. Maximum using reduce");

        Optional<Student> maxUsingReduce = students.stream().reduce((s1, s2) -> s1.getScore() > s2.getScore() ? s1 : s2);

        System.out.println(maxUsingReduce.orElse(null));


        // ============================================================
        // 54. Total score using reduce()
        // ============================================================

        System.out.println("\n54. Total using reduce");

        int totalUsingReduce = students.stream().map(Student::getScore).reduce(0, Integer::sum);

        System.out.println(totalUsingReduce);


        // ============================================================
        // 55. Find highest scorer name directly
        // ============================================================

        System.out.println("\n55. Highest scorer name");

        String highestScorerName = students.stream().max(Comparator.comparingInt(Student::getScore)).map(Student::getName).orElse(null);

        System.out.println(highestScorerName);


        // ============================================================
        // 56. Find lowest scorer name directly
        // ============================================================

        System.out.println("\n56. Lowest scorer name");

        String lowestScorerName = students.stream().min(Comparator.comparingInt(Student::getScore)).map(Student::getName).orElse(null);

        System.out.println(lowestScorerName);


        // ============================================================
        // 57. Find second-highest scorer name(s)
        // ============================================================

        System.out.println("\n57. Second-highest scorer names");

        students.stream().filter(s -> s.getScore() == secondHighest).map(Student::getName).forEach(System.out::println);


        // ============================================================
        // 58. Find students whose score is greater than 90
        // ============================================================

        System.out.println("\n58. Score > 90");

        students.stream().filter(s -> s.getScore() > 90).forEach(System.out::println);


        // ============================================================
        // 59. Find students whose score is between 70 and 90
        // ============================================================

        System.out.println("\n59. Score between 70 and 90");

        students.stream().filter(s -> s.getScore() >= 70 && s.getScore() <= 90).forEach(System.out::println);


        // ============================================================
        // 60. Count students in score ranges
        // ============================================================

        System.out.println("\n60. Group by score range");

        Map<String, Long> scoreRange = students.stream().collect(Collectors.groupingBy(s -> {

            if (s.getScore() >= 90) return "90-100";

            if (s.getScore() >= 80) return "80-89";

            if (s.getScore() >= 70) return "70-79";

            return "Below 70";
        }, Collectors.counting()));

        System.out.println(scoreRange);
    }
}