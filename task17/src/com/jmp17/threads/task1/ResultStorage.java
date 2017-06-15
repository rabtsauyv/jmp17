package com.jmp17.threads.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class ResultStorage {

	private AtomicInteger a = new AtomicInteger();
	private AtomicInteger b = new AtomicInteger();
	private AtomicInteger c = new AtomicInteger();
	private AtomicInteger d = new AtomicInteger();
	
	public int getA() {
		return a.get();
	}
	public void setA(int a) {
		this.a.set(a);
	}
	public boolean hasA() {
		return a.get() != 0;
	}
	
	public int getB() {
		return b.get();
	}
	public void setB(int b) {
		this.b.set(b);
	}
	public boolean hasB() {
		return b.get() != 0;
	}
	
	public int getC() {
		return c.get();
	}
	public void setC(int c) {
		this.c.set(c);
	}
	public boolean hasC() {
		return c.get() != 0;
	}
	
	public int getD() {
		return d.get();
	}
	public void setD(int d) {
		this.d.set(d);
	}
	public boolean hasD() {
		return d.get() != 0;
	}
	
	@Override
	public String toString() {
		return String.format("a=%d b=%d c=%d d=%d", a.get(), b.get(), c.get(), d.get());
	}
}
