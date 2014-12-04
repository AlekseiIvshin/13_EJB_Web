package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountBacking;
import domain.User;

/**
 * Servlet implementation class Account
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	@Inject
	AccountBacking accountBacking;

	/**
	 * Do login.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		accountBacking.logout();
		response.sendRedirect(getServletContext().getContextPath() + "/index");
		
	}

	/**
	 * Do logout.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		if (username != null && !username.isEmpty()) {
			accountBacking.login(new User(username));
		}
		response.sendRedirect(getServletContext().getContextPath() + "/index");
	}

}
