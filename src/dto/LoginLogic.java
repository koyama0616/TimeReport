package dto;

import java.sql.SQLException;

import dao.StaffDAO;

public class LoginLogic {

	public Staff execute(Login login) throws ClassNotFoundException,SQLException{
		StaffDAO dao = new StaffDAO(null);
		return dao.findByLogin(login);
	}
}
