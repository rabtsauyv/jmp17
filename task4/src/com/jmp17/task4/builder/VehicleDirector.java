package com.jmp17.task4.builder;

import com.jmp17.task4.builder.build.VehicleBuilder;
import com.jmp17.task4.builder.entity.Vehicle;

public class VehicleDirector {
	
	private VehicleBuilder builder;
	
	public void setBuilder(VehicleBuilder builder) {
		this.builder = builder;
	}
	
	public Vehicle build(String color, int wheels) {
		return builder.create()
			.addWheels(wheels)
			.paint(color)
			.getVehicle();
	}
}
