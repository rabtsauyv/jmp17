package com.jmp17.threads.task2;

public class UnsafeCounter implements Counter {
	private int count = 0;
	
	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	public Runnable getIncrement() {
		return () -> {
			for (int i = 0; i < 1_000_000; i++) {
				count++;
			}
		};
	}
	
	@Override
	public Runnable getDecrement() {
		return () -> {
			for (int i = 0; i < 1_000_000; i++) {
				count--;
			}
		};
	}
}