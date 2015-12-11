package br.com.fitrank.modelo;

public class RankingPessoa {
	
	private int id_ranking;
	
	private String id_pessoa;
	
	private int colocacao;
	
	private float resultado;
	
	private float distancia_percorrida;
	
	private float velocidade_media;
	
	private int quantidade_corridas;
	
	//table: pessoa - join column: id_pessoa
	private Pessoa pessoa;

	public int getId_ranking() {
		return id_ranking;
	}

	public void setId_ranking(int id_ranking) {
		this.id_ranking = id_ranking;
	}

	public String getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(String id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public int getColocacao() {
		return colocacao;
	}

	public void setColocacao(int colocacao) {
		this.colocacao = colocacao;
	}

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}

	public float getDistancia_percorrida() {
		return distancia_percorrida;
	}

	public void setDistancia_percorrida(float distancia_percorrida) {
		this.distancia_percorrida = distancia_percorrida;
	}

	public float getVelocidade_media() {
		return velocidade_media;
	}

	public void setVelocidade_media(float velocidade_media) {
		this.velocidade_media = velocidade_media;
	}

	public int getQuantidade_corridas() {
		return quantidade_corridas;
	}

	public void setQuantidade_corridas(int quantidade_corridas) {
		this.quantidade_corridas = quantidade_corridas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
