package com.jmp17.task5.run;

import com.jmp17.task5.adapter.BinaryAdd;
import com.jmp17.task5.adapter.BinaryAddAdapter;
import com.jmp17.task5.adapter.BinaryOperation;
import com.jmp17.task5.composite.Expression;
import com.jmp17.task5.composite.Operand;
import com.jmp17.task5.decorator.BaseIcecream;
import com.jmp17.task5.decorator.Caramel;
import com.jmp17.task5.decorator.Chocolate;
import com.jmp17.task5.decorator.Icecream;
import com.jmp17.task5.decorator.Vanilla;
import com.jmp17.task5.facade.Facade;
import com.jmp17.task5.flyweight.OperationFactory;
import com.jmp17.task5.flyweight.Operations;

public class Demo {

	public static void main(String[] args) {
		Demo demo = new Demo();
		
		demo.demoFacade();
		demo.demoFlyweightAndComposite();
		demo.demoAdapter();
		demo.demoDecorator();
	}

	public void demoFacade() {
		System.out.println("-------------------------- Facade ---------------------");
		Facade f = new Facade();
		f.task1();
		f.complexTask2();
		f.task3();
		f.task4();
	}
	
	public void demoFlyweightAndComposite() {
		System.out.println("-------------------------- Flyweight & Composite ---------------------");
		System.out.println("evaluate (2+3+4)*(6-10+3)*9");
		Expression e1 = new Expression();
		e1.setOperation(OperationFactory.get(Operations.ADD));
		e1.addSubExpression(new Operand(2));
		e1.addSubExpression(new Operand(3));
		e1.addSubExpression(new Operand(4));
		
		Expression e2 = new Expression();
		e2.setOperation(OperationFactory.get(Operations.ADD));
		e2.addSubExpression(new Operand(6));
		e2.addSubExpression(new Operand(-10));
		e2.addSubExpression(new Operand(3));
		
		Expression eMain = new Expression();
		eMain.setOperation(OperationFactory.get(Operations.MULTIPLY));
		eMain.addSubExpression(e1);
		eMain.addSubExpression(e2);
		eMain.addSubExpression(new Operand(9));
		
		System.out.println(eMain.calculate());
	}
	
	public void demoAdapter() {
		System.out.println("-------------------------- Adapter ---------------------");
		BinaryOperation addNative = new BinaryAdd();
		BinaryOperation addAdapter = new BinaryAddAdapter();
		
		System.out.println("evaluate 2+6");
		System.out.println("native  implementation: " + addNative.evaluate(2, 6));
		System.out.println("adapter implementation: " + addAdapter.evaluate(2, 6));
	}
	
	public void demoDecorator() {
		System.out.println("-------------------------- Decorator ---------------------");
		Icecream ice1 = new BaseIcecream();
		Icecream iceCh = new Chocolate(ice1);
		Icecream iceV = new Vanilla(ice1);
		Icecream iceC = new Caramel(ice1);
		Icecream iceVVC = new Vanilla(new Vanilla(new Caramel(new BaseIcecream())));
		Icecream iceChChCh = new Chocolate(new Chocolate(new Chocolate(ice1)));
		
		System.out.println("icecream costs " + ice1.cost());
		System.out.println("chocolate icecream costs " + iceCh.cost());
		System.out.println("vanilla icecream costs " + iceV.cost());
		System.out.println("caramel icecream costs " + iceC.cost());
		System.out.println("double vanilla caramel icecream costs " + iceVVC.cost());
		System.out.println("super chocolate icecream costs " + iceChChCh.cost());
	}
}
