package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet(name = "ChangeDivision", urlPatterns = { "/ChangeDivision" })
public class ChangeDivision extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		DAO getOpenDepDao = new DAO();
		String[] open = req.getParameterValues("openDep");
		String[] close = req.getParameterValues("closeDep");

		if (open != null) {
			String monthDays = (String) session.getAttribute("monthDays");
			System.out.println(monthDays);
			for (int i = 0; i < open.length; i++) {
				System.out.println(open[i]);
			}
			try {
				getOpenDepDao.changeCloseDivision(monthDays, open);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.getRequestDispatcher("calender.jsp").forward(req, res);

		} else if (close != null) {
			String monthDays = (String) session.getAttribute("monthDays");
			System.out.println(monthDays);
			for (int i = 0; i < close.length; i++) {
				System.out.println(close[i]);
			}
			try {
				getOpenDepDao.changeOpenDivision(monthDays, close);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			req.getRequestDispatcher("calender.jsp").forward(req, res);

		} else if (open == null || close == null) {
			req.getRequestDispatcher("calender.jsp").forward(req, res);
		}
	}
}
