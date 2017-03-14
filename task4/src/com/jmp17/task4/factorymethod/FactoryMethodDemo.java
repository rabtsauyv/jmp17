package com.jmp17.task4.factorymethod;

import java.util.Arrays;

public class FactoryMethodDemo {

	public void demo() {
		OperationFactory f = new OperationFactory();
		f.setList("list-1");
		
		TodoListOperation o1 = f.getOperation(Operations.fromString("add"), "task-1");
		TodoListOperation o2 = f.getOperation(Operations.fromString("show"));
		TodoListOperation o3 = f.getOperation(Operations.fromString("remove"), "6");
		TodoListOperation o4 = f.getOperation(Operations.fromString("clear"));
		
		for (TodoListOperation o : Arrays.asList(o1, o2, o3, o4)) {
			o.execute();
		}
	}
}
