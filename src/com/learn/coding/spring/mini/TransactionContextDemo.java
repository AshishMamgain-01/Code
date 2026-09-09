package com.learn.coding.spring.mini;

/**
 * Spring binds the current transaction to a ThreadLocal (simplified here).
 * Nested REQUIRED reuses the same context; REQUIRES_NEW would replace it.
 */
public class TransactionContextDemo {

	public static void main(String[] args) {
		TxTemplate tx = new TxTemplate();
		tx.required(() -> {
			System.out.println("outer tx=" + TxContext.current());
			tx.required(() -> System.out.println("nested REQUIRED same tx=" + TxContext.current()));
			tx.requiresNew(() -> System.out.println("REQUIRES_NEW different tx=" + TxContext.current()));
			System.out.println("back to outer tx=" + TxContext.current());
		});
		System.out.println("after commit, ThreadLocal cleared: " + TxContext.current());
	}

	static final class TxContext {
		private static final ThreadLocal<String> TX = new ThreadLocal<>();

		static String current() {
			return TX.get();
		}

		static void set(String id) {
			TX.set(id);
		}

		static void clear() {
			TX.remove();
		}
	}

	static final class TxTemplate {
		private int seq;

		void required(Runnable work) {
			boolean started = TxContext.current() == null;
			if (started) {
				TxContext.set("tx-" + (++seq));
			}
			try {
				work.run();
			} finally {
				if (started) {
					TxContext.clear();
				}
			}
		}

		void requiresNew(Runnable work) {
			String suspended = TxContext.current();
			TxContext.set("tx-" + (++seq));
			try {
				work.run();
			} finally {
				if (suspended == null) {
					TxContext.clear();
				} else {
					TxContext.set(suspended);
				}
			}
		}
	}
}
