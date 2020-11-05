package service;

import java.util.List;

import dao.DepartmentDao;
import dao.StaffDAO;
import dto.Department;
import dto.Staff;
import dto.StaffInfo;
import service.base.BaseService;
import util.DbUtil;


public class SearchService extends BaseService {

	public SearchService() throws Exception {
		super(false);
	}
//
//	/**
//	 * メールアドレス、パスワードでログイン判定を行う
//	 * @param username
//	 * @param pass
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Object> doLogin(String username, String pass) throws Exception {
//		Map<String, Object> loginData = new HashMap<String, Object>();
//		StaffDao dao = new StaffDao(this.con);
//		try {
//			loginData.put("STF", dao.selectLoginInfo(username, pass));
//			loginData.put("DEP_LIST", this.getDepartmentList());
//		} finally {
//			DbUtil.closeConnection(this.con);
//		}
//		return loginData;
//	}

	/**
	 * 部署一覧を取得する
	 * @return
	 */
	private List<Department> getDepartmentList() throws Exception {
		DepartmentDao dao = new DepartmentDao(this.con);
		List<Department> depList = null;
		depList = dao.findAll();
		return depList;
	}

	/**
	 * 社員検索を行う
	 * @param form
	 * @return
	 */
	public List<StaffInfo> searchStaffInfo(Staff stf) throws Exception {
		StaffDAO dao = new StaffDAO(this.con);
		List<StaffInfo> stfInfoList = null;
		try {
			stfInfoList = dao.findByParam(stf);
			if (stfInfoList.size() == 0) {
				stfInfoList = null;
			}
		} finally {
			DbUtil.closeConnection(this.con);
		}
		return stfInfoList;
	}

	/**
	 * 社員情報の主キー検索を行う
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public Staff searchStaffByPkey(Integer stfId) throws Exception {
		StaffDAO dao = new StaffDAO(this.con);
		Staff staff = null;
		try {
			staff = dao.findByPramaryKey(stfId);
		} finally {
			DbUtil.closeConnection(this.con);
		}
		return staff;
	}

	/**
	 * 社員名の重複チェック
	 * @param id
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean checkDuplicationMail(Integer id, String mail) throws Exception {
		//社員名で検索を行う
		StaffDAO dao = new StaffDAO(this.con);
		Staff stf = new Staff();
		try {
			stf.setMail(mail);
			List<StaffInfo> stfList = dao.findByParam(stf);

			for (StaffInfo stfInfo : stfList) {//登録しようとしている社員名に一致するデータがDBに存在した場合
				/*
				 * 登録・更新時の社員名重複チェックエラー修正
				 */
				if (id == null) {//新規登録の場合
						return true;//重複

					//更新で、かつmail重複データが更新対象者以外のデータの場合（社員idが異なる場合）
				}else if(id.equals(stfInfo.getStaff().getStaff_id()) == false) {
					return true;//重複

				}
			}

		} finally {
			DbUtil.closeConnection(this.con);
		}
		return false;
	}

}
