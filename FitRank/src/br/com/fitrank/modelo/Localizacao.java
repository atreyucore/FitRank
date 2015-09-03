package br.com.fitrank.modelo;
//TODO Verificar classe no banco, classe de modelo e UML
public class Localizacao {

	private int 	id_localizacao;
	private double  latitude;
	private double 	longitude;
	private double  altitude;
	private String  id_course;
	
	public int getId_localizacao() {
		return id_localizacao;
	}
	public void setId_localizacao(int id_localizacao) {
		this.id_localizacao = id_localizacao;
	}
	public String getId_course() {
		return id_course;
	}
	public void setId_course(String id_course) {
		this.id_course = id_course;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
	
}
