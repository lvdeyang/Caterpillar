package com.sumavision.tetris.commons.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {
	public static final String yearmonthPattern = "yyyy-MM";
	public static final String defaultDatePattern = "yyyy-MM-dd";
	public static final String datePatternZh_cn = "yyyy年MM月dd日";
	public static final String defaultTimePattern = "HH:mm:ss";
	public static final String currentDateTimePattern = "yyyyMMddHHmmss";
	public static final String currentDatePattern = "yyyyMMdd";
	public static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public static final String dateTimePattenWithoutSecind = "yyyy-MM-dd HH:mm";
	public static final String timePattenWithoutSecind = "HH:mm";
	public static final String shortDatePatten = "yy/MM/dd";
	public static final String shortYearmonthPattern = "yy/MM";
	public static final String shortDateTimePatten = "yy/MM/dd HH:mm:ss";
	public static final String dateTimeUTCPatten = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	static {
		TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(tz);
	}

	public static String now(){
		return format(new Date(), dateTimePattern);
	}
	
	public static String getDateTimePattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}

	public static String getDatePattern() {
		return "yyyy-MM-dd";
	}

	public static String getCurYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(1));
	}

	public static String getTimePattern() {
		return "HH:mm:ss";
	}

	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	public static String getYearmonth(Date date) {
		return format(date, "yyyy-MM");
	}

	public static String getYearmonthDay(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static Date getDate() {
		Date today = new Date();
		return today;
	}

	public static long getLongDate() {
		Date today = new Date();
		return today.getTime();
	}

	public static String format(Date date) {
		return format(date, getDateTimePattern());
	}

	public static String format(Date date, String pattern) {
		String returnValue = "";

		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}

		return returnValue;
	}

	public static Date parse(String strDate) throws ParseException {
		if ((strDate != null) && (strDate.length() > 13)) {
			return parse(strDate, getDatePattern() + " " + getTimePattern());
		}
		if (strDate != null) {
			return parse(strDate, getDatePattern());
		}

		return null;
	}

	public static Date parse(String strDate, String pattern) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(2, n);
		return cal.getTime();
	}

	public static Date addMonth(int n) {
		return addMonth(getDate(), n);
	}

	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, n);
		return cal.getTime();
	}

	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(10, n);
		return cal.getTime();
	}

	public static Date addMinute(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(12, n);
		return c.getTime();
	}

	public static Date addSecond(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(13, n);
		return c.getTime();
	}

	public static Date addMilliSecond(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, n);
		return c.getTime();
	}

	public static Date addDay(int n) {
		return addDay(getDate(), n);
	}

	public static boolean compare(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return !cal1.before(cal2);
	}

	public static Date[] getWeekStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];

		int dayOfWeek = calendar.get(7);

		calendar.add(7, -(dayOfWeek - 1));

		Date firstDateOfWeek = calendar.getTime();
		calendar.add(7, 6);
		Date lastDateOfWeek = calendar.getTime();
		dates[0] = firstDateOfWeek;
		dates[1] = lastDateOfWeek;
		return dates;
	}

	public static Date[] getMonthStartAndEndDate(Calendar calendar) {
		Date[] dates = new Date[2];

		int dayOfMonth = calendar.get(5);
		calendar.add(5, -(dayOfMonth - 1));

		Date firstDateOfMonth = calendar.getTime();
		calendar.add(5, calendar.getActualMaximum(5) - 1);
		Date lastDateOfMonth = calendar.getTime();
		dates[0] = firstDateOfMonth;
		dates[1] = lastDateOfMonth;
		return dates;
	}

	public static Calendar getCurrentDate() {
		Calendar now = Calendar.getInstance();
		return now;
	}

	public static Calendar getCalendar(String strDate) {
		Calendar c1 = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse(strDate);
			c1 = Calendar.getInstance();
			c1.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c1;
	}

	public static int getMonthCount(String strDate) {
		int day = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = new GregorianCalendar();
			Date date = sdf.parse(strDate);
			calendar.setTime(date);
			day = calendar.getActualMaximum(5);
		} catch (Exception localException) {
		}
		return day;
	}

	public static int getHours(String d1, String d2) {
		return (int) Math.abs(getDoubleHours(d1, d2));
	}

	public static double getDoubleHours(String d1, String d2) {
		double hour = 0.0D;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = df.parse(d1);
			Date date = df.parse(d2);
			long l = now.getTime() - date.getTime();
			long day = l / 86400000L;
			hour = l / 3600000.0D - day * 24L;
			day = Math.abs(day);
			if (day != 0L) {
				hour = Math.abs(hour);
				hour += 24L * day;
			}
		} catch (Exception localException) {
		}
		return Math.abs(hour);
	}

	public static int getHours(Date d1, Date d2) {
		return (int) Math.abs(getDoubleHours(d1, d2));
	}

	public static double getDoubleHours(Date d1, Date d2) {
		double hour = 0.0D;
		try {
			long l = d1.getTime() - d2.getTime();
			long day = l / 86400000L;
			hour = l / 3600000.0D - day * 24L;
			day = Math.abs(day);
			if (day != 0L) {
				hour = Math.abs(hour);
				hour += 24L * day;
			}
		} catch (Exception localException) {
		}
		return Math.abs(hour);
	}

	public static double getDoubleSeconds(Date d1, Date d2) {
		double second = 0.0D;
		try {
			long l = d1.getTime() - d2.getTime();
			second = l / 1000;
		} catch (Exception localException) {
		}
		return Math.abs(second);
	}

	public static String getCurrentDateTime() {
		Date date = new Date();
		return format(date, "yyyyMMddHHmmss");
	}

	public static String getCurrentDateTime(String patten) {
		Date date = new Date();
		return format(date, patten);
	}

	public static Date getBeginDate(String strBeginDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(strBeginDate + " 00:00:00");
	}

	public static Date getEndDate(String strEndDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(strEndDate + " 23:59:59");
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(7) - 1;
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(5);
	}

	public static String getTimeString(Date date) {
		String timePattren = "yyyyMMddHHmmssSSS";
		return dateToString(date, timePattren);
	}

	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat fo = new SimpleDateFormat(pattern);
		return fo.format(date);
	}

	public static int getTimeByHHmmss(String hhmmss) {
		if (hhmmss == null || hhmmss.trim().length() == 0) {
			return 0;
		}
		String[] arr = hhmmss.split(":");
		int num = 0;
		if (arr.length == 3) {
			num = (int) ((Double.valueOf(arr[0]).doubleValue() * 3600.0D + Double.valueOf(arr[1]).doubleValue() * 60.0D
					+ Double.valueOf(arr[2]).doubleValue()) * 1000.0D);
			return num;
		}

		return 0;
	}

	public static boolean check_yyyy_MM_dd_Valid(String dateStr) {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		return dateStr.matches(regex);
	}

	public static boolean check_yyyyMMdd_HHmmss_valid(String dateStr) {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(\\s([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9])";
		return dateStr.matches(regex);
	}

	public static boolean check_HH_mm_ss_valid(String dateStr) {
		String regex = "([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]";
		return dateStr.matches(regex);
	}

	public static String addDateStr(String dateStr, int n) throws ParseException {
		Date date = parse(dateStr, "yyyy-MM-dd");
		date = addDay(date, n);
		dateStr = format(date, "yyyy-MM-dd");
		return dateStr;
	}

	public static String getNowDate() {
		SimpleDateFormat ft = null;
		Date date = null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		date = cl.getTime();
		ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = ft.format(date);
		return dateTime;
	}

	public static String dateAddday(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		int d = c.get(5);
		d += i;
		c.set(5, d);
		String str = df.format(c.getTime());
		return str;
	}

	public static Date getDateByMillisecond(long millisecond) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millisecond);
		return c.getTime();
	}

	public static String addSecondStr(String dateStr, int n) throws ParseException {
		Date date = parse(dateStr, "yyyy-MM-dd HH:mm:ss");
		date = addSecond(date, n);
		dateStr = format(date, "yyyy-MM-dd HH:mm:ss");
		return dateStr;
	}

	public static String dateAddMnoth(int i) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		int d = c.get(2);
		d += i;
		c.set(2, d);
		String str = df.format(c.getTime());
		return str;
	}

	public static boolean compareStr(String str1, String str2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date a = df.parse(str1);
		Date b = df.parse(str2);
		return compare(a, b);
	}

	public static Integer SumStrtoInteger(String str) {
		Integer num = Integer.valueOf(0);
		String[] astr = str.split(":");
		if (Integer.valueOf(astr.length).toString().equals("3")) {
			num = Integer.valueOf(Integer.valueOf(astr[0]).intValue() * 3600 + Integer.valueOf(astr[1]).intValue() * 60
					+ Integer.valueOf(astr[2]).intValue());
		}
		return num;
	}
	
	/**
	 * 根据年 月 获取对应的月份 天数
	 * */
	public static int getDaysByYearMonth(int year, int month) {
		
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	public static List<Date> getDates(String startDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> list = new ArrayList<Date>(); //保存日期的集合 
		Date date_start = sdf.parse(startDate);
		Date date_end = sdf.parse(endDate);
		Date date =date_start;
		Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
		while (date.getTime() <= date_end.getTime()){
			list.add(date);
			cd.setTime(date);
			cd.add(Calendar.DATE, 1);//增加一天 放入集合
			date=cd.getTime();
		}
		return list;
	}

	
}