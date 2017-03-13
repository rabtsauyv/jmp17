package com.jmp17.task5.decorator;

public class Caramel extends IcecreamDecorator {

	public Caramel(Icecream base) {
		super(base);
	}

	@Override
	public int cost() {
		return 3 + base.cost();
	}

}
