package dto;

//Staff.java(dto)
import dto.base.BaseEntity;



public class Staff implements BaseEntity{

	private Integer staff_id;//社員id
	private Integer department_id;


	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	private String mail; // メールアドレス
	private String pass; // パスワード
	private String name; // 名前
	private String auth; // 管理者権限


	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Staff() {}

	public Staff(int id, String mail,String pass,String name , String auth) {
		this.staff_id=id;
		this.mail = mail;
		this.pass = pass;
		this.name = name;
		this.auth = auth;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "[mail_address = "+this.mail+" / password = "+this.pass+" / name = "+this.name+" "+this.auth+"]";
	}

	public boolean equals (Object a) {
		if (a == this) return true;
		if (a == null) return false;
		if (!(a instanceof Staff)) return false;
		return true;
	}

	public int hashCode() {
		int result = 37; // 適当な数字
		result = result * 31 + mail.hashCode();
		result = result * 31 + pass.hashCode();
		result = result * 31 + name.hashCode();
		return result;
	}
}
