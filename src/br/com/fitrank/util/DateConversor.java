package br.com.fitrank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateConversor {
	
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(FORMATO_DATA);
	private static GregorianCalendar gc;
	
	public static String DateToString(Date data) {
		try {
			return FORMATTER.format(data);
		} catch(NullPointerException e) {
			return null;
		}
	}
	
	public static Date StringToDate(String data){
		try {
			return FORMATTER.parse(data);
		} catch (ParseException e ) {
			System.out.println("DateConveror.StringToDate(String data) recebendo String inválida.");
			return null;
		} catch (NullPointerException e) {
			System.out.println("DateConveror.StringToDate(String data) recebendo String nula.");
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
	
	public static int getDaysDifference(Date date1, Date date2) {
		long daysDifference = date2.getTime() - date1.getTime();
		return (int) TimeUnit.DAYS.convert(daysDifference, TimeUnit.MILLISECONDS);
	}
	
}