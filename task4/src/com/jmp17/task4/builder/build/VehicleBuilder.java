package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Vehicle;

public abstract class VehicleBuilder {

	protected Vehicle vehicle;

	public abstract VehicleBuilder create();
	public abstract VehicleBuilder addWheels(int n);
	
	public VehicleBuilder paint(String color) {
		vehicle.setColor(color); 
		return this;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
}
