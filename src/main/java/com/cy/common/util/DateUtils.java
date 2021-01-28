package com.cy.common.util;

import com.cy.common.constants.RegexConstants;
import com.cy.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;

/**
 * 时间工具类
 * <p>
 * Created by wfeng on 2017/7/5.
 */
public class DateUtils {

	public static final String                               YYYY_MM_DD        = "yyyy-MM-dd";
	public static final String                               YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String                               YYYYMMDD          = "yyyyMMdd";
	public static final String                               YYYYMMDDHHMMSS    = "yyyyMMdd HH:mm:ss";
	private static      Logger                               logger            = LoggerFactory.getLogger(DateUtils.class);
	private static      Map<String, ThreadLocal<DateFormat>> dfMap             = new HashMap<>();

	private DateUtils() {

	}

	/**
	 * @param date   传入的时间
	 * @param format 返回字符串格式
	 * @return
	 * @Title: date2Str
	 * @Description:将时间类型转换为字符串
	 */
	public static String date2Str(Date date, String format) {
		if (null == date || StringUtils.isEmpty(format)) {
			return RegexConstants.EMPTY;
		}
		SimpleDateFormat sdf = initDateFormat(format);

		return sdf.format(date);
	}

	/**
	 * 初始化日期格式化对象
	 *
	 * @param format
	 * @return
	 */
	private static SimpleDateFormat initDateFormat(final String format) {
		ThreadLocal<DateFormat> tl = dfMap.get(format);
		if (tl == null) {
			tl = new ThreadLocal<DateFormat>() {

				@Override
				protected DateFormat initialValue() {
					return new SimpleDateFormat(format);
				}

			};
		}
		return (SimpleDateFormat) tl.get();
	}

