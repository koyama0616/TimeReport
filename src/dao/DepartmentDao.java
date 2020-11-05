package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.base.BaseDao;
import dto.Department;

public class DepartmentDao extends BaseDao<Department> {

	public DepartmentDao(Connection con) {
		super(con);
	}

	@Override
	protected String getTableName() {
		return "department";
	}

	@Override
	protected Department rowMapping(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setDepartment_id((Integer) rs.getInt("department_id"));
		department.setDepartment_name((String) rs.getString("department_name"));
		return department;
	}

	@Override
	protected String[] getColumnsList() {
		return new String[] {
				 "department_id"
				,"department_name"
		};
	}

	@Override
	protected String[] getPrimaryKey() {
		return new String[] {
				 "department_id"
		};
	}

}
