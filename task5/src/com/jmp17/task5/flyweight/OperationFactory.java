package com.jmp17.task5.flyweight;

import java.util.EnumMap;
import java.util.Map;

public class OperationFactory {

	private static Map<Operations, Operation> operMap = new EnumMap<>(Operations.class);

	public static Operation get(Operations operation) {
		Operation o = operMap.get(operation);
		if (o == null) {
			synchronized (operMap) {
				o = operMap.get(operation);
				if (o == null) {
					o = createOperation(operation);
					operMap.put(operation, o);
				}
			}
		}
		return o;
	}

	private static Operation createOperation(Operations operation) {
		switch (operation) {
		case ADD:
			return new AddOperation();
		case MULTIPLY:
			return new MultiplyOperation();
		default:
			return null;
		}
	}
}
