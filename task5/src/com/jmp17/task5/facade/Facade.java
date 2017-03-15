package com.jmp17.task5.facade;

public class Facade {

	private SubModule1 m1 = new SubModule1();
	private SubModule2 m2 = new SubModule2();
	private SubModule3 m3 = new SubModule3();

	public void task1() {
		m1.task1();
	}

	public void complexTask2() {
		System.out.println("performing complex task (2)...");
		m1.operation1();
		m2.operation2();
		m3.operation3();
	}

	public void task3() {
		m3.task3();
	}

	public void task4() {
		m2.task4();
	}
}
