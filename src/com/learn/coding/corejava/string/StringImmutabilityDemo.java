package com.learn.coding.corejava.string;

/**
 * Immutability, pool vs new String, and why + in a loop is expensive.
 */
public class StringImmutabilityDemo {

	public static void main(String[] args) {
		poolVsHeap();
		plusInLoopVsBuilder();
		substringNote();
	}

	private static void poolVsHeap() {
		String a = "cisco";
		String b = "cisco";
		String c = new String("cisco");
		String d = c.intern();
		System.out.println("literal == literal : " + (a == b));
		System.out.println("literal == new     : " + (a == c));
		System.out.println("literal == interned: " + (a == d));
		System.out.println("equals is what you use for content: " + a.equals(c));
	}

	private static void plusInLoopVsBuilder() {
		int n = 20_000;
		long t1 = System.nanoTime();
		String slow = "";
		for (int i = 0; i < n; i++) {
			slow += "x";
		}
		long plus = System.nanoTime() - t1;

		long t2 = System.nanoTime();
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			sb.append("x");
		}
		String fast = sb.toString();
		long builder = System.nanoTime() - t2;

		System.out.println("loop + ns=" + plus + " StringBuilder ns=" + builder + " lengths=" + slow.length() + ","
				+ fast.length());
		System.out.println("StringBuffer is synchronized StringBuilder — skip unless you share the builder.");
	}

	private static void substringNote() {
		String original = "interface-GigabitEthernet0/0/0";
		String iface = original.substring(10);
		System.out.println("substring copies bytes (Java 7u6+): " + iface);
		System.out.println("Original is unchanged (immutability): " + original);
	}
}
