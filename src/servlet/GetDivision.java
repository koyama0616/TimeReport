package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import dto.Workday_flagDTO;

@WebServlet(name = "GetDivision", urlPatterns = { "/GetDivision" })
public class GetDivision extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//JSPから日付と曜日を取得
		String getMonthDays = req.getParameter("date");
		System.out.println(getMonthDays);

		HttpSession session = req.getSession();
		session.setAttribute("monthDays", getMonthDays);

		//出社、休業している事業部をリストに入れて値遷移
		if (getMonthDays == null || getMonthDays == "") {
			req.setAttribute("daysMessage", "日を選択して下さい");
		} else {
			DAO getOpenDao = new DAO();
			List<Workday_flagDTO> wfd;
			try {
				wfd = getOpenDao.findOpen(getMonthDays);

				req.setAttribute("departmentList", wfd);
				for (Workday_flagDTO w : wfd) {
					System.out.println(w.getId());
					System.out.println(w.getName());
					System.out.println(w.isWorkflag());
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		req.getRequestDispatcher("calender.jsp").forward(req, res);
	}
}
