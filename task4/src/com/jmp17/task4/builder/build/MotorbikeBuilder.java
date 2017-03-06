package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Motorbike;

public class MotorbikeBuilder extends VehicleBuilder {

	@Override
	public void addWheels(int n) {
		vehicle.setWheels(n);
	}

	@Override
	public void create() {
		vehicle = new Motorbike();
		vehicle.setType("motorbike");
	}

}
