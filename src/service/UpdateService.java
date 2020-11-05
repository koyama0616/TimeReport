package service;

import java.util.ArrayList;
import java.util.List;

import dao.StaffDAO;
import dto.Staff;
import service.base.BaseService;
import util.DbUtil;

public class UpdateService extends BaseService {

	public UpdateService() throws Exception {
		super(true);
	}

	public enum UPDATE_MODE {
		 INSERT
		,UPDATE
	}

	/**
	 * 社員の登録・更新を行う
	 * @param stf
	 * @return
	 * @throws Exception
	 */
	public int registStaff(Staff stf, UPDATE_MODE mode) throws Exception {
		StaffDAO dao = new StaffDAO(this.con);
		int count = -1;

		try {
			if (mode == UPDATE_MODE.INSERT) {
				count = dao.insertStaff(stf);
			} else if (mode == UPDATE_MODE.UPDATE){
				// DAO -> List<Object>（DAO#updateを呼び出すため）
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(stf.getStaff_id());
				paramList.add(stf.getMail());
				paramList.add(stf.getPass());
				paramList.add(stf.getName());
				paramList.add(stf.getDepartment_id());
				paramList.add(stf.getAuth());
				count = dao.updateByPrimaryKey(paramList, stf.getStaff_id());
			}
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			throw new Exception("登録できませんでした:" + e.getMessage());
		} finally {
			DbUtil.closeConnection(this.con);
		}
		return count;
	}

	/**
	 * 社員情報の物理削除を行う
	 * @param stfId
	 * @return
	 * @throws Exception
	 */
	public int deleteStaff(Integer stfId) throws Exception {
		StaffDAO dao = new StaffDAO(this.con);
		int deleteCount = -1;

		try {
			deleteCount = dao.deleteByPrimaryKey(stfId);
			this.con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.con.rollback();
			throw new Exception("削除できませんでした:" + e.getMessage());
		} finally {
			DbUtil.closeConnection(this.con);
		}
		return deleteCount;
	}

}
