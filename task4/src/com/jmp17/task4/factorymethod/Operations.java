package com.jmp17.task4.factorymethod;

public enum Operations {
	ADD("add"), SHOW("show"), REMOVE("remove"), CLEAR("clear");
	
	private final String operation;
	
	private Operations(String operation) {
		this.operation = operation;
	}
	
	public static Operations fromString(String value) {
		if (value == null) {
			return null;
		}
		String v = value.toLowerCase();
		for (Operations o : Operations.values()) {
			if (o.operation.equals(v)) {
				return o;
			}
		}
		return null;
	}
}
