package com.jmp17.task4.factorymethod;

public class OperationFactory {
	
	private String list;
	
	public void setList(String list) {
		this.list = list;
	}

	public TodoListOperation getOperation(Operations operation, String... args) {
		switch (operation) {
		case ADD:
			String arg = args[0];
			return new AddItem(list, arg);
		case SHOW:
			return new ShowItems(list);
		case REMOVE:
			try {
				int index = Integer.parseInt(args[0]);
				if (index < 1) {
					throw new IllegalArgumentException("valid index is required");
				}
				return new RemoveItem(list, index-1);
				
			} catch (NumberFormatException  e) {
				throw new IllegalArgumentException("valid integer is required");
			}
		case CLEAR:
			return new RemoveAll(list);
		default:
			throw new IllegalArgumentException("unknown operation: " + operation);
		}
	}
}
