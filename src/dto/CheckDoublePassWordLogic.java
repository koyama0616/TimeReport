package dto;

import java.sql.SQLException;

import dao.StaffDAO;

public class CheckDoublePassWordLogic {

	public String execute(Staff staff) throws ClassNotFoundException,SQLException {

		StaffDAO dao = new StaffDAO(null);
		return dao.SearchPass(staff);
	}
}

