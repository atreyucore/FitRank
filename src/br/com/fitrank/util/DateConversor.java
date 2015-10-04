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
	
	public static String getPreviousYear(){
		gc = new GregorianCalendar();
		gc.add(Calendar.YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static String getPreviousMonth(){
		gc = new GregorianCalendar();
		gc.add(Calendar.MONTH, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static String getPreviousWeek(){
		gc = new GregorianCalendar();
		gc.add(Calendar.WEEK_OF_YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
	
	public static String getPreviousDay(){
		gc = new GregorianCalendar();
		gc.add(Calendar.DAY_OF_YEAR, -1);
		return FORMATTER.format(gc.getTime());
	}
}