package servlets;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AccountBacking;
import services.CarService;
import services.Config;
import services.HardProcess;
import domain.User;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	Config config;

	private static final int COUNT_PER_PAGE = 10;

	@EJB
	CarService carService;

	@EJB
	HardProcess hardProccess;

	@Inject
	AccountBacking accountBacking;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REQUEST!");
		initMarksAndPages(request);
		initUserData(request);
		if (request.getParameter("hard") != null) {
			initHardProcess(request);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void initMarksAndPages(HttpServletRequest request) {
		int page = 0;
		String pageParameter = request.getParameter("page");
		if (pageParameter != null && !pageParameter.isEmpty()) {
			page = Integer.valueOf(request.getParameter("page"));
		}
		String strMarkCountPerPage = config.getValue("markCountPerPage");
		int markCountPerPage = COUNT_PER_PAGE;
		if (strMarkCountPerPage != null && !strMarkCountPerPage.isEmpty()) {
			markCountPerPage = Integer.parseInt(strMarkCountPerPage);
		}

		int markCount = carService.getCarCount();
		int pageCount = getPageCount(markCount, markCountPerPage);
		page = Math.min(page, pageCount - 1);
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("markCount", markCount);
		request.setAttribute("markList",
				carService.getCars(page * markCountPerPage, markCountPerPage));
	}

	private void initUserData(HttpServletRequest request) {
		User user = accountBacking.getUser();
		if (user != null) {
			request.setAttribute("username", user.getName());
		}
	}

	private void initHardProcess(HttpServletRequest request) {
		String value = request.getParameter("hard");
		Future<String> resvalue = hardProccess.process(value);
		String status;
		try {
			status = resvalue.get().equals(value) ? "success" : "error";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			status = e.getMessage();
		}
		request.setAttribute("hardStatus", status);
	}

	private int getPageCount(int count, int countOnPage) {
		return (int) Math.round((double) count / countOnPage);
	}
}
