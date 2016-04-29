package br.com.fitrank.modelo;

import java.sql.Blob;

public class ImagemRanking {
	
	private int id_ranking;
	
	private Blob imagem;

	public int getId_ranking() {
		return id_ranking;
	}

	public void setId_ranking(int id_ranking) {
		this.id_ranking = id_ranking;
	}

	public Blob getImagem() {
		return imagem;
	}

	public void setImagem(Blob imagem) {
		this.imagem = imagem;
	}
}
