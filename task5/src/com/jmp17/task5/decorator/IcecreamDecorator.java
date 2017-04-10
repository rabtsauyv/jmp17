package com.jmp17.task5.decorator;

public abstract class IcecreamDecorator implements Icecream {
	
	protected Icecream base;
	
	protected IcecreamDecorator(Icecream base) {
		this.base = base;
	}
}
