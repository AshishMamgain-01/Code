package com.learn.coding.spring.mini;

import java.util.function.Supplier;

/**
 * Singleton vs prototype. Spring's trap: a singleton that holds a prototype
 * field still sees one instance unless you look up again (ObjectFactory).
 */
public class BeanScopesDemo {

	public static void main(String[] args) {
		PrototypeFactory factory = PrototypeFactory.of(RequestContext::new);

		SingletonWorker worker = new SingletonWorker(factory.create(), factory);

		System.out.println("injected once (wrong for request scope): " + worker.fixed.id + " then "
				+ worker.fixed.id);
		System.out.println("lookup each call (correct): " + worker.fresh().id + " then " + worker.fresh().id);
	}

	static final class RequestContext {
		final int id = System.identityHashCode(this);
	}

	static final class PrototypeFactory {
		private final Supplier<RequestContext> supplier;

		private PrototypeFactory(Supplier<RequestContext> supplier) {
			this.supplier = supplier;
		}

		static PrototypeFactory of(Supplier<RequestContext> supplier) {
			return new PrototypeFactory(supplier);
		}

		RequestContext create() {
			return supplier.get();
		}
	}

	static final class SingletonWorker {
		final RequestContext fixed;
		private final PrototypeFactory factory;

		SingletonWorker(RequestContext fixed, PrototypeFactory factory) {
			this.fixed = fixed;
			this.factory = factory;
		}

		RequestContext fresh() {
			return factory.create();
		}
	}
}
