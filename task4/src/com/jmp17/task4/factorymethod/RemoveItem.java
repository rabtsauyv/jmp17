package com.jmp17.task4.factorymethod;

public class RemoveItem implements TodoListOperation {

	private final String list;
	private final int index;
	
	public RemoveItem(String list, int index) {
		this.list = list;
		this.index = index;
	}

	@Override
	public String execute() {
		String msg = index + "th task was removed from " + list;
		System.out.println(msg);
		return msg;
	}
}
