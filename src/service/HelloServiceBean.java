package service;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import services.CarService;
import domain.Mark;

@WebService
public class HelloServiceBean {
	
	@EJB
	CarService carService;
	
	public HelloServiceBean() {	}
	
	@WebMethod
	public Mark	getMark(long id){
		return carService.getMark(id);
	}
	
	@WebMethod
	public List<Mark> getMarks(int offset, int count){
		return carService.getCars(offset, count);
	}
	
	@WebMethod
	public int getMarkCount(){
		return carService.getCarCount();
	}
}
