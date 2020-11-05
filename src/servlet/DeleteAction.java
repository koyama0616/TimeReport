package servlet;

import javax.servlet.annotation.WebServlet;

import service.UpdateService;
import servlet.base.BaseServlet;



/**
 * 社員情報削除を行う画面に対応したページクラス
 */
@WebServlet(name="delete", urlPatterns={"/delete"})
public class DeleteAction extends BaseServlet {
	@Override
	protected String getPageName() {
		return "deleteConfirm";
	}

	@Override
	protected String doAction() throws Exception {

		String[] pageParam = super.getInputParameter(
				 "accId"		// 0
		);

		UpdateService service = new UpdateService();
		service.deleteStaff(Integer.parseInt(pageParam[0]));

		return "deleteResult";
	}

}
