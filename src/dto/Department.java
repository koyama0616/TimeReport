package dto;

import dto.base.BaseEntity;

public class Department implements BaseEntity {
	public static final String TITLE_DEPARTMENT_ID = "部署ID";
	public static final String TITLE_DEPARTMENT_NAME = "部署名";
	public static final String TITLE_WORKDAY_MON = "月曜日";
	public static final String TITLE_WORKDAY_TUE = "火曜日";
	public static final String TITLE_WORKDAY_WED = "水曜日";
	public static final String TITLE_WORKDAY_THU = "木曜日";
	public static final String TITLE_WORKDAY_FRI = "金曜日";
	public static final String TITLE_WORKDAY_SAT = "土曜日";


	private Integer department_id;

	private String department_name;

	private Boolean workday_sun;

	private Boolean workday_mon;

	private Boolean workday_tue;

	private Boolean workday_wed;

	private Boolean workday_thu;

	private Boolean workday_fri;

	private Boolean workday_sat;




	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Boolean getWorkday_sun() {
		return workday_sun;
	}

	public void setWorkday_sun(Boolean workday_sun) {
		this.workday_sun = workday_sun;
	}

	public Boolean getWorkday_mon() {
		return workday_mon;
	}

	public void setWorkday_mon(Boolean workday_mon) {
		this.workday_mon = workday_mon;
	}

	public Boolean getWorkday_tue() {
		return workday_tue;
	}

	public void setWorkday_tue(Boolean workday_tue) {
		this.workday_tue = workday_tue;
	}

	public Boolean getWorkday_wed() {
		return workday_wed;
	}

	public void setWorkday_wed(Boolean workday_wed) {
		this.workday_wed = workday_wed;
	}

	public Boolean getWorkday_thu() {
		return workday_thu;
	}

	public void setWorkday_thu(Boolean workday_thu) {
		this.workday_thu = workday_thu;
	}

	public Boolean getWorkday_fri() {
		return workday_fri;
	}

	public void setWorkday_fri(Boolean workday_fri) {
		this.workday_fri = workday_fri;
	}

	public Boolean getWorkday_sat() {
		return workday_sat;
	}

	public void setWorkday_sat(Boolean workday_sat) {
		this.workday_sat = workday_sat;

	}
}
