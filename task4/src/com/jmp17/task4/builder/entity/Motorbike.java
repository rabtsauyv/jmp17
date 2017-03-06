package com.jmp17.task4.builder.entity;

public class Motorbike extends Vehicle {

	@Override
	public void setWheels(int wheels) {
		if (wheels != 2) {throw new IllegalArgumentException();}
		super.setWheels(wheels);
	}
	
	@Override
	public void drive() {
		System.out.println("sit");
		System.out.println("turn on engine");
		System.out.println("put on helmet");
		System.out.println("drive");
	}

}
