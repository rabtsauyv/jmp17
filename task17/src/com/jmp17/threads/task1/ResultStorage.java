package com.jmp17.threads.task1;

public class ResultStorage {

	private int a;
	private int b;
	private int c;
	private int d;
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public boolean hasA() {
		return a != 0;
	}
	
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public boolean hasB() {
		return b != 0;
	}
	
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public boolean hasC() {
		return c != 0;
	}
	
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public boolean hasD() {
		return d != 0;
	}
	
	@Override
	public String toString() {
		return String.format("a=%d b=%d c=%d d=%d", a, b, c, d);
	}
}
