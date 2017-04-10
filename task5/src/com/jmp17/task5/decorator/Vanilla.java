package com.jmp17.task5.decorator;

public class Vanilla extends IcecreamDecorator {

	public Vanilla(Icecream base) {
		super(base);
	}

	@Override
	public int cost() {
		return 2 + base.cost();
	}
}
