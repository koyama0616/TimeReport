package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TimeDAO;
import dto.Staff;
import util.Java_Util;

@WebServlet(name = "GetAttendanceServlet", urlPatterns = { "/GetAttendanceServlet" })

public class GetAttendanceServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String getMonth = request.getParameter("month");
		System.out.println(getMonth);

		Java_Util ju = new Java_Util();
		Date firstDay = ju.getFirstDay(getMonth);
		System.out.println(firstDay);
		int year = ju.buildYear(getMonth);
		int month = ju.buildMonth(getMonth);
		int maximumDay = ju.getMaximumDay(year, month);
		Date lastDay = ju.getLastDay(getMonth, maximumDay);
		System.out.println(lastDay);

		Staff staff = new Staff();
		staff = (Staff) session.getAttribute("staff");
		staff.getStaff_id();



		TimeDAO dao = new TimeDAO();
		dao.GetAttendance(staff.getStaff_id(), firstDay, lastDay);






	}
}
