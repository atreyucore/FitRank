package br.com.fitrank.service;

import java.sql.SQLException;

import br.com.fitrank.modelo.ImagemRanking;
import br.com.fitrank.persistencia.ImagemRankingDAO;

public class ImagemRankingServico {
	
	private ImagemRankingDAO imagemRankingDAO;
	
	public ImagemRanking adicionaRanking(ImagemRanking imagemRanking) {
		imagemRankingDAO = new ImagemRankingDAO();
		
		try {
			imagemRanking = imagemRankingDAO.adicionaImagemRanking(imagemRanking);
		} catch (SQLException e) {
			System.out.println("Erro ao salvar Ranking.");
			e.printStackTrace();
		}
		
		return imagemRanking;
	}
	
	public ImagemRanking leRanking(int idRanking) {
		imagemRankingDAO = new ImagemRankingDAO();
		ImagemRanking imagemRanking;
		
		try {
			imagemRanking = imagemRankingDAO.leImagemRanking(idRanking);
		} catch (SQLException e) {
			System.out.println("Erro ao ler Ranking.");
			System.out.println("id_ranking = " + idRanking);
			e.printStackTrace();
			imagemRanking = new ImagemRanking();
		}
		
		return imagemRanking;
	}

}