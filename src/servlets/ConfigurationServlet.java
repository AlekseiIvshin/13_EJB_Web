package servlets;

import java.io.IOException;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Config;

/**
 * Servlet implementation class ConfigurationBean
 */
@WebServlet("/configuration")
public class ConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	Config config;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Set<String> configNames = config.getConfigNames();
		for (String c : configNames) {
			String configValue = request.getParameter(c);
			if(configValue!=null){
				config.setConfig(c, configValue);
			}
		}
		response.sendRedirect(getServletContext().getContextPath() + "/index");
	}

}
