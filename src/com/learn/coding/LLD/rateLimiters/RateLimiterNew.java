package com.learn.coding.LLD.rateLimiters;

public class RateLimiterNew {

	public static void main(String[] args) {
		 RateLimiterNew limiter = new RateLimiterNew();
	        String user = "user123";

	        for (int i = 1; i <= 7; i++) {
	            boolean allowed = limiter.allowRequest(user);
	            System.out.println("Request " + i + " allowed? " + allowed);
	            try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }
	}

	private boolean allowRequest(String user) {
		
		return false;
	}

}
