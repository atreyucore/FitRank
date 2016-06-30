package br.com.fitrank.util;

import java.util.Date;

public class Logger {

	public static void insertLog(String msg) {
		System.out.println( "[[DEBUG FitRank]] " + DateConversor.DateHourToString(new Date()) + " " + msg);
		
	}

	
}
