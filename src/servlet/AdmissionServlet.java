package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TimeDAO;
import dto.Staff;
import util.Java_Util;

@WebServlet(name = "AdmissionServlet", urlPatterns = { "/AdmissionServlet" })

public class AdmissionServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		Java_Util ju = new Java_Util();

		Staff staff = new Staff();
		staff = (Staff) session.getAttribute("staff");
		System.out.println(staff.getStaff_id());

		TimeDAO dao = new TimeDAO();

		boolean attendanceJudge = dao.insertAttendanceTime(staff.getStaff_id(), ju.changeSqlDate(), ju.changeSqlTime());
		String forwardPath = "";
		String errorMsg = "";

		if(attendanceJudge) {
			forwardPath = "/WEB-INF/jsp/admissionOK.jsp";
		}else {
			errorMsg ="本日はすでに出社済みです";
			forwardPath = "/WEB-INF/jsp/attendance.jsp";
		}
		request.setAttribute("errorMsg",errorMsg);

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//   2020-11-03 16:09:00-00

	}

}
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String random_word = request.getParameter("random_word");
//		request.setCharacterEncoding("UTF-8");
//
//		HttpSession session = request.getSession();
//		// キーからメールアドレスを取得
//		Map<String, String> account = (Map<String, String>)session.getAttribute("account");
//		String mail = account.get(random_word);
//
//		// フォーマットはモデルにさせる
//		Date today = new Date();
//
//		String forwardPath = "";
//		String errorMsg = "";
//
//		try {
//			PostAdmissionLogic postAdmissionLogic = new PostAdmissionLogic();
//			boolean canAdmission = postAdmissionLogic.isExecute(mail,today);
//
//			// 出社できれば画面遷移します
//			if (canAdmission) {
//				request.setAttribute("random_word",random_word);
//				forwardPath = "/WEB-INF/jsp/admissionOK.jsp";
//
//			// できなければエラーメッセージ出力
//			} else {
//				errorMsg ="本日はすでに出社済みです";
//				forwardPath = "/WEB-INF/jsp/attendance.jsp";
//			}
//
//		// 例外処理
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			errorMsg = "システムエラーが発生しました。管理者にご連絡ください";
//			forwardPath = "/WEB-INF/jsp/attendance.jsp";
//		}
//
//		request.setAttribute("errorMsg",errorMsg);
//		// 二度目以降もmailに値が入るように保存
//		request.setAttribute("random_word",random_word);
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
//		dispatcher.forward(request, response);
//	}
//}