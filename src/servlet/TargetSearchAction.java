package servlet;

import javax.servlet.annotation.WebServlet;

import dto.Staff;
import service.SearchService;
import servlet.base.BaseServlet;

@WebServlet(name="targetSearch", urlPatterns={"/targetSearch"})
public class TargetSearchAction extends BaseServlet {
	@Override
	protected String getPageName() {
		String page = super.getInputParameter("page")[0];
		return "update".equals(page) ? "update" : "delete";
	}
	@Override
	protected String doAction() throws Exception {
		String[] pageParam = super.getInputParameter(
				 "stfId"		// 0
				,"page"			// 1
		);

		int stfId = -1;
		try {
			stfId = Integer.parseInt(pageParam[0]);
		} catch (NumberFormatException e) {
			throw new Exception("入力された社員IDで社員情報が見つかりませんでした");
		}

		SearchService service = new SearchService();
		Staff staff = service.searchStaffByPkey(stfId);

		if (staff == null) {
			throw new Exception("入力された社員IDで社員情報が見つかりませんでした");
		}

		super.request.setAttribute("stfId", staff.getStaff_id());
		super.request.setAttribute("stfName", staff.getName());
		super.request.setAttribute("stfPass", staff.getPass());
		super.request.setAttribute("stfDepId", staff.getDepartment_id());
		super.request.setAttribute("stfAuth", staff.getAuth());

		return "update".equals(pageParam[1]) ? "updateInput" : "deleteConfirm";
	}
}

