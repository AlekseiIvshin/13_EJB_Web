package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CarService;
import domain.Mark;

@WebServlet("/mark.create")
public class MarkCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CarService carService;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String status = "";
		if (name != null && !name.isEmpty()) {
			carService.createMark(new Mark(name));
			status = "?success=Mark created!";
		} else {
			status = "?error=Mark name is empty!";
		}
		response.sendRedirect(getServletContext().getContextPath() + "/index"
				+ status);
	}

}
