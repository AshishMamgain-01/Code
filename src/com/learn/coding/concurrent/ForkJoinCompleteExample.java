package com.learn.coding.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCompleteExample {

	public static void main(String[] args) {

		try (ForkJoinPool pool = new ForkJoinPool(4)) {

			System.out.println("===== RecursiveAction Example =====");
			MyRecursiveAction action = new MyRecursiveAction(128);
			pool.invoke(action);

			System.out.println("\n===== RecursiveTask Example =====");
			MyRecursiveTask task = new MyRecursiveTask(128);
			long result = pool.invoke(task);

			System.out.println("\nFinal Result = " + result);
			pool.shutdown();
		}
	}
}

/*--------------------------------------------------
        RecursiveAction
---------------------------------------------------*/
class MyRecursiveAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long workload;

	public MyRecursiveAction(long workload) {
		this.workload = workload;
	}

	@Override
	protected void compute() {

		System.out.println(Thread.currentThread().getName() + " Processing workload : " + workload);

		if (workload > 16) {

			System.out.println(Thread.currentThread().getName() + " Splitting workload : " + workload);

			long workload1 = workload / 2;
			long workload2 = workload - workload1;

			MyRecursiveAction subTask1 = new MyRecursiveAction(workload1);

			MyRecursiveAction subTask2 = new MyRecursiveAction(workload2);

			invokeAll(subTask1, subTask2);

		} else {

			System.out.println(Thread.currentThread().getName() + " Doing work : " + workload);
		}
	}
}

/*--------------------------------------------------
        RecursiveTask
---------------------------------------------------*/
class MyRecursiveTask extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long workload;

	public MyRecursiveTask(long workload) {
		this.workload = workload;
	}

	@Override
	protected Long compute() {

		System.out.println(Thread.currentThread().getName() + " Processing workload : " + workload);

		if (workload > 16) {

			System.out.println(Thread.currentThread().getName() + " Splitting workload : " + workload);

			long workload1 = workload / 2;
			long workload2 = workload - workload1;

			MyRecursiveTask subTask1 = new MyRecursiveTask(workload1);

			MyRecursiveTask subTask2 = new MyRecursiveTask(workload2);

			subTask1.fork();

			long result2 = subTask2.compute();

			long result1 = subTask1.join();

			long result = result1 + result2;

			System.out.println(
					Thread.currentThread().getName() + " Merged " + result1 + " + " + result2 + " = " + result);

			return result;

		} else {

			long result = workload * 3;

			System.out.println(Thread.currentThread().getName() + " Computing " + workload + " * 3 = " + result);

			return result;
		}
	}
}