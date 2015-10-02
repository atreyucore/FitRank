package br.com.fitrank.modelo.fb.Course;

import com.restfb.Facebook;

public class CustomUnitEnergy {
	
	@Facebook("value")
	private String value;
	
	@Facebook("units")
	private CustomUnitEnergyUnits units;
}
