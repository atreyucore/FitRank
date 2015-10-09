package br.com.fitrank.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class PostFitnessUtil {
	
	public double getNikeDistance(String courseTitle) {
		
		String miles = courseTitle.split("miles")[0].trim();
		
		if ( miles.charAt(0) == '.' ) {
			miles = "0".concat(miles);
		}
		
		double kms = Double.parseDouble(miles) * 1.60934;
		
		return kms;
	}
	
	public double getNikeDuration(String strStartTime, String strEndTime) {
		
		Date startTime =  DateConversor.StringToDate(strStartTime);
		
		Date endTime =  DateConversor.StringToDate(strEndTime);
		
		GregorianCalendar gcStartTime = DateConversor.convertDateToGregorian(startTime);
		
		GregorianCalendar gcEndTime = DateConversor.convertDateToGregorian(endTime);
		
		long diffMillis = gcEndTime.getTimeInMillis() - gcStartTime.getTimeInMillis();
		
		return (double) diffMillis/3600000;
		
	}
	
	public double getRuntasticDistance(String courseTitle) {
	
		courseTitle = courseTitle.replace("a ", "");
		
		String kms = courseTitle.split("km")[0].trim();
		
		return Double.parseDouble(kms);
	}
	
	public double getRuntasticDuration(String courseTitle) {
		
		courseTitle = courseTitle.split("in")[1].trim();
		
		String strHour = courseTitle.split("h")[0].trim();
		
		String strMinutes = courseTitle.split("h")[1].split("m")[0].trim();
		
		String strSeconds = courseTitle.split("m")[1].split("s")[0].trim();
		
		double doubleHour = Double.parseDouble(strHour);
		
		double doubleMinutesInHour = Double.parseDouble(strMinutes) / 60;
		
		double doubleSecondsInHour = Double.parseDouble(strSeconds) / 3600;
		
		return doubleHour + doubleMinutesInHour + doubleSecondsInHour;
		
	}
}
