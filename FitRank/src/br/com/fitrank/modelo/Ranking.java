package br.com.fitrank.modelo;

public class Ranking {
	
	private int id_ranking;
	
	private String titulo;
	
	private int id_configuracao;
	
	private String data_ranking;

	public int getId_ranking() {
		return id_ranking;
	}

	public void setId_ranking(int id_ranking) {
		this.id_ranking = id_ranking;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getId_configuracao() {
		return id_configuracao;
	}

	public void setId_configuracao(int id_configuracao) {
		this.id_configuracao = id_configuracao;
	}

	public String getData() {
		return data_ranking;
	}

	public void setData(String data) {
		this.data_ranking = data;
	}
	
	
}
