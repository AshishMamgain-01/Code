package com.learn.coding.dsa.stack;

import java.util.Stack;

class MyQueueImplementation {

	private Stack<Integer> enqueue = new Stack<Integer>();
	private Stack<Integer> dqueue = new Stack<Integer>();

	public void push(int item) {
		enqueue.push(item);
	}

	public int pop() {
		peek();
		return dqueue.pop();
	}

	public int peek() {
		if (dqueue.isEmpty()) {
			while (!enqueue.isEmpty()) {
				dqueue.push(enqueue.pop());
			}
		}
		if(dqueue.isEmpty()) {
			System.out.println("Queue is empty");
		}
		return dqueue.peek();
	}
}

public class QueueByStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
     MyQueueImplementation queu = new MyQueueImplementation();
     queu.push(10);
     queu.push(20);
     queu.push(30);
     
     System.out.println(queu.pop());
     System.out.println(queu.peek());
	}

}
