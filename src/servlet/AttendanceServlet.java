package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AttendanceServlet" , urlPatterns = {"/AttendanceServlet"} )

public class AttendanceServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




	String forwardPath = "/WEB-INF/jsp/attendance.jsp";
	request.getRequestDispatcher(forwardPath).forward(request, response);

	}

}