	/**
	 * @param dateStr 传入的字符串
	 * @param format  对应的格式
	 * @return
	 * @Title: str2Date
	 * @Description:将字符串改为时间
	 */
	public static Date str2Date(String dateStr, String format) {
		SimpleDateFormat sdf = initDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("dateStr:{} convert to format:{} exception", dateStr, format, e);
			return null;
		}
	}

	/**
	 * @return
	 * @Title: getNow
	 * @Description:获取当前时间
	 */
	public static String getNow() {
		return date2Str(new Date(), YYYY_MM_DD);
	}

	public static Date getDay() {
		return str2Date(getNow(), YYYY_MM_DD);
	}

	public static String getNow(String format) {
		return date2Str(new Date(), format);
	}

	/**
	 * @param addDay 新增的天数
	 * @return
	 * @Title: addNow
	 * @Description:获取今天新增天数之后的日期
	 */
	public static Date addNow(Integer addDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @param addDay 新增的天数
	 * @return
	 * @Title: addNow
	 * @Description:获取date新增天数之后的日期
	 */
	public static String addDay(String date, Integer addDay, String format) {
		if (StringUtils.isEmpty(format)) {
			format = YYYY_MM_DD;
		}
		try {
			SimpleDateFormat dateFormat = initDateFormat(format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			calendar.add(Calendar.DAY_OF_MONTH, addDay);
			return date2Str(calendar.getTime(), format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String addDay(String date, Integer addDay) {
		return addDay(date, addDay, YYYY_MM_DD_HHMMSS);
	}

	/**
	 * @param date
	 * @param addDay 新增的天数
	 * @return
	 * @Title: addNow
	 * @Description:获取date新增天数之后的日期
	 */
	public static Date addDay(Date date, Integer addDay, String format) {
		if (StringUtils.isEmpty(format)) {
			format = YYYY_MM_DD;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		return str2Date(date2Str(calendar.getTime(), format), format);
	}

	public static Date addDay(Date date, Integer addDay) {
		return addDay(date, addDay, YYYY_MM_DD_HHMMSS);
	}

	public static Date addMinute(Date date, int addMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, addMinute);
		return calendar.getTime();
	}

	public static Date addHour(Date date, int addHour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, addHour);
		return calendar.getTime();
	}

	/**
	 * 按照所给格式进行日期转换
	 *
	 * @param format
	 * @return
	 */
	public static String getDate(String format) {
		Date date = new Date();
		SimpleDateFormat sf = initDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 按照所给格式进行日期转换
	 *
	 * @param format
	 * @return
	 */
	public static String getDate(String date, String format) {
		SimpleDateFormat sf = initDateFormat(format);
		return sf.format(str2Date(date, format));
	}

	/**
	 * 按照所给格式进行日期转换
	 *
	 * @param format
	 * @return
	 */
	public static String getDate(Date date, String format) {
		SimpleDateFormat sf = initDateFormat(format);
		return sf.format(date);
	}

	/**
	 * @return
	 * @Title: getCurrTime
	 * @Description:获取时分秒信息
	 */
	public static String getCurrTime() {
		return getDate("HHmmss");
	}

	/**
	 * @param oldDate
	 * @param addMonth
	 * @return
	 * @Title: addMonth
	 * @Description:获取时间增加月份后的日期
	 */
	public static Date addMonth(Date oldDate, Integer addMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.MONTH, addMonth);
		return calendar.getTime();
	}

	/**
	 * @return
	 * @Title: getLastNow
	 * @Description:获取今天的截止时间 即:yyyy-MM-dd 23:59:59
	 */
	public static Date getLastNow() {
		String nowStr = getNow();
		String newNowStr = nowStr + " 23:59:59";
		return str2Date(newNowStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 比较两个日期的时间间隔天数
	 *
	 * @param date1 开始日期
	 * @param date2 截止日期
	 * @return 间隔天数
	 * @author wangwen
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) {// 同一年
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {// 闰年
					timeDistance += 366;
				} else { // 不是闰年
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else {// 不同年
			return day2 - day1;
		}
	}

	/**
	 * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间） ISO8601格林威治天文台的标准时间
	 *
	 * @param date
	 * @return 2017-04-29​T​10:02:04​Z
	 */
	public static String getISO8601Timestamp(Date date) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(tz);
		return df.format(date);
	}

	/**
	 * 获取当天0点时间(毫秒)
	 *
	 * @return
	 */
	public static long getTimesmorningByLong() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取当天0点时间
	 *
	 * @return
	 */
	public static Date getTimesmorningByDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定日期中的日
	 *
	 * @param date
	 * @return 日
	 * @author wangwen
	 */
	public static int getDayOfDate(Date date) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		return aCalendar.get(Calendar.DATE);
	}

	/**
	 * 比较日期的大小
	 *
	 * @param dateStr1 比较时间（yyyy-MM-dd格式）
	 * @param dataStr2 被比较时间（yyyy-MM-dd格式）
	 * @return 1：dateStr1大于dateStr2
	 * -1：dateStr1小于dateStr2
	 * 0：dateStr1等于dateStr2
	 */
	public static int compareDay(String dateStr1, String dataStr2) {
		long time1 = str2Date(dateStr1, YYYY_MM_DD).getTime();
		long time2 = str2Date(dataStr2, YYYY_MM_DD).getTime();
		if (time1 > time2) {
			return 1;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 0;
		}

	}

	public static int compareTime(String dateStr1, String dataStr2) {
		long time1 = str2Date(dateStr1, YYYY_MM_DD_HHMMSS).getTime();
		long time2 = str2Date(dataStr2, YYYY_MM_DD_HHMMSS).getTime();
		if (time1 > time2) {
			return 1;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 0;
		}

	}

	public static int compareDay(Date date1, Date data2) {
		long time1 = date1.getTime();
		long time2 = data2.getTime();
		if (time1 > time2) {
			return 1;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 检查格式是否正确
	 *
	 * @param dateStr
	 * @param pattern
	 * @return true：正确，false：错误
	 */
	public static boolean formatCorrect(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 在00:00:00~22:00:00之间
	 *
	 * @return
	 */
	public static boolean nowBetween() {
		String day = getNow();
		Date start = str2Date(day + " 00:00:00", YYYY_MM_DD_HHMMSS);
		Date end = str2Date(day + " 22:00:00", YYYY_MM_DD_HHMMSS);
		Date now = new Date();
		if (now.getTime() >= start.getTime() && now.getTime() <= end.getTime()) {
			return true;
		}

		return false;
	}

	public static Date getTodayStart() {
		String day = getNow();
		return str2Date(day + " 00:00:00", YYYY_MM_DD_HHMMSS);
	}

	public static Date getTodayEnd() {
		String day = getNow();
		return str2Date(day + " 23:59:59", YYYY_MM_DD_HHMMSS);
	}

	public static boolean checkFormat(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static Date getTimeStart(Date day) {
		String dayStr = date2Str(day, YYYY_MM_DD);
		return str2Date(dayStr + " 00:00:00", YYYY_MM_DD_HHMMSS);
	}

	public static Date getTimeStart(String dayStr) {
		if (checkFormat(dayStr, YYYY_MM_DD)) {
			throw new DateTimeException("Error in time format");
		}
		return getTimeStart(str2Date(dayStr, YYYY_MM_DD));
	}

	public static Date getTimeEnd(Date day) {
		String dayStr = date2Str(day, YYYY_MM_DD);
		return str2Date(dayStr + " 23:59:59", YYYY_MM_DD_HHMMSS);
	}

	public static Date getTimeEnd(String dayStr) {
		if (checkFormat(dayStr, YYYY_MM_DD)) {
			throw new ServiceException("Error in time format");
		}
		return getTimeEnd(str2Date(dayStr, YYYY_MM_DD));
	}

	public static void setCriteriaBetween(Example.Criteria criteria, String property, Object dataObject) {
		if (dataObject instanceof String) {
			String dataStr = (String) dataObject;
			criteria.andBetween(property, getTimeStart(dataStr), getTimeEnd((dataStr)));
		} else if (dataObject instanceof Date) {
			Date dataStr = (Date) dataObject;
			criteria.andBetween(property, getTimeStart(dataStr), getTimeEnd((dataStr)));
		} else {
			throw new ServiceException("Error in data type");
		}
	}
}
