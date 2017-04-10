package com.jmp17.task5.flyweight;

public class MultiplyOperation implements Operation {

	@Override
	public Integer evaluate(Integer... operands) {
		Integer res = 1;
		for (Integer in : operands) {
			res *= in;
		}
		return res;
	}
}
