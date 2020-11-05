package dto;

import dto.base.BaseEntity;

public class StaffInfo implements BaseEntity {
	public static final String TITLE_DEPARTMENT_ID = "部署ID";

	/** 社員情報 */
	private Staff staff;
	/** 部署名 */
	private String department_name;

	public StaffInfo() {}
	public StaffInfo(Staff stf) {
		this.staff = stf;
	}

	/**
	 * 社員情報を取得します
	 * @return 社員情報
	 */
	public Staff getStaff() {
		return staff;
	}
	/**
	 * 社員情報を設定します
	 * @param account 社員情報
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

}