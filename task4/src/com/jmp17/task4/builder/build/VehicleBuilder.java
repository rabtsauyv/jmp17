package com.jmp17.task4.builder.build;

import com.jmp17.task4.builder.entity.Vehicle;

public abstract class VehicleBuilder {

	protected Vehicle vehicle;
	
	public abstract void addWheels(int n);
	public void paint(String color) {vehicle.setColor(color);}
	public abstract void create();
	
	public Vehicle getVehicle() {
		return vehicle;
	}
}
