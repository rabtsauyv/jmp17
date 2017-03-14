package com.jmp17.task4.factorymethod;

public class ShowItems implements TodoListOperation {

	private final String list;
	
	public ShowItems(String list) {
		this.list = list;
	}
	
	@Override
	public String execute() {
		String msg = "showing tasks from " + list;
		System.out.println(msg);
		return msg;
	}

}
