package com.jmp17.threads.run;

import com.jmp17.threads.task1.Task1Executor;
import com.jmp17.threads.task2.CounterRunner;
import com.jmp17.threads.task3.Task3Executor;

public class Demo {

	public static void main(String[] args) {
		Demo demo = new Demo();
		
		demo.task1();
		demo.task2();
		demo.task3();
	}
	
	private void task1() {
		new Task1Executor().executeWithWaitNotify();
		new Task1Executor().executeWithLocks();
		new Task1Executor().executeWithFutures();
		new Task1Executor().executeWithCompletableFutures();
	}
	
	private void task2() {
		new CounterRunner().runAll();
	}
	
	private void task3() {
		new Task3Executor().runHashMap();
		new Task3Executor().runConcurrentMap();
		new Task3Executor().runSynchronizedMap();
	}
}
