package com.jmp17.threads.task2;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
	private AtomicInteger count = new AtomicInteger();
	
	@Override
	public int getCount() {
		return count.get();
	}
	
	@Override
	public Runnable getIncrement() {
		return () -> {
			for (int i = 0; i < 1_000_000; i++) {
				count.incrementAndGet();
			}
		};
	}
	
	@Override
	public Runnable getDecrement() {
		return () -> {
			for (int i = 0; i < 1_000_000; i++) {
				count.decrementAndGet();
			}
		};
	}
}