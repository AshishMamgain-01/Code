package com.learn.coding.java;

import java.util.concurrent.CompletableFuture;

public class Test1 {
	
	record User(int id, String Name) {
		
	}
	
	record Department(int id, String name) {
		
	}
	
	User getUser() {
		System.out.println("Fetching User");
		return new User(1, "Ashish");
	}
	
	Department getDepartment() {
		System.out.println("Fetching Department");
		return new Department(10, "IT");
	}
	
	

	public static void main(String[] args) {
		CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
			
			return "Name: Ashish";
		}).thenApply(String::toUpperCase);
		
		CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {return 10;});
		
		
		completableFuture.thenAccept(System.out::print);
		
		completableFuture.thenApply(String::toUpperCase);
		System.out.println(completableFuture.join());
		System.out.println(completableFuture2.join());
		
		CompletableFuture<String> combined = completableFuture.thenCombine(completableFuture2, 
				(u,a) -> u+" Age :"+a);
		
		System.out.println(combined.join());
		
		CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> 101)
				.thenCompose(id -> CompletableFuture.supplyAsync(() -> "user : " + id));
		
		System.out.println(completableFuture3.join());
		
		CompletableFuture<Integer> completableFuture4 = CompletableFuture.supplyAsync(() -> 10/0).exceptionally(ex -> {
			ex.printStackTrace();
			return -1;
			}
		);
		
		System.out.println(completableFuture4.join());
		
		
		Test1 obj = new Test1();
		CompletableFuture<User> user = CompletableFuture.supplyAsync(obj::getUser); 
		CompletableFuture<Department> dept = CompletableFuture.supplyAsync(obj::getDepartment); 
		
		CompletableFuture<Void> all =
                CompletableFuture.allOf(user,dept);
		
		all.thenRun(() -> {
			User myuser =  user.join();
			Department myDEpt =  dept.join();
			
			
			System.out.println("================DashBoard================");
			System.out.println(myuser);
			System.out.println(myDEpt);
		});
		
	}

}
