package com.learn.coding.corejava.exceptions;

import java.io.IOException;

/**
 * Checked vs unchecked, try-with-resources, suppressed exceptions.
 */
public class ExceptionHierarchyDemo {

	public static void main(String[] args) {
		try {
			readAndFail();
		} catch (IOException e) {
			System.out.println("primary: " + e.getMessage());
			for (Throwable s : e.getSuppressed()) {
				System.out.println("suppressed from close(): " + s.getMessage());
			}
		}

		try {
			unchecked();
		} catch (IllegalArgumentException e) {
			System.out.println("unchecked (RuntimeException): " + e.getMessage());
		}
	}

	private static void readAndFail() throws IOException {
		try (FailingReader reader = new FailingReader()) {
			throw new IOException("body failed");
		}
	}

	private static void unchecked() {
		throw new IllegalArgumentException("device id blank");
	}

	static final class FailingReader implements AutoCloseable {
		@Override
		public void close() throws IOException {
			throw new IOException("close failed");
		}
	}
}
