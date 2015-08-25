/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		DateUtil.java
 * Class:			DateUtil
 * Date:			Jan 8, 2007 11:00:32 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 8, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 8, 2007 11:00:32 PM
 * @version $Id DateUtil.java$
 */
public class DateUtil {
	private DateUtil(){};
    private static String defaultDatePattern = "yyyy-MM-dd";
    
    /**
     * get default date pattern
     */
    public static String getDatePattern() {
        return defaultDatePattern;
    }
    
    /**
     * use the default date pattern to format date
     * @param date
     * @return
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
    public static String format(Date date) {
        return date == null ? "" : format(date, getDatePattern());
    }
    
    /**
     * use the default date pattern to format date
     * @param date
     * @param pattern
     * @return
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
    public static String format(Date date, String pattern) {
        return date == null ? "" : new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * parse the string to date, use the default parrern.
     * @param strDate
     * @return
     * @throws ParseException
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
    public static Date parse(String strDate) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
    }

    /**
     * parse the string to date, use the given pattern.
     * @param strDate
     * @param pattern
     * @return
     * @throws ParseException
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
    }
    
    /**
     * get the date's month start time.
     * @param date
     * @return
     * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
     */
	public static Date getMonthStartTime(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 * get the date's month end time.
	 * @param date
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public static Date getMonthEndTime(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 * get the begin of the day.
	 * @param date
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public static Date getBeginOfDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	
	/**
	 * get the end of the day.
	 * @param date
	 * @return
	 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
	 */
	public static Date getEndOfDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}
}
