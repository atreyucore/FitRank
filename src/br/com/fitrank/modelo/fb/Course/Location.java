package br.com.fitrank.modelo.fb.Course;

import com.restfb.Facebook;

public class Location {
	
	@Facebook("latitude")
	private String latitude;
	
	@Facebook("longitude")
	private String longitude;
		
	@Facebook("altitude")
	private String altitude;
	
}
