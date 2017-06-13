package com.jmp17.threads.task2;

public class CounterRunner {
	
	public void runAll() {
		System.out.println("Unsafe");
		runCounter(new UnsafeCounter());

		System.out.println("Syncronized");
		runCounter(new SyncCounter());

		System.out.println("Atomic");
		runCounter(new AtomicCounter());
	}
	
	public void runCounter(Counter counter) {
		Thread t1 = new Thread(counter.getIncrement());
		Thread t2 = new Thread(counter.getDecrement());
		
		long startTime = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("Final value = " + counter.getCount() + ", execution time: " + (endTime - startTime));
	}

}
