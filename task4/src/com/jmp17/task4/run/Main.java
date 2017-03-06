package com.jmp17.task4.run;

import com.jmp17.task4.builder.BuilderDemo;
import com.jmp17.task4.factorymethod.FactoryMethodDemo;
import com.jmp17.task4.prototype.PrototypeDemo;
import com.jmp17.task4.singleton.SingletonDemo;

public class Main {

	public static void main(String[] args) {
		System.out.println("FactoryMethodDemo");
		new FactoryMethodDemo().demo();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("BuilderDemo");
		new BuilderDemo().demo();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("SingletonDemo");
		new SingletonDemo().demo();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("PrototypeDemo");
		new PrototypeDemo().demo();
	}

}
