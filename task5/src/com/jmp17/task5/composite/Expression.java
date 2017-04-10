package com.jmp17.task5.composite;

import java.util.ArrayList;
import java.util.List;

import com.jmp17.task5.flyweight.Operation;

public class Expression implements Calculatable {

	private List<Calculatable> subExpressions = new ArrayList<>();
	private Operation operation;
	
	@Override
	public int calculate() {
		Integer[] operands = subExpressions.parallelStream().map(e -> e.calculate()).toArray(Integer[]::new);
		return operation.evaluate(operands);
	}

	@Override
	public void addSubExpression(Calculatable e) {
		subExpressions.add(e);
	}
	
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
