package com.learn.coding.designpattern.singleton;

class HolderSingleton1 {

	private HolderSingleton1() {
		System.out.println("Singleton created");
	}

	private static class Holder {

		static {
			System.out.println("Holder initialized");
		}

		private static final HolderSingleton1 INSTANCE = new HolderSingleton1();
	}

	public static HolderSingleton1 getInstance() {
		System.out.println("getInstance() called");
		return Holder.INSTANCE;
	}
}

public class HolderTest {

	public static void main(String[] args) {

		System.out.println("Application started");

		System.out.println("Before getInstance()");

		HolderSingleton1 s1 = HolderSingleton1.getInstance();

		System.out.println("After first getInstance()");

		HolderSingleton1 s2 = HolderSingleton1.getInstance();

		System.out.println("After second getInstance()");

		System.out.println(s1 == s2);
	}

}
