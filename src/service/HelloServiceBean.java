package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class HelloServiceBean {

	private final String message = "Hello, ";
	
	public HelloServiceBean() {	}
	
	@WebMethod
	public String sayHello(String name){
		return message+name;
	}
}
