package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Car;

public class CarBuilder extends VehicleBuilder {

	@Override
	public VehicleBuilder addWheels(int n) {
		vehicle.setWheels(n);
		return this;
	}

	@Override
	public VehicleBuilder create() {
		vehicle = new Car();
		vehicle.setType("car");
		return this;
	}

}
