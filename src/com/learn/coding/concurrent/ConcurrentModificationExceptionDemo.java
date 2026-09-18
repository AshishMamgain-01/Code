package com.learn.coding.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcurrentModificationExceptionDemo {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>(Arrays.asList("A","B","C","D"));
		for(String s : list) {
			if(s.equals("B")) {
				list.remove(s);
			}
		}
		
		System.out.println("Hi");

	}
}