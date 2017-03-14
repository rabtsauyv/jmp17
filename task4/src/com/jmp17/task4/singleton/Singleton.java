package com.jmp17.task4.singleton;

public class Singleton {
	
	private static volatile Singleton instance;
	
	public static Singleton getInstance() {
		if (instance == null) {
			System.out.println(Thread.currentThread().getName() + ": 1st check");
			synchronized (Singleton.class) {
				if(instance == null) {
					System.out.println(Thread.currentThread().getName() + ": 2nd check");
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	private Singleton() {
		System.out.println("creating Singleton...");
	}
	
	public void action() {
		System.out.println(this.toString() + " is performing action in " + Thread.currentThread().getName());
	}
}
