package dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Timeline implements Serializable {

	private int staff_id;        // スタッフID
	private Date datetime;         // 日付
	private Timestamp coming_time;   // 出社日時
	private Timestamp leaving_time; //退社日時
	private Timestamp break_time; //休憩時間
	private Timestamp over_time; //残業時間
	private Timestamp actual_time; //実働時間
	private String location; //作業場所
	private String content; //作業内容
	private String memo; // 作業メモ

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Timestamp getComing_time() {
		return coming_time;
	}

	public void setComing_time(Timestamp coming_time) {
		this.coming_time = coming_time;
	}

	public Timestamp getLeaving_time() {
		return leaving_time;
	}

	public void setLeaving_time(Timestamp leaving_time) {
		this.leaving_time = leaving_time;
	}

	public Timestamp getBreak_time() {
		return break_time;
	}

	public void setBreak_time(Timestamp break_time) {
		this.break_time = break_time;
	}

	public Timestamp getOver_time() {
		return over_time;
	}

	public void setOver_time(Timestamp over_time) {
		this.over_time = over_time;
	}

	public Timestamp getActual_time() {
		return actual_time;
	}

	public void setActual_time(Timestamp actual_time) {
		this.actual_time = actual_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timeline (int staff_id,Date datetime,Timestamp coming_time, Timestamp leaving_time, Timestamp break_time , Timestamp over_time, Timestamp actual_time, String location, String content, String memo) {
		this.staff_id = staff_id;
		this.datetime = datetime;
		this.coming_time = coming_time;
		this.leaving_time = leaving_time;
		this.break_time = break_time;
		this.over_time = over_time;
		this.actual_time = actual_time;
		this.location = location;
		this.content = content;
		this.memo = memo;
	}
}
//	private int staff_id;        // スタッフID
//	private Date datetime;         // 日付
//	private Timestamp coming_time;   // 出社日時
//	private Timestamp leaving_time; //退社日時
//	private Timestamp break_time; //休憩時間
//	private Timestamp over_time; //残業時間
//	private Timestamp actual_time; //実働時間
//	private String location; //作業場所
//	private String content; //作業内容
//	private String memo; // 作業メモ
