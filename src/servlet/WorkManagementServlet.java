package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	@WebServlet(name = "WorkManagementServlet" , urlPatterns = { "/WorkManagementServlet" })
	public class WorkManagementServlet extends HttpServlet {

		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			String forwardPath = "/WEB-INF/jsp/workManagement.jsp";
			request.getRequestDispatcher(forwardPath).forward(request, response);



		}
}

