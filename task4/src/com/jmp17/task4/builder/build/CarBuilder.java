package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Car;

public class CarBuilder extends VehicleBuilder {

	@Override
	public void addWheels(int n) {
		vehicle.setWheels(n);
	}

	@Override
	public void create() {
		vehicle = new Car();
		vehicle.setType("car");
	}

}
