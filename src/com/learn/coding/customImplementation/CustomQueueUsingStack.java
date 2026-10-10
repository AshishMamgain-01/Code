package com.learn.coding.customImplementation;

import java.util.Stack;

class CustomQueueUsingStack {
	Stack<Integer> stack1 = new Stack<>();
	Stack<Integer> stack2 = new Stack<>();

	// Enqueue
	public void enqueue(int x) {
		stack1.push(x);
	}

	// Dequeue
	public int dequeue() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		if (stack2.isEmpty()) {
			throw new RuntimeException("Queue is empty");
		}
		return stack2.pop();
	}

	// Peek
	public int peek() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.peek();
	}

	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	public static void main(String[] args) {
		CustomQueueUsingStack q = new CustomQueueUsingStack();
		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(30);

		System.out.println(q.dequeue()); // 10
		System.out.println(q.peek()); // 20
		System.out.println(q.dequeue()); // 20
	}
}
