package br.com.fitrank.modelo;

public class Course {
	
	private String id_course;
	private String modalidade;
	private float  distancia;
	private float  calorias;
	private float  ritmo;
	private String id_publicacao;
	
	public String getId_course() {
		return id_course;
	}
	public void setId_course(String id_course) {
		this.id_course = id_course;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	public float getCalorias() {
		return calorias;
	}
	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}
	public float getRitmo() {
		return ritmo;
	}
	public void setRitmo(float ritmo) {
		this.ritmo = ritmo;
	}
	public String getId_publicacao() {
		return id_publicacao;
	}
	public void setId_publicacao(String id_publicacao) {
		this.id_publicacao = id_publicacao;
	}
	
}
