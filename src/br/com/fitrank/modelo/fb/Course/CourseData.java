package br.com.fitrank.modelo.fb.Course;

import java.util.List;

import br.com.fitrank.modelo.fb.auxiliary.Grandeza;

import com.restfb.Facebook;

public class CourseData {

	//Somente para NikeRunning	
	@Facebook("custom_unit_energy")
	private CustomUnitEnergy custom_unit_energy;	
	
	@Facebook("distance")
	private Grandeza distance;
	
	@Facebook("duration")
	private Grandeza duration;
	
	@Facebook("metrics")
	private List<Metric> metrics;
	
	@Facebook("pace")
	private Grandeza pace;
	
	@Facebook("calories")
	private String calories;

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

	public Grandeza getDuration() {
		return duration;
	}

	public void setDuration(Grandeza duration) {
		this.duration = duration;
	}

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

	public Grandeza getPace() {
		return pace;
	}

	public void setPace(Grandeza pace) {
		this.pace = pace;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	
}
