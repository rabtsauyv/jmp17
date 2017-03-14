package com.jmp17.task4.builder;

import com.jmp17.task4.builder.build.CarBuilder;
import com.jmp17.task4.builder.build.MotorbikeBuilder;
import com.jmp17.task4.builder.entity.Vehicle;

public class BuilderDemo {

	public void demo() {
		VehicleDirector dir = new VehicleDirector();
		
		dir.setBuilder(new CarBuilder());
		Vehicle v1 = dir.build("white", 4);
		
		dir.setBuilder(new MotorbikeBuilder());
		Vehicle v2 = dir.build("black", 2);
		
		v1.drive();
		v2.drive();
	}
}
