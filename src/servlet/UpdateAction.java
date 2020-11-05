package servlet;

import javax.servlet.annotation.WebServlet;

import dto.Staff;
import service.SearchService;
import service.UpdateService;
import service.UpdateService.UPDATE_MODE;
import servlet.base.BaseServlet;
//import service.UpdateService.UPDATE_MODE;

@WebServlet(name="update", urlPatterns={"/update"})
public class UpdateAction extends BaseServlet {

	@Override
	protected String getPageName() {
		return "updateInput";
	}

	@Override
	protected String doAction() throws Exception {
		// データを取得
		String[] pageParam = super.getInputParameter(
				 "stfId"				// 0
				,"stfName"				// 1
				,"stfMail"				// 2
				,"stfPass"				// 3
				,"passConf"				// 4
				,"passOld"				// 5
				,"depId"				// 6
		);
		String pass = pageParam[6];

		if (!"".equals(pageParam[3]) || !"".equals(pageParam[4])) {
			if (!pageParam[3].equals(pageParam[4])) {
				throw new Exception("パスワードが一致していません");
			}
			pass = pageParam[3];
		}

		SearchService sService = new SearchService();
		if (sService.checkDuplicationMail(Integer.parseInt(pageParam[0]), pageParam[1])) {
			throw new Exception("入力された名前は既に存在しています");
		}

		UpdateService uService= new UpdateService();
		Staff stf = new Staff();
		stf.setStaff_id(Integer.parseInt(pageParam[0]));
		stf.setName(pageParam[1]);
		stf.setMail(pageParam[2]);
		stf.setDepartment_id(Integer.parseInt(pageParam[3]));
		stf.setPass(pass);
		//stf.setIdDepartment(Integer.parseInt(pageParam[7]));

		uService.registStaff(stf, UPDATE_MODE.UPDATE);
		return "updateResult";
	}
}