package com.jmp17.task4.factorymethod;

public class RemoveAll implements TodoListOperation {

	private final String list;
	
	public RemoveAll(String list) {
		this.list = list;
	}
	
	@Override
	public String execute() {
		String msg = "removing all tasks from " + list;
		System.out.println(msg);
		return msg;
	}

}
