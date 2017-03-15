package com.jmp17.task5.flyweight;

public class AddOperation implements Operation {

	@Override
	public Integer evaluate(Integer... operands) {
		Integer sum = 0;
		for (Integer in : operands) {
			sum += in;
		}
		return sum;
	}
}
