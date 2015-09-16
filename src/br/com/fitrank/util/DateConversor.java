package br.com.fitrank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversor {
	
	private static final String FORMATO_DATA = "dd/MM/yyyy";
	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(FORMATO_DATA);
	
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
}