package servlet;

import javax.servlet.annotation.WebServlet;

import dto.Staff;
import service.SearchService;
import servlet.base.BaseServlet;

@WebServlet(name="inputCheck", urlPatterns={"/inputCheck"})
public class InputCheckAction  extends BaseServlet {

	@Override
	protected String getPageName() {
		System.out.println("ここは通ってますか？1");
		return "insert";
	}

	@Override
	protected String doAction() throws Exception {
		String[] pageParam = super.getInputParameter(
				 "stfId"	// 0
				,"stfMail"	// 1
				,"stfPass"		// 2
				,"stfName"		// 3
				,"stfDepId"		// 4
				,"stfAuth"		// 5

			);
		System.out.println("ここは通ってますか？2");

		Staff staff = new Staff();
		staff.setName(pageParam[1]);

		SearchService service = new SearchService();
		if (service.checkDuplicationMail(null, pageParam[1])) {
			throw new Exception("入力されたメールアドレスは既に存在しています");
		}

		return "insertConfirm";
	}
}