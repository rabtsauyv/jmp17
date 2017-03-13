package com.jmp17.task5.adapter;

public class BinaryAdd implements BinaryOperation {

	@Override
	public Integer evaluate(Integer operand1, Integer operand2) {
		return operand1 + operand2;
	}

}
