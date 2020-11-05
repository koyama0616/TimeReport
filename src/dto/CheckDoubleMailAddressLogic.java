package dto;

import java.sql.SQLException;

import dao.StaffDAO;

public class CheckDoubleMailAddressLogic {

	public boolean execute(String mail) throws ClassNotFoundException,SQLException {

		StaffDAO dao = new StaffDAO(null);
		return dao.isDoubleMail(mail);
	}
}

