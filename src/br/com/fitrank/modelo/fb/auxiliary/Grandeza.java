package br.com.fitrank.modelo.fb.auxiliary;

import com.restfb.Facebook;

public class Grandeza {
	@Facebook("value")
	private String value;
	
	@Facebook("units")
	private String units;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
}
