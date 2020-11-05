package servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import dto.Staff;
import dto.StaffInfo;
import service.SearchService;
import servlet.base.BaseServlet;


@WebServlet(name = "select", urlPatterns = { "/select" })
public class SelectAction extends BaseServlet {

	@Override
	protected String getPageName() {
		return "select";
	}

	@Override
	protected String doAction() throws Exception {
		String[] pageParam = super.getInputParameter(
				"stfId" // 0
				, "stfUn" // 1
				, "stfDepId" // 2
				, "stfAuth" // 3
		);

		/*
		 * 検索の際にIDを入力しないとエラーになるバグ対応
		 */
		Integer stfId = null;

		// 入力チェック
		/*
		 * IDが数値でない場合は検索しなくても0件
		 */
		if (pageParam[0].trim().length() > 0) {
			try {
				/*
				 * 検索の際にIDを入力しないとエラーになるバグ対応
				 */
				stfId = Integer.parseInt(pageParam[0]);

			} catch (NumberFormatException e) {
				throw new Exception("入力された条件で情報が見つかりませんでした");
			}
		}

		// 画面入力値 -> DTOへ
		Staff staff = new Staff();
		/*
		 * 検索の際にIDを入力しないとエラーになるバグ対応
		 */
		//account.setId(Integer.parseInt(pageParam[0]));
		staff.setStaff_id(stfId);

		staff.setName(pageParam[1]);
		staff.setDepartment_id(Integer.parseInt(pageParam[2]));
		staff.setAuth(pageParam[3]);
		if (!"".equals(pageParam[2])) {
			staff.setDepartment_id(Integer.parseInt(pageParam[2]));
		}

		// 検索を行う
		SearchService service = new SearchService();
		List<StaffInfo> stfInfoList = service.searchStaffInfo(staff);

		// 検索結果の判定
		/*
		 * 検索結果が0件の際にエラーメッセージが表示されないバグ対応
		 */
		//if (empInfoList.size() == 0) {
		if (stfInfoList == null || stfInfoList.size() == 0) {
			throw new Exception("入力された条件で情報が見つかりませんでした");
		}

		// 取得した結果をセット
		super.request.setAttribute("stfInfoList", stfInfoList);
		return "selectResult";
	}

}