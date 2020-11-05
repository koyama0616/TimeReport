package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Java_Util;


@WebServlet(name = "getMonthDays", urlPatterns = { "/getMonthDays" })
public class GetMonthDays extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			//JSPから年月を取得
			String getMonth = req.getParameter("month");

			//nullか空文字だったら元のページに戻る
			if (getMonth == null || getMonth == "") {
				req.setAttribute("monthMessage", "月を選択して下さい");
			} else {
				//年と月を分ける
				Java_Util ju = new Java_Util();
				int year = ju.buildYear(getMonth);
				int month = ju.buildMonth(getMonth);

				//選択した月の日数を取得
				int result = ju.getMaximumDay(year, month);

				//月の日付に曜日を付けてコレクションに収納して値遷移
				List<String> monthDayList = ju.monthDays(year, month, result);
				HttpSession session = req.getSession();
				session.setAttribute("monthDaysList", monthDayList);
			}
		} catch (Exception e) {
			req.getRequestDispatcher("calender.jsp").forward(req, res);
		}
		req.getRequestDispatcher("calender.jsp").forward(req, res);
	}
}
