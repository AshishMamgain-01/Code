package com.learn.coding.spring.mini;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK dynamic proxy = Spring AOP when the bean implements an interface.
 * Calls go: caller -> proxy -> advice -> target.
 *
 * this.foo() on the target skips the proxy (self-invocation).
 */
public class JdkProxyAopDemo {

	public static void main(String[] args) {
		Collector target = new CollectorImpl();
		Collector proxy = (Collector) Proxy.newProxyInstance(Collector.class.getClassLoader(),
				new Class<?>[] { Collector.class }, new TimingAdvice(target));

		proxy.collect("NCS1004");
		System.out.println("--- self-invocation inside impl skips advice ---");
		target.collectWithSelfCall("NCS1004");
	}

	interface Collector {
		void collect(String deviceId);

		void collectWithSelfCall(String deviceId);
	}

	static final class CollectorImpl implements Collector {
		@Override
		public void collect(String deviceId) {
			System.out.println("collecting " + deviceId);
		}

		@Override
		public void collectWithSelfCall(String deviceId) {
			collect(deviceId);
		}
	}

	static final class TimingAdvice implements InvocationHandler {
		private final Object target;

		TimingAdvice(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getDeclaringClass() == Object.class) {
				return method.invoke(target, args);
			}
			long start = System.nanoTime();
			try {
				System.out.println("[advice] before " + method.getName());
				return method.invoke(target, args);
			} finally {
				System.out.println("[advice] after " + method.getName() + " " + (System.nanoTime() - start) + "ns");
			}
		}
	}
}
