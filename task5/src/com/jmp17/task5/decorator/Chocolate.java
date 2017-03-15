package com.jmp17.task5.decorator;

public class Chocolate extends IcecreamDecorator {

	public Chocolate(Icecream base) {
		super(base);
	}

	@Override
	public int cost() {
		return 5 + base.cost();
	}
}
