package com.learn.coding.designpattern.creational.builder.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

//Variation 1 — Lazy, Not Thread Safe
class Singleton {

	private static Singleton instance;

	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}

		return instance;
	}

	/*
	 * Note : This implementation is lazy but not thread-safe. Two threads can
	 * simultaneously observe instance == null and create separate objects.
	 */
}

//Variation 2: Eager Singleton
class EagerSingleton {

	private static final EagerSingleton instance = new EagerSingleton();

	private EagerSingleton() {
	}

	public static EagerSingleton getInstance() {
		return instance;
	}

	/*
	 * Note:This is thread-safe because class initialization is guaranteed to be
	 * performed safely by the JVM.
	 * 
	 * Java class initialization is thread-safe, so we don't need explicit
	 * synchronization here.
	 */
}

//Variation 3: Lazy and Thread Safe
class LazyThreadSafeSingleton {
	private static LazyThreadSafeSingleton instance;

	private LazyThreadSafeSingleton() {
	}

	public static synchronized LazyThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new LazyThreadSafeSingleton();
		}
		return instance;
	}

	/*
	 * Note: There is a performance consideration. Every call goes
	 * through:synchronized even after the instance has already been created.
	 * 
	 * For a Singleton getter that may be called frequently, unnecessary
	 * synchronization can be avoided. That's why we have Double-Checked Locking.
	 */
}

//Variation 4 :Double checked locking Singleton	
class DoubleCheckedLockingSingleton {
	private static volatile DoubleCheckedLockingSingleton instance;

	private DoubleCheckedLockingSingleton() {
	}

	public static DoubleCheckedLockingSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedLockingSingleton.class) {
				if (instance == null) {
					instance = new DoubleCheckedLockingSingleton();
				}
			}
		}
		return instance;
	}
}

//Variation 5 : Serialization-Safe, Lazy initialization and ThreadSafety 
class SerializeSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SerializeSingleton instance;

	private SerializeSingleton() {
	}

	public static SerializeSingleton getInstance() {
		if (instance == null) {
			synchronized (SerializeSingleton.class) {
				if (instance == null) {
					instance = new SerializeSingleton();
				}
			}
		}
		return instance;
	}

	private Object readResolve() throws ObjectStreamException {
		return getInstance();
	}
}
	
//Variation 6: enum Singleton :	NotLazy , Thread Safe and Serialization Safe
enum SingletonEnum {

    INSTANCE;

    public void doSomething() {
        System.out.println("Doing something");
    }
    
// Note invocation: SingletonEnum.INSTANCE.doSomething();
}

//Variation 7: Holder :	NotLazy , Thread Safe and Serialization Safe
class HolderSingleton {

    private HolderSingleton() {
    }

    private static class Holder {
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}


