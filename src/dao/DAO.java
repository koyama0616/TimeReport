package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Workday_flagDTO;
import util.Java_Util;

public class DAO {

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rset = null;
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = "rebellion17";

	//取得した曜日の出社、休業の事業部をリストに入れて返すメソッド
	public List<Workday_flagDTO> findOpen(String monthDays) throws SQLException {
		List<Workday_flagDTO> WorkflagList = new ArrayList<>();
		Java_Util ju = new Java_Util();
		String dayOfTheWeek = ju.workDays(monthDays);

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "select department_id,department_name," + dayOfTheWeek + " from department where ? = '1'";
			stmt = conn.prepareStatement(sql);

			stmt.setBoolean(1, ju.BooleanWorkDays(monthDays));

			rset = stmt.executeQuery();
			while (rset.next()) {
				Workday_flagDTO wfd = new Workday_flagDTO();
				wfd.setId(rset.getInt("department_id"));
				wfd.setName(rset.getString("department_name"));

				//曜日で取得する値が変わる。（見づらいですけどこれ以外方法が見つかりませんでした。。。見やすいですよ！！！！！！！！！！！！！）
				if (dayOfTheWeek.contains("sun")) {
					wfd.setWorkflag(rset.getBoolean("workday_sun"));
				}
				if (dayOfTheWeek.contains("mon")) {
					wfd.setWorkflag(rset.getBoolean("workday_mon"));
				}
				if (dayOfTheWeek.contains("tue")) {
					wfd.setWorkflag(rset.getBoolean("workday_tue"));
				}
				if (dayOfTheWeek.contains("wed")) {
					wfd.setWorkflag(rset.getBoolean("workday_wed"));
				}
				if (dayOfTheWeek.contains("thu")) {
					wfd.setWorkflag(rset.getBoolean("workday_thu"));
				}
				if (dayOfTheWeek.contains("fri")) {
					wfd.setWorkflag(rset.getBoolean("workday_fri"));
				}
				if (dayOfTheWeek.contains("sat")) {
					wfd.setWorkflag(rset.getBoolean("workday_sat"));
				}
				WorkflagList.add(wfd);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rset != null)
				rset.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return WorkflagList;
	}

	//休業の部署を出社に変えるメソッド
	public void changeOpenDivision(String monthDays, String Divi[]) throws SQLException {
		Java_Util ju = new Java_Util();
		String dayOfTheWeek = ju.workDays(monthDays);

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "update department SET " + dayOfTheWeek + " = true where department_name = ?";
			stmt = conn.prepareStatement(sql);

			for (int i = 0; i < Divi.length; i++) {
				stmt.setString(1, Divi[i]);

				stmt.executeUpdate();
			}
			conn.commit();

		} catch (SQLException | ClassNotFoundException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	//出社の部署を休業に変えるメソッド
	public void changeCloseDivision(String monthDays, String Divi[]) throws SQLException {
		Java_Util ju = new Java_Util();
		String dayOfTheWeek = ju.workDays(monthDays);

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			String sql = "update department SET " + dayOfTheWeek + " = false where department_name = ?";
			stmt = conn.prepareStatement(sql);

			for (int i = 0; i < Divi.length; i++) {
				stmt.setString(1, Divi[i]);

				stmt.executeUpdate();
			}
			conn.commit();

		} catch (SQLException | ClassNotFoundException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}
}