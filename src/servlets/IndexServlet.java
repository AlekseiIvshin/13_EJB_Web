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

import services.CarService;
import domain.Mark;

/**
 * Servlet implementation class HellotServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int COUNT_PER_PAGE = 10;

	@EJB
	CarService carService;

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
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		// try (PrintWriter writer = response.getWriter()) {
		// writer.println("Hello!");
		// writer.println("We have "+carService.getCarCount()+" cars marks!");
		// writer.println("See top 10:");
		// for(Mark mark: carService.getCars(0, 10)){
		// writer.println(mark.getName());
		// }
		// }
	}

	private int getPageCount(int count) {
		return (int) Math.ceil(count / COUNT_PER_PAGE);
	}
}
