package com.jmp17.task5.composite;

public class Operand implements Calculatable {
	
	private final int value;
	
	public Operand(int value) {
		this.value = value;
	}

	@Override
	public int calculate() {
		return value;
	}

	@Override
	public void addSubExpression(Calculatable e) {
		// log
		System.err.println("impossible to add SubExpression into simple type");
		// ignore
	}
}
