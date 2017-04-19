package com.jmp17.task10;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HelloControllerTest {
	
	private HelloController controller; 

	@Before
	public void setUp() throws Exception {
		controller = new HelloController();
	}

	@Test
	public void testSayHello() {
		String actual = controller.sayHello();
		assertEquals("hello", actual);
	}

}
