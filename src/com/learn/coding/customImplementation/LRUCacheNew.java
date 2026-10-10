package com.learn.coding.customImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheNew<K,V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;
	public final int capacity;
	
	public LRUCacheNew(int capacity) {
		// accessOrder = true → maintains order by access (LRU)
		super(capacity,0.75f,true);
		this.capacity=capacity;		
	}
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		// automatically evict when size exceeds capacity
        return size()>capacity;
    }

	public static void main(String[] args) {
		
		LRUCacheNew<Integer, String> cache = new LRUCacheNew<>(3);
		
		cache.put(1, "Ashish");
		cache.put(2, "Mamgain");
		cache.put(3, "Rohit");
		
		System.out.println(cache);
		
		System.out.println(cache.get(1));	
		cache.put(4, "Atul");
		System.out.println(cache);		

	}

}
