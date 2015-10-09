package br.com.fitrank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConversor {
	
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(FORMATO_DATA);
	private static GregorianCalendar gc;
	
	public static String DateToString(Date data) {
		return FORMATTER.format(data);
	}
	
	public static Date StringToDate(String data){
		try {
			return FORMATTER.parse(data);
		} catch (ParseException e) {
			System.out.println("DateConveror.StringToDate(String data) recebendo String inválida ou nula.");;
			return null;
		}
	}
			
	public static Date getPreviousYear(){
		gc = new GregorianCalendar();
		gc.add(Calendar.YEAR, -1);
		return gc.getTime();
	}
	
	public static Date getPreviousMonth(){
		gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		return gc.getTime();
	}
	
	public static Date getPreviousWeek(){
		gc = new GregorianCalendar();
		gc.add(Calendar.WEEK_OF_YEAR, -1);
		return gc.getTime();
	}
	
	public static Date getPreviousDay(){
		gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_YEAR, -1);
		return gc.getTime();
	}
	
	public static String getPreviousYearString(){
		gc = new GregorianCalendar();
		gc.add(Calendar.YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	
	public static String getPreviousMonthString(){
		gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static String getPreviousWeekString(){
		gc = new GregorianCalendar();
		gc.add(Calendar.WEEK_OF_YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static String getPreviousDayString(){
		gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static GregorianCalendar convertDateToGregorian(Date date) {
		gc = new GregorianCalendar();
		gc.setTime(date);
		return gc;
	}
	
	public static int getHourFromDate(Date date) {
		gc = new GregorianCalendar();
		gc.setTime(date);
		return gc.get(Calendar.HOUR_OF_DAY);
	}
	
	
}