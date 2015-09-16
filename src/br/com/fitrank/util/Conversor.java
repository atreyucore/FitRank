package br.com.fitrank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {
	
	public static String DateToString(Date data, String formato) {
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		return formatter.format(data);
	}
	
	public static Date StringToDate(String data, String formato){
		SimpleDateFormat formatter = new SimpleDateFormat(formato);
		try {
			return formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}