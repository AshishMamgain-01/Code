package com.learn.coding.spring.mini;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter + sort + pagination without Spring MVC. CNC REST talking point:
 * offset is simple; keyset is what you want on large inventory lists.
 *
 * See interview-prep/spring/REST.md
 */
public class RestPaginationDemo {

	public static void main(String[] args) {
		List<DeviceRow> all = List.of(
				new DeviceRow("d3", "NCS1004", "optical"),
				new DeviceRow("d1", "ASR9K", "router"),
				new DeviceRow("d2", "NCS1001", "optical"),
				new DeviceRow("d4", "MX960", "router"),
				new DeviceRow("d5", "NCS2K", "optical"));

		List<DeviceRow> optical = all.stream()
				.filter(d -> d.family().equals("optical"))
				.sorted(Comparator.comparing(DeviceRow::name))
				.collect(Collectors.toList());

		Page offset = offsetPage(optical, 0, 2);
		System.out.println("offset page0 size2: " + offset.items() + " hasMore=" + offset.hasMore());

		Page keyset = keysetPage(optical, "NCS1001", 2);
		System.out.println("keyset after NCS1001: " + keyset.items() + " hasMore=" + keyset.hasMore());
		System.out.println("Prefer keyset at scale: WHERE name > ? ORDER BY name LIMIT n — no OFFSET scan.");
	}

	record DeviceRow(String id, String name, String family) {
		@Override
		public String toString() {
			return name;
		}
	}

	record Page(List<DeviceRow> items, boolean hasMore) {
	}

	static Page offsetPage(List<DeviceRow> sorted, int page, int size) {
		int from = page * size;
		if (from >= sorted.size()) {
			return new Page(List.of(), false);
		}
		int to = Math.min(from + size, sorted.size());
		return new Page(sorted.subList(from, to), to < sorted.size());
	}

	static Page keysetPage(List<DeviceRow> sortedByName, String afterName, int size) {
		List<DeviceRow> next = sortedByName.stream()
				.filter(d -> d.name().compareTo(afterName) > 0)
				.limit(size + 1L)
				.collect(Collectors.toList());
		boolean hasMore = next.size() > size;
		List<DeviceRow> items = hasMore ? next.subList(0, size) : next;
		return new Page(items, hasMore);
	}
}
