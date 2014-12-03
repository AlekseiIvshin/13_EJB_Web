package servlets;

import java.io.IOException;
import java.net.URL;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Mark;
import services.CarService;

/**
 * Servlet implementation class MarkCreate
 */
@WebServlet("/mark.create")
public class MarkCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	CarService carService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarkCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String status="";
		if(name!=null && !name.isEmpty()){
			carService.createMark(new Mark(name));
			status = "?success=Mark created!";
		}else {
			status = "?error=Mark name is empty!";
		}
		response.sendRedirect(getServletContext().getContextPath()+"/index"+status);
	}

}
