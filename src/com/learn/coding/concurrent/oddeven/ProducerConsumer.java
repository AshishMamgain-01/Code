package com.learn.coding.concurrent.oddeven;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

	public static void main(String[] args) {

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);

		Thread producer = new Thread(() -> {

			try {
				for (int i = 0; i <= 10; i++) {
					System.out.println("Producer produced : " + i);
					queue.put(i);
					Thread.sleep(500);
				}
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				return;
			}
		});

		Thread consumer = new Thread(() -> {
			try {
				while(true) {
					
					Integer item = queue.take();
					System.out.println("Consumer consumed : "+ item);
					Thread.sleep(1000);
				}
				
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				return;				
			}
		});
		
		
		producer.start();
		consumer.start();

	}

}
