package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Motorbike;

public class MotorbikeBuilder extends VehicleBuilder {

	@Override
	public VehicleBuilder addWheels(int n) {
		vehicle.setWheels(n);
		return this;
	}

	@Override
	public VehicleBuilder create() {
		vehicle = new Motorbike();
		vehicle.setType("motorbike");
		return this;
	}

}
