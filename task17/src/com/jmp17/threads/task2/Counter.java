package com.jmp17.threads.task2;

public interface Counter {

	int getCount();
	Runnable getIncrement();
	Runnable getDecrement();
}
