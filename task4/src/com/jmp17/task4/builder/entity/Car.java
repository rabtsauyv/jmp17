package com.jmp17.task4.builder.entity;

public class Car extends Vehicle {

	@Override
	public void setWheels(int wheels) {
		if (wheels < 4) {
			throw new IllegalArgumentException("Car cannot have less than 4 wheels, but there were " + wheels);
		}
		super.setWheels(wheels);
	}
	
	@Override
	public void drive() {
		System.out.println("open");
		System.out.println("sit");
		System.out.println("turn on engine");
		System.out.println("drive");
	}

}
