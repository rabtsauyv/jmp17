package com.jmp17.task4.prototype;

public class PrototypeDemo {

	public void demo() {
		Address a1 = new Address("Minsk", "Zhukova");
		a1.setHouse(29);
		Address a2 = new Address("Minsk", "Kuprevicha");
		a2.setHouse(1);
		Address a3 = null;
		try {
			a3 = a2.copy();
			a3.setHouse(3);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
	}
}
