package com.learn.coding.spring.mini;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Who maintains lastId? The caller (UI, REST client, or this batch loop).
 * DB and SQL do not store the cursor. Each response returns the last row of
 * the page; the next request sends that pair back as (:lastName, :lastId).
 *
 * First page: lastName/lastId are null → omit the tuple predicate.
 */
public class InterfaceKeysetPaginationDemo {

	static final long DEVICE_ID = 12321313L;
	static final int PAGE_SIZE = 5;

	public static void main(String[] args) {
		InterfaceRepository repo = new InterfaceRepository(seed());

		List<Iface> all = fetchAllForDevice(repo, DEVICE_ID);
		System.out.println("fetched=" + all.size());
		all.forEach(i -> System.out.println("  " + i.ifName() + " id=" + i.interfaceId()));
	}

	/** Client / job: holds cursor between calls until hasMore is false. */
	static List<Iface> fetchAllForDevice(InterfaceRepository repo, long deviceId) {
		// Count is not in the LIMIT query. One COUNT(*) on the same filter, once.
		long total = repo.countByDevice(deviceId);
		List<Iface> collected = new ArrayList<>();
		String lastName = null;
		String lastId = null;
		boolean hasMore = true;
		int page = 0;

		while (hasMore) {
			Page result = repo.listByDevice(deviceId, lastName, lastId, PAGE_SIZE);
			collected.addAll(result.items());
			hasMore = result.hasMore();
			page++;
			System.out.println("page " + page + " size=" + result.items().size() + "/" + total + " hasMore=" + hasMore
					+ " cursorIn=[" + lastName + "," + lastId + "]");

			if (hasMore || !result.items().isEmpty()) {
				Iface last = result.items().get(result.items().size() - 1);
				lastName = last.ifName();
				lastId = last.interfaceId();
			}
		}
		return collected;
	}

	static List<Iface> seed() {
		List<Iface> rows = new ArrayList<>();
		// Other devices — must not appear in device 12321313 pages.
		rows.add(new Iface("uuid-other-1", 999L, "GigabitEthernet0/0", 1, "up", "up"));
		for (int i = 1; i <= 12; i++) {
			String name = "GigabitEthernet0/" + i;
			// Collection-time UUIDs: not sequential, not time-ordered.
			String id = "if-" + Integer.toHexString((i * 7919) ^ 0xA5A5);
			rows.add(new Iface(id, DEVICE_ID, name, i, "up", i % 2 == 0 ? "up" : "down"));
		}
		return rows;
	}

	record Iface(String interfaceId, long deviceId, String ifName, int ifIndex, String adminStatus,
			String operStatus) {
	}

	record Page(List<Iface> items, boolean hasMore) {
	}

	/**
	 * Same predicates as:
	 *
	 * <pre>
	 * SELECT COUNT(*) FROM interfaces WHERE device_id = :deviceId;
	 *
	 * SELECT interface_id, if_name, if_index, admin_status, oper_status
	 * FROM interfaces
	 * WHERE device_id = :deviceId
	 *   AND (lastName IS NULL OR (if_name, interface_id) > (:lastName, :lastId))
	 * ORDER BY if_name, interface_id
	 * LIMIT :size + 1
	 * </pre>
	 */
	static final class InterfaceRepository {
		private final List<Iface> table;

		InterfaceRepository(List<Iface> table) {
			this.table = table;
		}

		long countByDevice(long deviceId) {
			return table.stream().filter(row -> row.deviceId() == deviceId).count();
		}

		Page listByDevice(long deviceId, String lastName, String lastId, int size) {
			List<Iface> next = table.stream()
					.filter(row -> row.deviceId() == deviceId)
					.filter(row -> afterCursor(row, lastName, lastId))
					.sorted(Comparator.comparing(Iface::ifName).thenComparing(Iface::interfaceId))
					.limit(size + 1L)
					.toList();
			boolean hasMore = next.size() > size;
			List<Iface> items = hasMore ? next.subList(0, size) : next;
			return new Page(List.copyOf(items), hasMore);
		}

		static boolean afterCursor(Iface row, String lastName, String lastId) {
			if (lastName == null) {
				return true;
			}
			int nameCmp = row.ifName().compareTo(lastName);
			if (nameCmp != 0) {
				return nameCmp > 0;
			}
			return row.interfaceId().compareTo(lastId) > 0;
		}
	}
}
