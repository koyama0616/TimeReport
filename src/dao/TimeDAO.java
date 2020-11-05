package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.Timeline;

public class TimeDAO {

	Connection conn = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;

	//接続文字列
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = "rebellion17";

	//出社メソッド
	public boolean insertAttendanceTime(Integer id, Date yearMonthDays, Timestamp time) {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			//yyyy-MM-dd HH:mm:ss-SS

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO timeline");
			stringBuilder.append("(staff_id,datetime,coming_time)");
			stringBuilder.append("values(?,?,?)");
			String sql = new String(stringBuilder);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setDate(2, yearMonthDays);
			pStmt.setTimestamp(3, time);

			int result = pStmt.executeUpdate();

			// もし一件もINSERTできなければ
			if (result != 1) {
				System.out.println("INSERTできませんでした。エラーです");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return true; // 出勤OK

	}

	//退社メソッド
	public boolean insertLeavingTime(Integer id, Date yearMonthDays, Timestamp time) {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			//yyyy-MM-dd HH:mm:ss-SS

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE timeline set leaving_time = ? ");
			stringBuilder.append("where staff_id = ? and datetime = ? ");
			String sql = new String(stringBuilder);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setTimestamp(1, time);
			pStmt.setInt(2, id);
			pStmt.setDate(3, yearMonthDays);

			int result = pStmt.executeUpdate();
			System.out.println(result);
			// もし一件もupdateできなければ
			if (result != 1) {
				System.out.println("UPDATEできませんでした。エラーです");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return true; // 退社OK

	}

	public void GetAttendance(Integer id, Date firstDay, Date lastDay) {

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT * FROM timeline where staff_id = ? ");
			stringBuilder.append("and datetime between ? and ?");
			String sql = new String(stringBuilder);

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setDate(2, firstDay);
			pStmt.setDate(3, lastDay);

			rs = pStmt.executeQuery();

			List<Timeline> timeLineList = new ArrayList<>();

			while (rs.next()) {
								rs.getDate("datetime");
								rs.getTimestamp("coming_time");
								rs.getTimestamp("leaving_time");
				System.out.println(rs.getDate("datetime"));
				System.out.println(rs.getTimestamp("coming_time"));
				System.out.println(rs.getTimestamp("leaving_time"));
								rs.getTimestamp("break_time");
								rs.getTimestamp("over_time");
								rs.getTimestamp("actual_time");
								rs.getString("location");
								rs.getString("content");
								rs.getString("memo");

								Timeline timeLine = new Timeline(id, lastDay, null, null, null, null, null, sql, sql, sql);

			}



		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//		select * from timeline where staff_id = 1 and datetime between '2020/11/1' and '2020/11/30';

		}
	}
}

//
//	// 出社メソッド
//	// trueで出社できる、falseで出社できない
//	public boolean canAdmission(Time time) throws ClassNotFoundException,SQLException {
//
//
//
//		try {
//			Class.forName("org.postgresql.Driver");
//			conn = DriverManager.getConnection(url,user,password);
//
//			String sql = "SELECT admission FROM time WHERE mail=? AND day=?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, time.getMail());
//			pStmt.setString(2, time.getDay());
//
//			ResultSet rs = pStmt.executeQuery();
//
//			if (rs.next()) {
//				System.out.println("出社が被っています。エラーです");
//		  		return false; // 出勤が被っています。OUT
//			}
//
//			else {
//				String sql1 = "INSERT INTO time(mail,day,admission) VALUES(?,?,?)";
//				PreparedStatement pStmt1 = conn.prepareStatement(sql1);
//				pStmt1.setString(1, time.getMail());
//				pStmt1.setString(2, time.getDay());
//				pStmt1.setString(3, time.getAdmission());
//
//				int result = pStmt1.executeUpdate();
//
//				// もし一件もINSERTできなければ
//				if (result != 1) {
//					System.out.println("INSERTできませんでした。エラーです");
//					return false;
//				}
//			}
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//		return true; // 出勤OK
//	}
//
//
//
//	// 退社メソッド
//	// 実行状態によりメッセージを返す
//	public MsgLeaving runLeaving(Time time) throws ClassNotFoundException,SQLException {
//
//
//		// レコードから取得した値を入れる
//		String leaving = "";
//		// 件数のカウント
//		int count = 0;
//
//
//
//
//		try {
//			Class.forName("org.postgresql.Driver");
//			conn = DriverManager.getConnection(url,user,password);
//
//			// 2重投稿にならないよう、すでに退社済みか確認
//			String sql = "SELECT leaving FROM time WHERE mail=? AND day=?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, time.getMail());
//			pStmt.setString(2, time.getDay());
//
//			ResultSet rs = pStmt.executeQuery();
//
//			while (rs.next()) { // ヒットした件数分回す
//				leaving = rs.getString("leaving");
//				count++;
//				System.out.println("今日の退社レコードを確認しました。leaving ("+leaving+")、count ("+count+")");
//			}
//
//			// ヒットしていない場合、ここからの処理になる
//
//			// 退社されていません(出社でINSERTされていません)
//			if (count == 0) {
//				// 日付をまたいだ退社かどうか確認
//				// 型変換用に昨日の日時を入れるための変数を2つ用意
//				Date yesterday_Dat = null;
//				String yesterday_Str = "";
//
//				// 日付のフォーマット
//				SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd");
//
//				// 昨日の日付を用意
//				try {
//					// Calendar型を使うのでtime.getDay()をString型からDate型に変換し、1日引いて昨日にする
//					Date date_time_getDay = tdf.parse(time.getDay());
//
//					Calendar yesterday_Cal = Calendar.getInstance();
//					yesterday_Cal.setTime(date_time_getDay);
//					yesterday_Cal.add(Calendar.DAY_OF_MONTH, -1); // 1日引く
//
//					yesterday_Dat = yesterday_Cal.getTime();
//
//				// テキストを解析して日付を生成する際の予想外のエラー(例外処理)
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//
//				// setStringできるようにDate型からString型に戻す
//				yesterday_Str = tdf.format(yesterday_Dat);
//
//				// 昨日の退社が確認できるか検索
//				String sql2 = "SELECT leaving FROM time WHERE mail=? AND day=?";
//				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
//				pStmt2.setString(1, time.getMail());
//				pStmt2.setString(2, yesterday_Str);
//
//				ResultSet rs2 = pStmt2.executeQuery();
//
//				// ヒットしなければ
//				if (!rs2.next()) {
//					System.out.println("出社されていません(昨日の退社も確認できませんでした。今日の出社がされていないオチと判断します)");
//					return MsgLeaving.NOT_ADMISSION; // 今日の出社がされていないオチ
//				}
//				else {
//					String leaving2 = rs2.getString("leaving");
//					System.out.println("昨日の退社レコードを確認しました");
//
//					// 取得したleaving2に退社時刻が入っていれば
//					if (leaving2 != null) {
//						System.out.println("出社されていません(昨日の退社は確認できましたが、日付が入っています。退社済みです。今日の出社がされていないオチと判断します)");
//						return MsgLeaving.NOT_ADMISSION; // 今日の出社がされていないオチ
//					}
//
//					// それ以外なら昨日の日付を更新
//					System.out.println("今日の退社も確認できず、かつ昨日の退社はnullでした。日付をまたいだ退社と判断します");
//
//					String sql3 = "UPDATE time SET leaving=? WHERE mail=? AND day=?";
//					PreparedStatement pStmt3 = conn.prepareStatement(sql3);
//					pStmt3.setString(1, time.getLeaving());
//					pStmt3.setString(2, time.getMail());
//					pStmt3.setString(3, yesterday_Str); // 昨日の日付
//
//					int result = pStmt3.executeUpdate();
//
//					// もしも更新できなければ
//					if (result != 1) {
//						System.out.println("更新ができませんでした。");
//						return MsgLeaving.SYSTEM_ERROR;
//					}
//
//					return MsgLeaving.OK;
//				}
//			}
//
//			// 2件以上はDBの内部的エラーです。
//			if (count >= 2) {
//				System.out.println("DBエラーです。leaving ("+leaving+")、count ("+count+")");
//				return MsgLeaving.SYSTEM_ERROR;
//			}
//			// 退社済みです。
//			if (count == 1 && leaving != null) {
//				System.out.println("退社済みです。leaving ("+leaving+")、count ("+count+")");
//				return MsgLeaving.ALREADY;
//			}
//
//			else {
//				// 退社準備OK。更新します。
//				System.out.println("退社OK");
//
//				String sql2 = "UPDATE time SET leaving=? WHERE mail=? AND day=?";
//				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
//				pStmt2.setString(1, time.getLeaving());
//				pStmt2.setString(2, time.getMail());
//				pStmt2.setString(3, time.getDay());
//
//				int result = pStmt2.executeUpdate();
//
//				// もしも更新できなければ
//				if (result != 1) {
//					System.out.println("更新ができませんでした。");
//					return MsgLeaving.SYSTEM_ERROR;
//				}
//			}
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}
//		return MsgLeaving.OK; // 退社OK
//	}
