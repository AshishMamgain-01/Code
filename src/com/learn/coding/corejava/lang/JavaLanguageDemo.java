package com.learn.coding.corejava.lang;

import java.util.concurrent.Executors;

/**
 * Java 8–21 highlights interviewers expect at senior level. Run on JDK 21.
 * Virtual-thread *pools* you already practiced in concurrent.executors.VirtualThreadDemo.
 *
 * See interview-prep/core-java/Java8to21.md
 */
public class JavaLanguageDemo {

	public static void main(String[] args) {
		showRecord();
		showSealedAndSwitch();
		showVirtualThread();
		showTextBlock();
	}

	private static void showRecord() {
		InterfaceDetail a = new InterfaceDetail("eth0", 1500);
		InterfaceDetail b = new InterfaceDetail("eth0", 1500);
		System.out.println("record equals by components: " + a.equals(b) + " name=" + a.name());
	}

	private static void showSealedAndSwitch() {
		DeviceKind kind = new OpticalLineCard("NCS1K4-2-QDD-C-K9");
		System.out.println("sealed switch: " + describe(kind));
	}

	private static String describe(DeviceKind kind) {
		return switch (kind) {
		case OpticalLineCard(String pid) -> "optical pid=" + pid;
		case RouterChassis(String pid) -> "router pid=" + pid;
		};
	}

	private static void showVirtualThread() {
		try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {
			exec.submit(() -> System.out.println("virtual thread? " + Thread.currentThread().isVirtual())).get();
		} catch (Exception e) {
			System.out.println("virtual thread demo: " + e.getMessage());
		}
		System.out.println("Use virtual threads for many blocking I/O calls, not for CPU-bound hash loops.");
	}

	private static void showTextBlock() {
		String json = """
				{"code":"NOT_FOUND","message":"device missing"}
				""";
		System.out.println("text block JSON snippet: " + json.strip());
	}

	record InterfaceDetail(String name, int mtu) {
	}

	sealed interface DeviceKind permits OpticalLineCard, RouterChassis {
	}

	record OpticalLineCard(String pid) implements DeviceKind {
	}

	record RouterChassis(String pid) implements DeviceKind {
	}
}
