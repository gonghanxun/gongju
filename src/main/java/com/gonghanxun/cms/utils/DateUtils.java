package com.gonghanxun.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	static final int millSecondsPerDay =  1000*60*60*24; 
	
	
	/**
	 * 
	 */
	public static int getAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDate = calendar.get(Calendar.DATE);
		
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDate = calendar.get(Calendar.DATE);
		
		int age = currentYear - birthYear ;
		
		if(currentMonth < birthMonth) {
			age--;
		}else if(currentMonth==birthMonth && currentDate<birthDate) {
			age--;
		}
		return age;
	}
	
	public static int  getRemainDays(Date future) {
		
		return (int )((future.getTime()- new Date().getTime())/millSecondsPerDay);
		
	}
	
	public static boolean isToday(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		String formatSrc = dateFormat.format(date);
		
		String formatToday = dateFormat.format(new Date());
		
		return formatSrc.equals(formatToday);
	}
	
	public static Date getBeginOfMonth() {
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.HOUR, 0);
		instance.set(Calendar.AM_PM, Calendar.AM);
		instance.set(Calendar.DATE, 1);
		
		return instance.getTime();
	}
	
	public static Date getEndOfMonth() {
		
		Calendar instance = Calendar.getInstance();
		
		instance.setTime(new Date());
		instance.add(Calendar.MONTH, 1);

		
		instance.set(Calendar.SECOND, 0);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.HOUR, 0);
		instance.set(Calendar.AM_PM, Calendar.AM);
		instance.set(Calendar.DATE, 1);
		
		
		instance.add(Calendar.SECOND, -1);
		return instance.getTime();
		
	}
	
	public static boolean  isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day+1+1);


		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7-day+1);


		System.out.println(format.format(lastDayOfWeek.getTime()));
		
		return (date.getTime()<lastDayOfWeek.getTime().getTime() &&
				date.getTime()>firstDayOfWeek.getTime().getTime() );

	}
	
}
