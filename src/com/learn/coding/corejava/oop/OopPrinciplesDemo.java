package com.learn.coding.corejava.oop;

/**
 * OOP talking points for a 10 YOE round: abstraction, polymorphism,
 * encapsulation, composition over inheritance. Inventory adapters are the
 * Cisco-shaped example (no product IP).
 *
 * See interview-prep/core-java/OOP.md
 */
public class OopPrinciplesDemo {

	public static void main(String[] args) {
		DeviceAdapter cisco = new VendorAdapter("cisco", new SnmpCollector());
		DeviceAdapter juniper = new VendorAdapter("juniper", new GnmiCollector());
		InventoryFacade facade = new InventoryFacade(cisco, juniper);

		System.out.println(facade.snapshot("cisco", "NCS1004"));
		System.out.println(facade.snapshot("juniper", "MX960"));
		System.out.println("composition: adapter delegates to collector, does not inherit it");
	}

	/** Abstraction: callers depend on this contract, not SNMP vs gNMI. */
	interface Collector {
		String collect(String deviceId);
	}

	static final class SnmpCollector implements Collector {
		@Override
		public String collect(String deviceId) {
			return "snmp:" + deviceId;
		}
	}

	static final class GnmiCollector implements Collector {
		@Override
		public String collect(String deviceId) {
			return "gnmi:" + deviceId;
		}
	}

	/** Encapsulation: vendor is private; behavior is the public API. */
	interface DeviceAdapter {
		String vendor();

		String read(String deviceId);
	}

	/**
	 * Composition: an adapter *has a* collector. Inheritance would couple you to
	 * one collector hierarchy and break when a vendor needs two protocols.
	 */
	static final class VendorAdapter implements DeviceAdapter {
		private final String vendor;
		private final Collector collector;

		VendorAdapter(String vendor, Collector collector) {
			this.vendor = vendor;
			this.collector = collector;
		}

		@Override
		public String vendor() {
			return vendor;
		}

		@Override
		public String read(String deviceId) {
			return vendor + " -> " + collector.collect(deviceId);
		}
	}

	/** Polymorphism: facade talks to DeviceAdapter, not concrete classes. */
	static final class InventoryFacade {
		private final DeviceAdapter[] adapters;

		InventoryFacade(DeviceAdapter... adapters) {
			this.adapters = adapters;
		}

		String snapshot(String vendor, String deviceId) {
			for (DeviceAdapter adapter : adapters) {
				if (adapter.vendor().equals(vendor)) {
					return adapter.read(deviceId);
				}
			}
			throw new IllegalArgumentException("unknown vendor " + vendor);
		}
	}
}
