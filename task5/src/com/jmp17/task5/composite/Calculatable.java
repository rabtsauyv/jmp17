package com.jmp17.task5.composite;

public interface Calculatable {

	int calculate();
	
	void addSubExpression(Calculatable e);
}
