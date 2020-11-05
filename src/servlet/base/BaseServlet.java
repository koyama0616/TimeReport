package servlet.base;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Department;
import dto.Staff;

public abstract class BaseServlet extends HttpServlet {
	//-------------------------------------- プロパティ群
	/** HttpServletRequest */
	protected HttpServletRequest request;
	/** HttpServletResponse */
	protected HttpServletResponse response;
	/** HttpSession */
	protected HttpSession session;
	/** ErrorMessage */
	protected String message;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		arg0.setCharacterEncoding("UTF-8");
		arg1.setContentType("text/html; charset=UTF-8");

		this.request = arg0;
		this.response = arg1;
		this.session = arg0.getSession();

		/*
		 * （エラーが表示されるようになると出るバグ）
		 * エラーメッセージが残ってしまうバグ対応
		 */
		this.message = null;

		String nextPage = this.getPageName();
		try {
			//			// ログインチェック
			if (!"login".equals(this.getPageName())) {
				if (session != null) {
					Staff staff = (Staff) session.getAttribute("LOGIN_STF");
					@SuppressWarnings("unchecked")
					List<Department> departmentList = (List<Department>) session.getAttribute("DEP_LIST");
					if ((staff == null || "".equals(staff.getName()))
							||
							(departmentList == null || departmentList.size() == 0)) {
						//						/*
						//						 * 不正アクセス時の遷移先バグに関する修正
						//						 */
						//						//nextPage = "login.jsp";
						nextPage = "index";

						throw new Exception("不正なログイン、またはログイン有効期間が過ぎています");
					}
				}
			}

			//			// 画面ごとの処理
			nextPage = this.doAction();
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
		}

				/*
				 * エラーメッセージ表示バグの修正　5段目
				 */
				//arg0.setAttribute("message", this.message);
		arg0.setAttribute("errMsg", this.message);

		arg0.getRequestDispatcher(nextPage + ".jsp").forward(arg0, arg1);
	}

	//-------------------------------------- Utilメソッド群
	protected String[] getInputParameter(String... names) {
		String[] values = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			values[i] = this.request.getParameter(names[i]);
			/*
			 * 入力チェック時に値を保持できないバグ修正・3段目　かなり汎用的なコーディング
			 */
			this.request.setAttribute(names[i], values[i]);
		}
		return values;
	}

	//-------------------------------------- 抽象メソッド群
	protected abstract String getPageName();

	protected abstract String doAction() throws Exception;
}