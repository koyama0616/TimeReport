package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CheckMailAddress;
import dto.CheckMailAddress.ErrorMsgMail;
import dto.CheckPassWord;
import dto.CheckPassWord.ErrorMsgPass;
import dto.Login;
import dto.LoginLogic;
import dto.Staff;

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");

		System.out.println(mail);
		System.out.println(pass);

		String msg = "";
		String forwardPath = "";

		// メールチェック
		CheckMailAddress m = new CheckMailAddress();
		ErrorMsgMail check_mail = m.checkMail(mail);

		switch (check_mail) {
			case OK:
				break;
			default:
				msg = "ログインに失敗しました";
				forwardPath = "/index.jsp";
		}

		// パスワードチェック
		CheckPassWord p = new CheckPassWord();
		ErrorMsgPass check_pass = p.checkPass(pass);

		switch (check_pass) {
			case OK:
				break;
			default:
				msg = "ログインに失敗しました";
				forwardPath = "/index.jsp";
		}

		if (!forwardPath.equals("")) {

			request.setAttribute("msg", msg);

			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
			return;
		}

		// ログイン処理
		try {

			System.out.println("ログイン処理");

			Login login = new Login(mail,pass);
			LoginLogic bo = new LoginLogic();
			Staff staff = bo.execute(login);

			if (staff == null) {   // 一致するユーザがない
				msg = "ログインに失敗しました";
				forwardPath = "/index.jsp";

			} else {   // 一致するユーザがある
				String random_word = UUID.randomUUID().toString();

				HttpSession session = request.getSession();
				// セッションからとってきたaccountの型で変数を作ってるだけ
				// 最初、セッションスコープのaccount自体保存されていないので"null"になる
				Map<String, String> account = (Map<String, String>) session.getAttribute("account");

				// HashMap上書き防止
				if (account == null) {
					account = new HashMap<String, String>();
					System.out.println("ifの中="+random_word);
				}

				account.put(random_word, staff.getMail());

				System.out.println("ifの外="+random_word);
				System.out.println("ifの外="+staff.getAuth());
				System.out.println("ifnosoto="+staff.getStaff_id());
				request.setAttribute("random_word",random_word);

				session.setAttribute("account", account);
				session.setAttribute("staff.getName()",staff.getName());
				session.setAttribute("staff", staff);


				forwardPath = "/WEB-INF/jsp/attendance.jsp";
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			msg = "システムエラーが発生しました。管理者にご連絡ください";
			forwardPath = "/index.jsp";
		}

		request.setAttribute("msg", msg);

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}


}