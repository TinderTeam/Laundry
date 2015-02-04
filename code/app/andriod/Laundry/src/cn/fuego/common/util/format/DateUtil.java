package cn.fuego.common.util.format;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.fuego.common.log.FuegoLog;

 

public class DateUtil
{
	private static final FuegoLog log = FuegoLog.getLog(DateUtil.class);

	public static Date addYear(Date date, int year)
	{

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		Date newDate = cal.getTime();
		return newDate;

	}
	
	public static long getDateTime(Date date)
	{
		if(null != date)
		{
			return date.getTime();
		}
		return 0 ;
	}

	public static String DateToString(Date date)
	{
		String str = null;
		//DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM);
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd ");

		if (null == date)
		{
			return "";
		}
		try
		{
			str = d.format(date);
		}
		catch (Exception e)
		{
			str = "";
			log.warn("the date formart is wrong." + date);
		}
		return str;

	}

	public static String DateToLongString(Date date)
	{
		String str = null;
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		str = d.format(date);
		return str;

	}


	public static Date shortStrToDate(String date)
	{
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		Date da;
		if (null == date || date.trim().isEmpty())
		{
			return null;

		}

		try
		{
			da = d.parse(date);
		}
		catch (Exception e)
		{
			log.error("Err: Date Str is:" + date);
			throw new RuntimeException(e);

		}
		return da;

	}

	public static Date addYear(String purchaseDate, int expectYear)
	{

		return addYear(shortStrToDate(purchaseDate), expectYear);
	}

	/**
	 * get the first date of current month
	 * 
	 * @return
	 */
	public static String getCurMonthFirstDate()
	{
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		Date date = cal_1.getTime();
		return DateToString(date);
	}

	/**
	 * get the last date of current month
	 * 
	 * @return
	 */
	public static String getCurMonthLastDate()
	{
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date date = ca.getTime();

		return DateToString(date);
	}

	public static Date getCurrentDate()
	{

		return new Date(System.currentTimeMillis());
	}

	public static Timestamp getCurrentDateTime()
	{

		return new Timestamp(System.currentTimeMillis());
	}

	public static String getCurrentDateTimeStr()
	{
		Date time = new Timestamp(System.currentTimeMillis());

		return time.toString();
	}

	public static int countDayNum(Date startDate, Date endDate)
	{
		if (null == startDate || null == endDate)
		{
			return 0;
		}
		long milSec = startDate.getTime() - endDate.getTime();
		int dayNum = (int) (milSec / 1000 / 3600 / 24);
		return dayNum;

	}
	public static Date dayCalculate(Date date,int num)
	{
		Calendar convertDate=Calendar.getInstance();
		convertDate.setTime(date); 
		convertDate.add(Calendar.DATE, num);
		Date result=convertDate.getTime();
		return result;
		
	}
	public static String getStrTime(long l_stamp)
	{
		String strTime=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		strTime=sdf.format(new Date(l_stamp));
		return strTime;
		
	}

	public static CharSequence DateToShotString(Date datetime)
	{
		String strTime=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		strTime=sdf.format(datetime);
		return strTime;
	}

}
