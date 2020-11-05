package servlet;

import javax.servlet.annotation.WebServlet;

import dto.Staff;
import service.UpdateService;
import service.UpdateService.UPDATE_MODE;
import servlet.base.BaseServlet;
//import service.UpdateService.UPDATE_MODE;



@WebServlet(name="insert", urlPatterns={"/insert"})
public class InsertAction extends BaseServlet {
	@Override
	protected String getPageName() {
		return "insertConfirm";
	}

	@Override
	protected String doAction() throws Exception {

		String[] pageParam = super.getInputParameter(
				 "stfName"		// 0
				,"stfPass"		// 1
				,"stfDepId"		// 2
				,"stfAuth"		// 3
				,"passConfirm"		// 4
				/*
				 * 登録->内容編集時の値保持バグの修正
				 */
				,"editFlg"	// 5
		);

		/*
		 * 登録->内容編集時の値保持バグの修正
		 */
		if ("edit".equals(pageParam[5])) {
			return "insert";
		}

		if (!pageParam[1].equals(pageParam[4])) {
			throw new Exception("パスワードが一致していません");
		}

		Staff stf = new Staff();
		stf.setName(pageParam[0]);
		stf.setPass(pageParam[1]);
		stf.setDepartment_id(Integer.parseInt(pageParam[2]));
		stf.setAuth(pageParam[3]);
		//acc.setIdDepartment(Integer.parseInt(pageParam[5]));

		UpdateService service = new UpdateService();
		service.registStaff(stf, UPDATE_MODE.INSERT);

		/*
		 * 登録結果画面へ遷移できないバグを修正
		 */
		// return "insertResult.jsp";
		return "insertResult";
	}
}
