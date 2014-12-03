package service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import services.AccountService;
import domain.User;

@SessionScoped
@Named
public class AccountBacking implements Serializable{
	@EJB
	AccountService accountService;
	
	public User getUser(){
		return accountService.getUser();
	}
	
	public void login(User user){
		accountService.login(user);
	}
}
