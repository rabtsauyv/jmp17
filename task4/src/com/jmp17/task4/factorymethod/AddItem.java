package com.jmp17.task4.factorymethod;

public class AddItem implements TodoListOperation {

	private final String list;
	private final String item;
	
	public AddItem(String list, String item) {
		this.list = list;
		this.item = item;
	}
	
	@Override
	public String execute() {
		String msg = "task " + item + " added to " + list;
		System.out.println(msg);
		return msg;
	}

}
