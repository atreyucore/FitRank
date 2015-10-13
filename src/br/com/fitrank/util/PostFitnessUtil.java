package br.com.fitrank.util;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

public class PostFitnessUtil {
	
	public static double getNikeDistance(String courseTitle) {
		
		String miles = courseTitle.split("miles")[0].trim();
		
		if ( miles.charAt(0) == '.' ) {
			miles = "0".concat(miles);
		}
		
		double kms = Double.parseDouble(miles) * 1.60934;
		
		return kms;
	}
	
	public static double getNikeDuration(Date startTime, Date endTime) {
		
//		Date startTime =  DateConversor.StringToDate(strStartTime);
		
//		Date endTime =  DateConversor.StringToDate(strEndTime);
		
		GregorianCalendar gcStartTime = DateConversor.convertDateToGregorian(startTime);
		
		GregorianCalendar gcEndTime = DateConversor.convertDateToGregorian(endTime);
		
		long diffMillis = gcEndTime.getTimeInMillis() - gcStartTime.getTimeInMillis();
		
		return (double) diffMillis/3600000;
		
	}
	
	public static double getRuntasticDistance(String courseTitle) {
	
		courseTitle = courseTitle.replace("a ", "");
		
		String kms = courseTitle.split("km")[0].trim();
		
		return Double.parseDouble(kms);
	}
	
	public static double getRuntasticDuration(String courseTitle) {
		
		courseTitle = courseTitle.split("in")[1].trim();
		
		String strHour = courseTitle.split("h")[0].trim();
		
		String strMinutes = courseTitle.split("h")[1].split("m")[0].trim();
		
		String strSeconds = courseTitle.split("m")[1].split("s")[0].trim();
		
		double doubleHour = Double.parseDouble(strHour);
		
		double doubleMinutesInHour = Double.parseDouble(strMinutes) / 60;
		
		double doubleSecondsInHour = Double.parseDouble(strSeconds) / 3600;
		
		return doubleHour + doubleMinutesInHour + doubleSecondsInHour;
		
	}
	
	public static double getRunKeeperDistance(String courseTitle) {
		
//		courseTitle = courseTitle.replace("a ", "");
		
		String kms = courseTitle.split("km")[0].trim();
		
		return Double.parseDouble(kms);
	}
	
	public static double getRunKeeperDuration(String courseTitle) {
		
		int countColons = StringUtils.countMatches(courseTitle, ":");
		
		
		courseTitle = courseTitle.split("in")[1].trim();
		
		String strHour = "";
		String strMinutes = "";
		String strSeconds = "";
		
		if (countColons == 1) {
			
			strMinutes = courseTitle.split(":")[0].trim();
			
			strSeconds = courseTitle.split(":")[1].trim();
			
		} else if (countColons == 2) {
			strHour = courseTitle.split(":")[0].trim();
			
			strMinutes = courseTitle.split(":")[1].trim();
			
			strSeconds = courseTitle.split(":")[2].trim();
		}
		
		
		double doubleHour = 0.0;
		double doubleMinutesInHour = 0.0;
		double doubleSecondsInHour = 0.0;
		
		if (! strHour.equals("")){
			doubleMinutesInHour = Double.parseDouble(strMinutes) / 60;
			
			doubleSecondsInHour = Double.parseDouble(strSeconds) / 3600;
		} else {
			doubleHour = Double.parseDouble(strHour);
		}
		
		return doubleHour + doubleMinutesInHour + doubleSecondsInHour;
		
	}	
}
