package com.jmp17.task4.factorymethod;

import java.util.Arrays;

public class FactoryMethodDemo {

	public void demo() {
		OperationFactory f = new OperationFactory();
		f.setList("list-1");
		
		TodoListOperation o1 = f.getOperation("add", "task-1");
		TodoListOperation o2 = f.getOperation("show");
		TodoListOperation o3 = f.getOperation("remove", "6");
		TodoListOperation o4 = f.getOperation("clear");
		
		for (TodoListOperation o : Arrays.asList(o1, o2, o3, o4)) {
			o.execute();
		}
	}
}
