package service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import services.AccountService;
import domain.User;

@SessionScoped
@Named
public class AccountBacking implements Serializable {

	private static final long serialVersionUID = -825561046720852238L;

	@EJB
	AccountService accountService;

	public User getUser() {
		return accountService.getUser();
	}

	public void login(User user) {
		accountService.login(user);
	}
	
	public void logout(){
		accountService.logout();
	}
}
