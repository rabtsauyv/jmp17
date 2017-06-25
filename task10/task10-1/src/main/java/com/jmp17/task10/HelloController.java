package com.jmp17.task10;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public class HelloController {

	@WebMethod(operationName="getHelloWorld")
	public String sayHello() {
		return "hello";
	}
}
