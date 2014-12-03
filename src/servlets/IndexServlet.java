package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import services.AccountService;
import services.CarService;
import domain.Mark;
import domain.User;

/**
 * Servlet implementation class HellotServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int COUNT_PER_PAGE = 10;

	@EJB
	CarService carService;
	
	@EJB
	AccountService accountService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REQUEST!");
		int page = 0;
		String pageParameter = request.getParameter("page");
		if (pageParameter != null && !pageParameter.isEmpty()) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		int markCount = carService.getCarCount();
		request.setAttribute("page", page);
		request.setAttribute("pageCount", getPageCount(markCount));
		request.setAttribute("markCount", markCount);
		request.setAttribute("markList",
				carService.getCars(page * COUNT_PER_PAGE, COUNT_PER_PAGE));
		User user = accountService.getUser();
		if(user!=null){
			request.setAttribute("username", user.getName());
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		if(username!=null && !username.isEmpty()){
			accountService.login(new User(username));
		}
		resp.sendRedirect(getServletContext().getContextPath()+"/index");
	}

	private int getPageCount(int count) {
		return (int) Math.ceil(count / COUNT_PER_PAGE);
	}
}
