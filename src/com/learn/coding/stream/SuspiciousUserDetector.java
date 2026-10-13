package com.learn.coding.stream;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class ListEntry {
    private String userId;
    private String action;
    private LocalDateTime time;

    public ListEntry(String userId, String action, LocalDateTime time) {
        this.userId = userId;
        this.action = action;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

public class SuspiciousUserDetector {

    public static Set<String> findSuspiciousUsers(
            List<ListEntry> entries) {

        Map<String, List<LocalDateTime>> failedActions =
                entries.stream()
                    .filter(e -> "failed".equalsIgnoreCase(e.getAction()))
                    .collect(Collectors.groupingBy(
                        ListEntry::getUserId,
                        Collectors.mapping(
                            ListEntry::getTime,
                            Collectors.toList()
                        )
                    ));

        Set<String> suspiciousUsers = new HashSet<>();

        for (Map.Entry<String, List<LocalDateTime>> entry
                : failedActions.entrySet()) {

            List<LocalDateTime> times = entry.getValue();
            Collections.sort(times);

            for (int i = 0; i < times.size(); i++) {
                LocalDateTime windowEnd = times.get(i);
                LocalDateTime windowStart = windowEnd.minusMinutes(5);

                long count = times.stream()
                    .filter(t -> !t.isBefore(windowStart)
                              && !t.isAfter(windowEnd))
                    .count();

                if (count > 3) {
                    suspiciousUsers.add(entry.getKey());
                    break;
                }
            }
        }

        return suspiciousUsers;
    }
}