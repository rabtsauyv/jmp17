package com.jmp17.task5.adapter;

import com.jmp17.task5.flyweight.Operation;
import com.jmp17.task5.flyweight.OperationFactory;
import com.jmp17.task5.flyweight.Operations;

public class BinaryAddAdapter implements BinaryOperation {

	private Operation multiply = OperationFactory.get(Operations.ADD);

	@Override
	public Integer evaluate(Integer operand1, Integer operand2) {
		return multiply.evaluate(operand1, operand2);
	}
}
