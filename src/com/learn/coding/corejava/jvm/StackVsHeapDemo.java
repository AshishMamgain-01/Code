package com.learn.coding.corejava.jvm;

/**
 * Stack holds frames and local references. Heap holds objects.
 * Recursion without tail-call optimization grows the stack.
 */
public class StackVsHeapDemo {

	public static void main(String[] args) {
		Device d = new Device("NCS1004");
		System.out.println("Reference 'd' lives in the current stack frame; Device object lives on the heap: "
				+ d.name());

		int depth = 0;
		try {
			depth = recurseUntilAlmostOverflow(0);
		} catch (StackOverflowError e) {
			System.out.println("StackOverflowError after ~" + depth
					+ " frames (limit depends on -Xss). Objects on the heap are unrelated to this.");
		}
	}

	private static int recurseUntilAlmostOverflow(int n) {
		if (n == 8_000) {
			return n;
		}
		return recurseUntilAlmostOverflow(n + 1);
	}

	record Device(String name) {
	}
}
