package br.com.fitrank.modelo;

//Entidade Jsoup do banco 
public class JSoup {

	private String id_course;
	
	private float duration;
	
	private float avg_pace;

	private float elevation_gain;

	private float calories;

	private float heart_rate;

	private float max_heart_rate;

	private String weather;

	private float celsius_degrees;

	private String place_kind;

	private String evaluation;

	private float avg_speed;

	private float red_line_heart_rate;

	private float red_line_heart_rate_duration;

	private float anaerobic_heart_rate;

	private float anaerobic_heart_rate_duration;

	private float aerobic_heart_rate;

	private float aerobic_heart_rate_duration;

	private float fat_burning_heart_rate;

	private float fat_burning_heart_rate_duration;

	private float easy_heart_rate;

	private float easy_heart_rate_duration;

	private float no_zone_heart_rate;

	private float no_zone_heart_rate_duration;
	
	private byte[] json_couse;
	
	private String json_url;

	public String getId_course() {
		return id_course;
	}

	public void setId_course(String id_course) {
		this.id_course = id_course;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public float getAvg_pace() {
		return avg_pace;
	}

	public void setAvg_pace(float avg_pace) {
		this.avg_pace = avg_pace;
	}

	public float getElevation_gain() {
		return elevation_gain;
	}

	public void setElevation_gain(float elevation_gain) {
		this.elevation_gain = elevation_gain;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public float getHeart_rate() {
		return heart_rate;
	}

	public void setHeart_rate(float heart_rate) {
		this.heart_rate = heart_rate;
	}

	public float getMax_heart_rate() {
		return max_heart_rate;
	}

	public void setMax_heart_rate(float max_heart_rate) {
		this.max_heart_rate = max_heart_rate;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public float getCelsius_degrees() {
		return celsius_degrees;
	}

	public void setCelsius_degrees(float celsius_degrees) {
		this.celsius_degrees = celsius_degrees;
	}

	public String getPlace_kind() {
		return place_kind;
	}

	public void setPlace_kind(String place_kind) {
		this.place_kind = place_kind;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public float getAvg_speed() {
		return avg_speed;
	}

	public void setAvg_speed(float avg_speed) {
		this.avg_speed = avg_speed;
	}

	public float getRed_line_heart_rate() {
		return red_line_heart_rate;
	}

	public void setRed_line_heart_rate(float red_line_heart_rate) {
		this.red_line_heart_rate = red_line_heart_rate;
	}

	public float getRed_line_heart_rate_duration() {
		return red_line_heart_rate_duration;
	}

	public void setRed_line_heart_rate_duration(float red_line_heart_rate_duration) {
		this.red_line_heart_rate_duration = red_line_heart_rate_duration;
	}

	public float getAnaerobic_heart_rate() {
		return anaerobic_heart_rate;
	}

	public void setAnaerobic_heart_rate(float anaerobic_heart_rate) {
		this.anaerobic_heart_rate = anaerobic_heart_rate;
	}

	public float getAnaerobic_heart_rate_duration() {
		return anaerobic_heart_rate_duration;
	}

	public void setAnaerobic_heart_rate_duration(float anaerobic_heart_rate_duration) {
		this.anaerobic_heart_rate_duration = anaerobic_heart_rate_duration;
	}

	public float getAerobic_heart_rate() {
		return aerobic_heart_rate;
	}

	public void setAerobic_heart_rate(float aerobic_heart_rate) {
		this.aerobic_heart_rate = aerobic_heart_rate;
	}

	public float getAerobic_heart_rate_duration() {
		return aerobic_heart_rate_duration;
	}

	public void setAerobic_heart_rate_duration(float aerobic_heart_rate_duration) {
		this.aerobic_heart_rate_duration = aerobic_heart_rate_duration;
	}

	public float getFat_burning_heart_rate() {
		return fat_burning_heart_rate;
	}

	public void setFat_burning_heart_rate(float fat_burning_heart_rate) {
		this.fat_burning_heart_rate = fat_burning_heart_rate;
	}

	public float getFat_burning_heart_rate_duration() {
		return fat_burning_heart_rate_duration;
	}

	public void setFat_burning_heart_rate_duration(
			float fat_burning_heart_rate_duration) {
		this.fat_burning_heart_rate_duration = fat_burning_heart_rate_duration;
	}

	public float getEasy_heart_rate() {
		return easy_heart_rate;
	}

	public void setEasy_heart_rate(float easy_heart_rate) {
		this.easy_heart_rate = easy_heart_rate;
	}

	public float getEasy_heart_rate_duration() {
		return easy_heart_rate_duration;
	}

	public void setEasy_heart_rate_duration(float easy_heart_rate_duration) {
		this.easy_heart_rate_duration = easy_heart_rate_duration;
	}

	public float getNo_zone_heart_rate() {
		return no_zone_heart_rate;
	}

	public void setNo_zone_heart_rate(float no_zone_heart_rate) {
		this.no_zone_heart_rate = no_zone_heart_rate;
	}

	public float getNo_zone_heart_rate_duration() {
		return no_zone_heart_rate_duration;
	}

	public void setNo_zone_heart_rate_duration(float no_zone_heart_rate_duration) {
		this.no_zone_heart_rate_duration = no_zone_heart_rate_duration;
	}

	public byte[] getJson_couse() {
		return json_couse;
	}

	public void setJson_couse(byte[] json_couse) {
		this.json_couse = json_couse;
	}

	public String getJson_url() {
		return json_url;
	}

	public void setJson_url(String json_url) {
		this.json_url = json_url;
	}
		
}
