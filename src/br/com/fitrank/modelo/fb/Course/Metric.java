package br.com.fitrank.modelo.fb.Course;

import br.com.fitrank.modelo.fb.auxiliary.Grandeza;

import com.restfb.Facebook;

public class Metric {
	
	@Facebook("custom_unit_energy")
	private CustomUnitEnergy custom_unit_energy;
	
	@Facebook("distance")
	private Grandeza distance;
	
	@Facebook("pace")
	private Grandeza pace;
	
	@Facebook("timestamp")
	private String timestamp;
	
	//RunKeeper
	@Facebook("calories")
	private String calories;
	
	@Facebook("location")
	private Location location;

	public CustomUnitEnergy getCustom_unit_energy() {
		return custom_unit_energy;
	}

	public void setCustom_unit_energy(CustomUnitEnergy custom_unit_energy) {
		this.custom_unit_energy = custom_unit_energy;
	}

	public Grandeza getDistance() {
		return distance;
	}

	public void setDistance(Grandeza distance) {
		this.distance = distance;
	}

	public Grandeza getPace() {
		return pace;
	}

	public void setPace(Grandeza pace) {
		this.pace = pace;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
