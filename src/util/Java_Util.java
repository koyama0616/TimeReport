package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Java_Util {

	//年を分けるメソッド
	public int buildYear(String nen) {
		StringBuilder buildYear = new StringBuilder();
		String[] monthArray = nen.split("");
		for (int i = 0; i < 4; i++) {
			buildYear.append(monthArray[i]);
		}
		nen = new String(buildYear);
		int year = Integer.parseInt(nen);
		return year;
	}

	//月を分けるメソッド
	public int buildMonth(String tuki) {
		StringBuilder buildMonth = new StringBuilder();
		String[] monthArray = tuki.split("");
		for (int i = 5; i < 7; i++) {
			buildMonth.append(monthArray[i]);
		}
		tuki = new String(buildMonth);
		int month = Integer.parseInt(tuki);
		return month;
	}

	//月の最終日取得メソッド
	public int getMaximumDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int result = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return result;

	}

	//月の初日から最終日までを日付をつけてコレクションに収納するメソッド
	public List<String> monthDays(int year, int month, int maxDay) {
		List<String> monthDayList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (int i = 1; i <= maxDay; i++) {

			//桁をそろえるために空白を追加する
			String space = "/";
			if (i < 10) {
				space = "/ ";
			}
			cal.set(year, month-1, i);
			switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case Calendar.SUNDAY:
				monthDayList.add(month + space + i + " 日");
				break;
			case Calendar.MONDAY:
				monthDayList.add(month + space + i + " 月");
				break;
			case Calendar.TUESDAY:
				monthDayList.add(month + space + i + " 火");
				break;
			case Calendar.WEDNESDAY:
				monthDayList.add(month + space + i + " 水");
				break;
			case Calendar.THURSDAY:
				monthDayList.add(month + space + i + " 木");
				break;
			case Calendar.FRIDAY:
				monthDayList.add(month + space + i + " 金");
				break;
			case Calendar.SATURDAY:
				monthDayList.add(month + space + i + " 土");
				break;
			}
		}
		return monthDayList;
	}

	//取得した日付の曜日を返すメソッド

	public String workDays(String day) {
		String dayOfTheWeek = null;

		if (day.contains("日")) {
			dayOfTheWeek = "workday_sun";
		} else if (day.contains("月")) {
			dayOfTheWeek = "workday_mon";
		} else if (day.contains("火")) {
			dayOfTheWeek = "workday_tue";
		} else if (day.contains("水")) {
			dayOfTheWeek = "workday_wed";
		} else if (day.contains("木")) {
			dayOfTheWeek = "workday_thu";
		} else if (day.contains("金")) {
			dayOfTheWeek = "workday_fri";
		} else if (day.contains("土")) {
			dayOfTheWeek = "workday_sat";
		}
		return dayOfTheWeek;
	}

	//取得した日付の曜日を返すメソッド
	public boolean BooleanWorkDays(String day) {

		if (day.contains("日")) {
			boolean workday_sun = true;
			return workday_sun;
		} else if (day.contains("月")) {
			boolean workday_mon = true;
			return workday_mon;
		} else if (day.contains("火")) {
			boolean workday_tue = true;
			return workday_tue;
		} else if (day.contains("水")) {
			boolean workday_wed = true;
			return workday_wed;
		} else if (day.contains("木")) {
			boolean workday_thu = true;
			return workday_thu;
		} else if (day.contains("金")) {
			boolean workday_fri = true;
			return workday_fri;
		} else if (day.contains("土")) {
			boolean workday_sat = true;
			return workday_sat;
		} else {
			return false;
		}
	}

	//現在時刻をタイムスタンプ型に変換するメソッド
	public Timestamp changeSqlTime() {

		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm");
		String time = simpleDateFormat.format(timeStamp);

		java.util.Date parsedDate = null;

		try {
			parsedDate = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Timestamp sqlTime = new java.sql.Timestamp(parsedDate.getTime());


		return sqlTime;

	}

	//現在の日時をデート型に変換するメソッド
	public Date changeSqlDate() {
		LocalDateTime nowTime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = format.format(nowTime);
		Date sqlDate = Date.valueOf(date);

		return sqlDate;

	}

	public Date getFirstDay(String month) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(month);
		stringBuilder.append("-1");
		String date = new String(stringBuilder);
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String strTime = format.format(date);
		Date firstDay = Date.valueOf(date);
		return firstDay;

	}

	public Date getLastDay(String month , int maximumDay) {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(month);
		stringBuilder.append("-" + maximumDay);
		String date = new String(stringBuilder);
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String strTime = format.format(date);
		Date lastDay = Date.valueOf(date);
		return lastDay;

	}

}
