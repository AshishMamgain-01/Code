package com.learn.coding.spring.mini;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tiny IoC container: register implementations, resolve by constructor
 * injection, cache singletons. This is the story behind Spring's
 * ApplicationContext (minus scanning, AOP, scopes).
 *
 * See interview-prep/spring/IoC-DI.md
 */
public class MiniContainerDemo {

	public static void main(String[] args) {
		MiniContainer c = new MiniContainer();
		c.register(DeviceRepository.class, InMemoryDeviceRepository.class);
		c.register(InventoryService.class, InventoryService.class);

		InventoryService a = c.getBean(InventoryService.class);
		InventoryService b = c.getBean(InventoryService.class);
		System.out.println("same singleton service: " + (a == b));
		System.out.println(a.find("NCS1004"));
	}

	interface DeviceRepository {
		String findName(String id);
	}

	static final class InMemoryDeviceRepository implements DeviceRepository {
		@Override
		public String findName(String id) {
			return "device-" + id;
		}
	}

	static final class InventoryService {
		private final DeviceRepository repo;

		InventoryService(DeviceRepository repo) {
			this.repo = repo;
		}

		String find(String id) {
			return repo.findName(id);
		}
	}

	static final class MiniContainer {
		private final Map<Class<?>, Class<?>> bindings = new ConcurrentHashMap<>();
		private final Map<Class<?>, Object> singletons = new ConcurrentHashMap<>();

		<T> void register(Class<T> type, Class<? extends T> impl) {
			bindings.put(type, impl);
		}

		@SuppressWarnings("unchecked")
		<T> T getBean(Class<T> type) {
			Object cached = singletons.get(type);
			if (cached != null) {
				return (T) cached;
			}
			T created = create(type);
			singletons.put(type, created);
			return created;
		}

		@SuppressWarnings("unchecked")
		private <T> T create(Class<T> type) {
			try {
				Class<?> impl = bindings.getOrDefault(type, type);
				Constructor<?> ctor = impl.getDeclaredConstructors()[0];
				ctor.setAccessible(true);
				Class<?>[] params = ctor.getParameterTypes();
				Object[] args = new Object[params.length];
				for (int i = 0; i < params.length; i++) {
					args[i] = getBean(params[i]);
				}
				return (T) ctor.newInstance(args);
			} catch (ReflectiveOperationException e) {
				throw new IllegalStateException("cannot create " + type, e);
			}
		}
	}
}
