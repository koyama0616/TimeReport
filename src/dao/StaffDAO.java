package dao;

//テーブル毎に作成

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dao.base.BaseDao;
import dto.Login;
import dto.Staff;
import dto.StaffInfo;
import util.Util;

public class StaffDAO extends BaseDao<Staff>{

	public StaffDAO(Connection con) {
		super(con);
	}



	Connection conn = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;

	//接続文字列
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = "rebellion17";

	// メールアドレス重複チェックメソッド
	// アドレスがヒットすればtrue、しなければfalse
	public boolean isDoubleMail(String mail) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			// 入力されたメールアドレスで検索
			String sql = "SELECT mail_address FROM account WHERE mail_address=?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mail);

			ResultSet rs = pStmt.executeQuery();

			// 同じメールアドレスが取得できれば
			if (rs.next()) {
				return true;
			}

			// 必ずDBを切断
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return false;
	}

	// メールで検索メソッド
	// 配列で送られてきたメールで調べヒットしたリストStaffを返す
	public List<Staff> searchMail(String[] mail) throws ClassNotFoundException, SQLException {

		Staff staff = null;
		List<Staff> staff_List = new ArrayList<Staff>();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			for (int i = 0; i < mail.length; i++) {

				String sql = "SELECT * FROM account WHERE mail_address=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, mail[i]);

				ResultSet rs = pStmt.executeQuery();

				// 一致したユーザが取得できれば
				if (rs.next()) {
					int id= rs.getInt("staff_id");
					String mail2 = rs.getString("mail_address");
					String pass = rs.getString("password");
					String name = rs.getString("name");
					String auth = rs.getString("authority");

					staff = new Staff(id,mail2, pass, name,auth);

					staff_List.add(staff);
				}
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		for (int i = 0; i < staff_List.size(); i++) {
			System.out.println("メールで検索結果 = " + staff_List.get(i).toString());
		}
		return staff_List;
	}

	// パスワード検索メソッド
	// メールで調べヒットしたパスワードを返す
	public String SearchPass(Staff staff) throws ClassNotFoundException, SQLException {

		// 取得するパスワード
		String find_pass = "";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT password FROM account WHERE mail_address=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, staff.getMail());

			ResultSet rs = pStmt.executeQuery();

			// パスワードが取得できれば
			if (rs.next()) {
				find_pass = rs.getString("password");
			}

			// 必ずDBを切断
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return find_pass;
	}

	// 新規登録メソッド
	// レコードを追加できればtrue、できなければfalse
	public boolean canCreateStaff(Staff staff) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			// メール、パス、名前でレコード追加
			String sql = "INSERT INTO staff(mail_address,password,name) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, staff.getMail());
			pStmt.setString(2, staff.getPass());
			pStmt.setString(3, staff.getName());

			int result = pStmt.executeUpdate();

			// 追加できなければ
			if (result != 1) {
				return false;
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	// ログインメソッド
	// 同じメール、パスがあるか確認し取得後、Staffインスタンスに格納し返す。
	public Staff findByLogin(Login login) throws ClassNotFoundException, SQLException {

		Staff staff = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT staff_id, mail_address, password, name ,authority FROM staff WHERE mail_address=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getMail());
			pStmt.setString(2, login.getPass());

			ResultSet rs = pStmt.executeQuery();

			// 一致したユーザが取得できれば
			if (rs.next()) {
				int id= rs.getInt("staff_id");
				String mail = rs.getString("mail_address");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				String auth = rs.getString("authority");

				staff = new Staff(id,mail, pass, name,auth);
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return staff;
	}

	// アカウント更新メソッド
	// 上書きしたStaffインスタンスを返す
	public Staff UpDate(Staff staff, String column, String update_value) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			// カラム名を変数に
			String sql = "UPDATE staff SET " + column + " = ? WHERE mail_address = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, update_value);
			pStmt.setString(2, staff.getMail());

			int result = pStmt.executeUpdate();

			// もしも更新できなければ
			if (result != 1) {
				System.out.println("更新ができませんでした。");
				return staff;

			} else {
				String sql1 = "SELECT * FROM staff WHERE mail_address=?";
				PreparedStatement pStmt1 = conn.prepareStatement(sql1);
				pStmt1.setString(1, staff.getMail());

				ResultSet rs = pStmt1.executeQuery();

				// 変更後のアカウントを取得
				// 一致したユーザが取得できれば
				if (rs.next()) {
					int id= rs.getInt("staff_id");
					String mail = rs.getString("mail_address");
					String pass = rs.getString("password");
					String name = rs.getString("name");
					String auth = rs.getString("authority");

					staff = new Staff(id,mail, pass, name,auth);
				}
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return staff;
	}

	// アカウント一覧
	public List<Staff> findAll(int count, int hope_count) throws ClassNotFoundException, SQLException {

		List<Staff> staff_List = new ArrayList<Staff>();
		Staff find_staff = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			// 登録者hope_count件分のレコードを取得
			//String sql = "UPDATE staff SET " + column + " = ? WHERE mail = ?";SELECT * FROM staff LIMIT 10 OFFSET 0;
			String sql = "SELECT * FROM staff LIMIT " + hope_count + " OFFSET " + count;
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			System.out.println("sql = " + sql);

			// 取得した結果全てをArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("staff_id");
				String mail = rs.getString("mail_address");
				String pass = rs.getString("password");
				String name = rs.getString("name");
				String auth = rs.getString("authority");

				// インスタンスに格納
				find_staff = new Staff(id,mail, pass, name,auth);

				// Listにインスタンスを順番に詰める
				staff_List.add(find_staff);
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return staff_List;
	}

	// アカウントは全部で何件あるか
	public int staffCount() throws ClassNotFoundException, SQLException {

		int count = 0;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			// 登録者全レコードを取得
			String sql = "select count(*) from staff";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			// 件数を取得
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return count;
	}

	// アカウント削除
	// 削除できれば
	public boolean canDelete(String delete_user) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			// 登録者全レコードを取得
			String sql = "DELETE FROM staff WHERE mail_address = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, delete_user);

			int rs = pStmt.executeUpdate();

			// 削除できなければ
			if (rs != 1) {
				return false;
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}

	// 部分一致検索メソッド
	// 全パターンが入った返り値配列で検索後、ヒットしたレコードをリスト型に詰め戻す
	public List<Staff> searchResults(List word) throws ClassNotFoundException, SQLException {

		List<Staff> staff_List = new ArrayList<Staff>();
		Staff find_staff = null;

		System.out.println("全パターン数 = " + word.size());

		StringBuilder sq = null;

		// DBにパターン数回接続
		// 配列をsetString用に装飾
		for (int i = 0; i < word.size(); i++) {
			Object[] arr = (Object[]) word.get(i);

			// 2週目以降のappendの上書き防止
			sq = new StringBuilder();

			for (int j = 0; j < arr.length; j++) {
				String w = arr[j].toString().replaceAll("\\[", "");
				w = w.replaceAll("\\]", "");

				sq.append("%" + w + "%");
			}

			// StringBuilderインスタンスから Stringインスタンスを生成
			String t = sq.toString();
			System.out.println("t = " + t);

			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, password);

				String sql = "SELECT * FROM staff WHERE mail_address like ? OR name like ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, t);
				pStmt.setString(2, t);

				ResultSet rs = pStmt.executeQuery();

				System.out.println(pStmt.toString());

				// 取得した結果全てをArrayListに格納
				while (rs.next()) {
					System.out.println("該当あり");
					int id = rs.getInt("staff_id");
					String mail = rs.getString("mail_address");
					String pass = rs.getString("password");
					String name = rs.getString("name");
					String auth = rs.getString("authority");

					// インスタンスに格納
					find_staff = new Staff(id,mail, pass, name,auth);

					// Listにインスタンスを順番に詰める
					staff_List.add(find_staff);

				}

			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		} // for

		for (int i = 0; i < staff_List.size(); i++) {
			System.out.println("DB接続結果 = " + staff_List.get(i).toString());
		}

		// 重複リストを削除し、新たに格納
		HashSet<Staff> checkHash = new HashSet<Staff>();
		checkHash.addAll(staff_List);

		List<Staff> staff_List2 = new ArrayList<Staff>();
		staff_List2.addAll(checkHash);

		for (Staff staff : staff_List2) {
			System.out.println("重複 = " + staff);
		}

		return staff_List2;
	}
	/**
	 * 条件検索を行う
	 * @param acc 検索条件
	 * @return 検索結果
	 */
	public List<StaffInfo> findByParam(Staff stf) throws SQLException {
		StringBuilder sql = new StringBuilder();

		// SQLの生成
		sql.append(" SELECT");
		sql.append("      s.staff_id");
		sql.append("     ,s.mail_address");
		sql.append("     ,s.password");
		sql.append("     ,s.name");
		sql.append("     ,s.department_id");
		sql.append("     ,s.authority");
		sql.append("     ,d.department_id");
		sql.append(" FROM");
		sql.append("     staff s INNER JOIN department d");
		sql.append("         ON s.department_id = d.department_id");

		String keyword = " WHERE ";
		List<Object> paramList = new ArrayList<Object>();

		if (stf.getStaff_id() != null) {
			sql.append(keyword + " s.staff_id = ?");
			paramList.add(stf.getStaff_id());
			keyword = " AND ";
		}

		if (!(Util.isStringEmpty(stf.getName()))) {
			sql.append(keyword + " s.name LIKE ?");
			paramList.add("%" + stf.getName() + "%");
			keyword = " AND ";
		}

		if (!(stf.getDepartment_id() == null)) {
			sql.append(keyword + " s.mail_address LIKE ?");
			paramList.add("%" + stf.getDepartment_id() + "%");
			keyword = " AND ";
		}

		if (stf.getAuth() != null) {
			sql.append(keyword + " s.department_id = ?");
			paramList.add(stf.getAuth());
			keyword = " AND ";
		}

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(paramList.toArray());

		ResultSet rs = stmt.executeQuery();

		// 検索結果の取得
		List<StaffInfo> stfList = new ArrayList<StaffInfo>();
		while (rs.next()) {
			StaffInfo stfInfo = new StaffInfo(rowMapping(rs));
			stfInfo.setDepartment_name(rs.getString("department_name")); //ここのカラム名をどうしようか考え中
			stfList.add(stfInfo);
		}

		// 見つからなかった
		this.colseStatement();
		return stfList;
	}

	/**
	 * 社員情報の登録を行う
	 * @param stf
	 * @return
	 */
	public int insertStaff(Staff stf) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO " + this.getTableName());
		sql.append("(");
		sql.append("     staff_id");
		sql.append("    ,mail_address");
		sql.append("    ,password");
		sql.append("    ,name");
		sql.append("    ,department_id");
		sql.append("    ,authority");
		sql.append(") VALUES ( ?, ?, ?, ?, ?, ?, )");

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(
				stf.getStaff_id()
				,stf.getMail()
				,stf.getPass()
				,stf.getName()
				,stf.getDepartment_id()
				,stf.getAuth()
		);

		int count = this.stmt.executeUpdate();

		this.colseStatement();
		return count;
	}

	@Override
	protected String getTableName() {
		return "account";
	}

	@Override
	protected Staff rowMapping(ResultSet rs) throws SQLException {
		Staff staff = new Staff();
		staff.setStaff_id(rs.getInt("staff_id"));
		staff.setMail(rs.getString("mail_address"));
		staff.setName(rs.getString("name"));
		staff.setPass(rs.getString("password"));
		staff.setDepartment_id(rs.getInt("department_id"));
		staff.setAuth(rs.getString("authority"));
		return staff;
	}

	@Override
	protected String[] getColumnsList() {
		return new String[]{
				 "staff_id"
				,"mail_address"
				,"password"
				,"name"
				,"department_id"
				,"authority"
		};
	}

	@Override
	protected String[] getPrimaryKey() {
		return new String[]{
				"staff_id"
		};
	}
}

